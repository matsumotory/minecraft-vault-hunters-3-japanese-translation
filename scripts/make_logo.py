#!/usr/bin/env python3
"""プロジェクトロゴの生成 (自作。ゲーム画像・公式アートは使わない)。

採用案 (羊皮紙基調のC案) を src/main/resources/logo.png へ書き出し、
検討用の別案2つを build/logo-variants/ (git管理外) へ書き出す。

色はVault Huntersのテーマの実測パレットだけを使う (2026-08-14定義):
- #2F0B04 焦げ茶      … 公式サイトvaulthunters.ggのダーク基調 (計算済みスタイルの実測)
- #192022 暗スレート  … ゲーム内文字列の色タグ頻出色
- #FF9500 焔オレンジ  … 公式サイトの主アクセント (ゲーム内#FF8900と同族)
- #FFBA00 琥珀        … ゲーム内色タグ (金はこの琥珀で代える)
- #FCF5C5 羊皮紙      … ゲーム内色タグ (主文字色)
- #A3E2F5 結晶シアン  … ゲーム内色タグ (差し色。微量のみ)
- #D87B00 濃橙        … ゲーム内色タグ (明色地の濃アクセント)

必要: Pillow (pip install pillow)、日本語太字フォント (既定はWindowsのYu Gothic Bold。
他OSではFONTを差し替える)。

使い方:
    python scripts/make_logo.py
"""
from pathlib import Path

from PIL import Image, ImageDraw, ImageFont

REPO_ROOT = Path(__file__).resolve().parent.parent
FONT = "C:/Windows/Fonts/YuGothB.ttc"

W = 512
UMBER = (47, 11, 4)
SLATE = (25, 32, 34)
EMBER = (255, 149, 0)
AMBER = (255, 186, 0)
PARCH = (252, 245, 197)
CYAN = (163, 226, 245)
DARKORANGE = (216, 123, 0)

CX, CY, R_OUT, R_IN = W // 2, 232, 168, 148


def diamond(cx: int, cy: int, r: int) -> list:
    return [(cx, cy - r), (cx + r, cy), (cx, cy + r), (cx - r, cy)]


def vgrad(top: tuple, bottom: tuple, w: int, h: int) -> Image.Image:
    g = Image.new("RGB", (1, h))
    for y in range(h):
        t = y / (h - 1)
        g.putpixel((0, y), tuple(round(top[i] + (bottom[i] - top[i]) * t) for i in range(3)))
    return g.resize((w, h))


def ring_mask(cx: int, cy: int, r_out: int, r_in: int) -> Image.Image:
    m = Image.new("L", (W, W), 0)
    dm = ImageDraw.Draw(m)
    dm.polygon(diamond(cx, cy, r_out), fill=255)
    dm.polygon(diamond(cx, cy, r_in), fill=0)
    return m


def compose(bg: tuple, frame: tuple, grad_top: tuple, grad_bottom: tuple,
            glyph_color: tuple, title_color: tuple, sub_color: tuple,
            inner_outline: tuple = None) -> Image.Image:
    img = Image.new("RGB", (W, W), bg)
    d = ImageDraw.Draw(img)
    d.rounded_rectangle([14, 14, W - 14, W - 14], radius=40, outline=frame, width=3)
    img.paste(vgrad(grad_top, grad_bottom, W, W), (0, 0), ring_mask(CX, CY, R_OUT, R_IN))
    d = ImageDraw.Draw(img)
    if inner_outline:
        d.polygon(diamond(CX, CY, R_IN - 8), outline=inner_outline, width=3)
    f_kanji = ImageFont.truetype(FONT, 150, index=0)
    f_en = ImageFont.truetype(FONT, 34, index=0)
    f_sub = ImageFont.truetype(FONT, 24, index=0)
    d.text((CX, CY), "日", font=f_kanji, fill=glyph_color, anchor="mm")
    d.text((CX, 438), "VAULT HUNTERS 3", font=f_en, fill=title_color, anchor="mm")
    d.text((CX, 474), "JAPANESE TRANSLATION", font=f_sub, fill=sub_color, anchor="mm")
    return img


def main() -> None:
    # 採用: C案 (羊皮紙基調の反転。明るい一覧面で目立つ)
    adopted = compose(PARCH, DARKORANGE, EMBER, DARKORANGE, UMBER, UMBER, DARKORANGE)
    dest = REPO_ROOT / "src" / "main" / "resources" / "logo.png"
    adopted.save(dest)
    print(f"採用ロゴ (C案): {dest}")

    # 別案 (検討の再現用。git管理外へ)
    variants = REPO_ROOT / "build" / "logo-variants"
    variants.mkdir(parents=True, exist_ok=True)
    compose(UMBER, (90, 40, 18), AMBER, EMBER, PARCH, PARCH, EMBER).save(variants / "a_umber.png")
    compose(SLATE, (52, 66, 70), AMBER, EMBER, PARCH, PARCH, CYAN,
            inner_outline=CYAN).save(variants / "b_slate.png")
    print(f"別案2つ: {variants}")


if __name__ == "__main__":
    main()
