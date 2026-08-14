#!/usr/bin/env python3
"""コミット前の公開基準の機械検査。違反があれば終了コード1 (fail-closed)。

このリポジトリはpublic前提なので、trackedとstagedの全テキストファイルを走査し、
公開基準 (CLAUDE.md第4節) に反する痕跡を検出する。

- 実在のホームディレクトリのパス (/Users/名前、C:\\Users\\名前、/home/名前)。
  <user>のようなプレースホルダは許可
- 追加の禁止パターンは、git管理外のprivate-patterns.local.txt (1行1正規表現) に置く。
  禁止する名前のリスト自体をtrackedに書くと本末転倒なので、trackedのこのスクリプトには
  一般形のパターンだけを持つ

使い方 (コミットと必ず&&で直結する。検査を先に流して目視してから別の連鎖でコミットしない):
    python scripts/check_publish_safety.py && git commit ...

公開範囲を変える操作 (public切替) の前には、全コミット履歴も検査する:
    python scripts/check_publish_safety.py --history

素のgrepを門に使ってはいけない。grepはヒットで終了コード0を返すため、&&で直結すると
「見つかったのに通る」逆向きの門になる。
"""
from __future__ import annotations

import re
import subprocess
import sys
from pathlib import Path

REPO_ROOT = Path(__file__).resolve().parent.parent

PATTERNS = [
    re.compile(r"/Users/(?![<*])[A-Za-z0-9_.-]+"),
    re.compile(r"C:[\\/]+Users[\\/]+(?![<*])[A-Za-z0-9_.-]+", re.IGNORECASE),
    re.compile(r"/home/(?![<*])[A-Za-z0-9_.-]+"),
]


def load_local_patterns() -> list:
    extra = REPO_ROOT / "private-patterns.local.txt"
    patterns = []
    if extra.is_file():
        for line in extra.read_text(encoding="utf-8").splitlines():
            line = line.strip()
            if line and not line.startswith("#"):
                patterns.append(re.compile(line))
    return patterns


def target_files() -> list:
    out = subprocess.run(
        ["git", "-C", str(REPO_ROOT), "ls-files", "--cached", "--others", "--exclude-standard"],
        capture_output=True,
        text=True,
        check=True,
    )
    return [REPO_ROOT / line for line in out.stdout.splitlines() if line]


def scan_files(patterns: list) -> tuple:
    hits = []
    checked = 0
    for path in target_files():
        if not path.is_file():
            continue
        try:
            text = path.read_text(encoding="utf-8")
        except (UnicodeDecodeError, OSError):
            continue  # バイナリは対象外
        checked += 1
        rel = path.relative_to(REPO_ROOT)
        for i, line in enumerate(text.splitlines(), start=1):
            for pat in patterns:
                m = pat.search(line)
                if m:
                    hits.append(f"{rel}:{i}: {m.group(0)}")
                    break
    return hits, checked


def scan_history(patterns: list) -> tuple:
    """全ブランチの全コミット (メッセージと差分) を検査する。public切替の前に使う。"""
    out = subprocess.run(
        ["git", "-C", str(REPO_ROOT), "log", "--all", "-p", "--format=commit %H %s"],
        capture_output=True,
        text=True,
        encoding="utf-8",
        errors="replace",
        check=True,
    )
    hits = []
    current = "?"
    lines = out.stdout.splitlines()
    for line in lines:
        if line.startswith("commit "):
            current = line.split()[1][:12]
            continue
        for pat in patterns:
            m = pat.search(line)
            if m:
                hits.append(f"history {current}: {m.group(0)}")
                break
    return hits, len(lines)


def main() -> int:
    patterns = PATTERNS + load_local_patterns()
    if "--history" in sys.argv:
        hits, scanned = scan_history(patterns)
        label = f"全履歴{scanned}行"
    else:
        hits, scanned = scan_files(patterns)
        label = f"{scanned}ファイル"
    if hits:
        for h in hits:
            print(f"NG {h}")
        print(f"公開基準の検査失敗: {len(hits)}件。実在のパスや固有名を一般形へ直すこと")
        return 1
    print(f"公開基準の検査通過: {label}、問題なし")
    return 0


if __name__ == "__main__":
    sys.exit(main())
