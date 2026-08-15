# HANDOFF

いまの作業状態と次の作業だけを現在形で書く。経緯はgitログが正。

## 現在地 (2026-08-16)

- **翻訳適用の実装が済み、実機インスタンスで機械検証済み**: 白紙化した実機 (config原文化+VaultPatcher設定とガイドブックja_jp削除+旧リソースパック撤去) に本modのjarだけを置き、初回起動401msでガイドブック61ファイル配置・VaultPatcher設定供給・config層21ファイル差し込み (採用7,921件 = 翻訳作業側ビルダーと完全一致) を確認した。2回目起動は全層書き込みゼロ (冪等、325ms)。3回目起動でVaultPatcherがthe_vault_vpモジュール (3,587件) を読み込みハードコード層が有効化。ERROR行は未mod時代の検証済み起動と同一signatureの本体側既存ノイズで、FATAL 0
- 事前の等価性検査も通過: コンパイル済み実物のConfigMerger+AsciiJsonの出力を、実機画面検証済みのPython版ビルダー出力と全21ファイル突き合わせ、20がバイト同一・1が数値リテラル表記差のみのJSON同値
- VaultPatcherは起動最初期 (TransformationService初期化時) に設定を読むことをログで実測した。本modの設定供給が効くのは次回起動からで、mod導入の初回だけハードコード層が英語のまま (仕様として説明文に書く)
- **ゲーム画面での視認 (文字化け・はみ出し) は未検証**。適用データはzip版時代に画面検証済みのものとバイト同一だが、mod経由 (特にlang層のmod assets読み込み) の目視を公開前に一度行う
- GitHubへpush済み (private)。ロゴはC案 (羊皮紙基調) を同梱済み。公開基準の機械検査あり (`--history`で全履歴も)。コミットは検査との&&直結が運用 (CLAUDE.md第4節)

## 次の作業 (順番どおり)

1. ゲーム画面での視認確認 (lang・config・ガイドブック・ハードコードの代表面。ゲーム画面の操作許可が要る)
2. リリースづくり: 説明文の最終化 (mod導入初回はハードコード層が次回起動から有効、の一文を含める)、バージョン3.21.7-ja.1のタグ、`--history`全履歴検査
3. CurseForge投稿 (作者アカウントの登録と投稿の実行は本人)。public切替の前に`--history`の全履歴検査を通す

## 決まっていること

- 名前: Vault Hunters 3 Japanese Translation (日本語化)。CurseForgeスラッグ vault-hunters-3-japanese-translation、modid vhjapanese。GitHubリポジトリ名は手元のリポジトリ群の慣行に合わせminecraft-接頭辞つき (2026-08-15の本人指示)
- 対象: modpack 3.21.7 (Forge 1.18.2-40.3.11)
- 権利の姿勢: 非公式・非収益・訳文のみ・権利者の要請があれば取り下げ (CLAUDE.md第2節)
- VaultPatcher ASM版 (forge-1.2.14-asm) はmods.tomlを持たずForgeのmodidが無い (2026-08-14に実物jarで実測)。mods.tomlでの依存宣言は不可能。依存はCurseForgeのRequired Library宣言で表現し、本modはVaultPatcher不在でもlang層とconfig層が動く設計にする
- the_vaultのmodidは"the_vault" (実物jarのmods.tomlで実測)。必須依存・versionRange [0,) (実バージョン文字列がMC版接頭辞つきのため範囲指定はしない)・ordering=BEFORE (本modの構築とconfig差し込みをthe_vaultより先行させる) で宣言済み。実機3回の起動で動作を確認済み
