# HANDOFF

いまの作業状態と次の作業だけを現在形で書く。経緯はgitログが正。

## 現在地 (2026-08-14)

- リポジトリを新設した。Forge公式MDK 1.18.2-40.3.11 (実機インスタンスのForgeと同版) から雛形を生成し、modid vhjapanese、最小の@Modクラスだけがある状態
- GitHubへpush済み (2026-08-15、privateで作成)。publicへの切替は公開準備要件を満たしたときに本人が行う
- ロゴはテーマ実測パレットのA案 (焦げ茶#2F0B04基調、琥珀→焔のダイヤ、羊皮紙文字) を採用し、src/main/resources/logo.pngへ同梱、mods.tomlのlogoFileで宣言済み (2026-08-15)
- 初回ビルドの結果はgitログとコミット本文が正
- 公開基準の機械検査 scripts/check_publish_safety.py を備えた (通常はtracked+staged、`--history`で全コミット履歴。内部の固有名はgit管理外のローカルパターン表に置き、禁止リスト自体を公開しない設計)。コミットは検査との&&直結が運用 (CLAUDE.md第4節)
- 翻訳データ (訳文のみ) の取り込みと、翻訳適用の実装はまだ無い

## 次の作業 (順番どおり)

1. lang層とPatchouliガイドブックのja_jpをassetsへ同梱する生成の型を作る (訳文のみ。原文を含めない)
2. VaultPatcher連携: Required Library宣言 (CurseForge側) と、置換設定をconfig/vaultpatcher_asm/へ供給する実装
3. config層の差し込み実装 (起動時に手元の英語configへ訳文をマージ。バックアップと復元を必ず持つ)
4. dev環境 (runClient) と実機インスタンスでの起動・表示確認
5. CurseForge投稿 (作者アカウントの登録と投稿の実行は本人)。public切替の前に`--history`の全履歴検査を通す

## 決まっていること

- 名前: Vault Hunters 3 Japanese Translation (日本語化)。CurseForgeスラッグ vault-hunters-3-japanese-translation、modid vhjapanese。GitHubリポジトリ名は手元のリポジトリ群の慣行に合わせminecraft-接頭辞つき (2026-08-15の本人指示)
- 対象: modpack 3.21.7 (Forge 1.18.2-40.3.11)
- 権利の姿勢: 非公式・非収益・訳文のみ・権利者の要請があれば取り下げ (CLAUDE.md第2節)
- VaultPatcher ASM版 (forge-1.2.14-asm) はmods.tomlを持たずForgeのmodidが無い (2026-08-14に実物jarで実測)。mods.tomlでの依存宣言は不可能。依存はCurseForgeのRequired Library宣言で表現し、本modはVaultPatcher不在でもlang層とconfig層が動く設計にする
- the_vaultのmodidは"the_vault" (実物jarのmods.tomlで実測)。mods.tomlに必須依存として宣言済み。読み込み順 (ordering) はthe_vaultのconfig読み込みタイミングの実測後に確定する
