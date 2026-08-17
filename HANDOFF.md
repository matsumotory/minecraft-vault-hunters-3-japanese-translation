# HANDOFF

いまの作業状態と次の作業だけを現在形で書く。経緯はgitログが正。

## 現在地 (2026-08-16)

- **翻訳適用の実装が済み、実機インスタンスで機械検証済み**: 白紙化した実機 (config原文化+VaultPatcher設定とガイドブックja_jp削除+旧リソースパック撤去) に本modのjarだけを置き、初回起動401msでガイドブック61ファイル配置・VaultPatcher設定供給・config層21ファイル差し込み (採用7,921件 = 翻訳作業側ビルダーと完全一致) を確認した。2回目起動は全層書き込みゼロ (冪等、325ms)。3回目起動でVaultPatcherがthe_vault_vpモジュール (3,587件) を読み込みハードコード層が有効化。ERROR行は未mod時代の検証済み起動と同一signatureの本体側既存ノイズで、FATAL 0
- 事前の等価性検査も通過: コンパイル済み実物のConfigMerger+AsciiJsonの出力を、実機画面検証済みのPython版ビルダー出力と全21ファイル突き合わせ、20がバイト同一・1が数値リテラル表記差のみのJSON同値
- VaultPatcherは起動最初期 (TransformationService初期化時) に設定を読むことをログで実測した。本modの設定供給が効くのは次回起動からで、mod導入の初回だけハードコード層が英語のまま (仕様として説明文に書く)
- **ゲーム画面の視認確認も完了 (2026-08-16)**: タイトルとワールド選択の日本語UI、クリエイティブのアイテム名とタブ名 (lang層のmod assets読み込み)、エキスパティーズ説明とクエストブック全文 (config層)、クリスタルツールチップ・統計画面・習得ボタン (ハードコード層) を実機画面で確認。文字化け・崩れなし。識別子系の英語残し (統計のArmor等6語、エキスパティーズ名) も設計どおり。Patchouliガイドブックの画面表示だけは未視認 (翻訳作業側に元からある残確認項目と同一。プレイヤー露出が低く、ファイル配置は機械検証済み)
- GitHubへpush済み (private)。ロゴはC案 (羊皮紙基調) を同梱済み。公開基準の機械検査あり (`--history`で全履歴も)。コミットは検査との&&直結が運用 (CLAUDE.md第4節)

## 現在地の追記 (2026-08-16)

- GitHubはpublic切替とRelease (3.21.7-ja.1、jar添付) を本人が実施済み
- CurseForgeへ本人が投稿済み (2026-08-16)。プロジェクトとファイルは審査待ち (Status: New)。審査は数分から3営業日

## 次の作業 (順番どおり)

1. Relations (VaultPatcherのRequired Dependency) が設定済みかを確認する (未設定ならプロジェクト編集のRelationsタブから。自動導入の要)
2. 審査結果の反映。承認: READMEへCurseForgeリンクを追加し、各リポジトリへ完了を焼き込む。差し戻し (Changes Required): 指示文に即対応して再提出

リリース物はバージョン3.21.7-ja.1 (タグ済み)。jarは`gradlew build`でbuild/libs/へ再現できる。

## 決まっていること

- 名前: Vault Hunters 3 Japanese Translation (日本語化)。CurseForgeスラッグ vault-hunters-3-japanese-translation、modid vhjapanese。GitHubリポジトリ名は手元のリポジトリ群の慣行に合わせminecraft-接頭辞つき (2026-08-15の本人指示)
- 対象: modpack 3.21.7 (Forge 1.18.2-40.3.11)
- 権利の姿勢: 非公式・非収益・訳文のみ・権利者の要請があれば取り下げ (CLAUDE.md第2節)
- VaultPatcher ASM版 (forge-1.2.14-asm) はmods.tomlを持たずForgeのmodidが無い (2026-08-14に実物jarで実測)。mods.tomlでの依存宣言は不可能。依存はCurseForgeのRequired Library宣言で表現し、本modはVaultPatcher不在でもlang層とconfig層が動く設計にする
- the_vaultのmodidは"the_vault" (実物jarのmods.tomlで実測)。必須依存・versionRange [0,) (実バージョン文字列がMC版接頭辞つきのため範囲指定はしない)・ordering=BEFORE (本modの構築とconfig差し込みをthe_vaultより先行させる) で宣言済み。実機3回の起動で動作を確認済み
