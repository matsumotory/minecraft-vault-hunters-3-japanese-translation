# HANDOFF

いまの作業状態と次の作業だけを現在形で書く。経緯はgitログが正。

## 現在地 (2026-08-14)

- リポジトリを新設した。Forge公式MDK 1.18.2-40.3.11 (実機インスタンスのForgeと同版) から雛形を生成し、modid vhjapanese、最小の@Modクラスだけがある状態
- 初回ビルドの結果はgitログとコミット本文が正
- 翻訳データ (訳文のみ) の取り込みと、翻訳適用の実装はまだ無い

## 次の作業 (順番どおり)

1. lang層とPatchouliガイドブックのja_jpをassetsへ同梱する生成の型を作る (訳文のみ。原文を含めない)
2. VaultPatcher連携: Required Library宣言 (CurseForge側) と、置換設定をconfig/vaultpatcher_asm/へ供給する実装
3. config層の差し込み実装 (起動時に手元の英語configへ訳文をマージ。バックアップと復元を必ず持つ)
4. dev環境 (runClient) と実機インスタンスでの起動・表示確認
5. 公開基準の機械検査 (ローカルパス・個人情報・原文混入の検出) をこのリポジトリのscripts/へ用意し、コミット前に&&で直結する運用にする
6. GitHubリポジトリの作成とCurseForge投稿 (実行は本人)

## 決まっていること

- 名前: Vault Hunters 3 Japanese Translation (日本語化)。スラッグ vault-hunters-3-japanese-translation、modid vhjapanese
- 対象: modpack 3.21.7 (Forge 1.18.2-40.3.11)
- 権利の姿勢: 非公式・非収益・訳文のみ・権利者の要請があれば取り下げ (CLAUDE.md第2節)
- VaultPatcher ASM版 (forge-1.2.14-asm) はmods.tomlを持たずForgeのmodidが無い (2026-08-14に実物jarで実測)。mods.tomlでの依存宣言は不可能。依存はCurseForgeのRequired Library宣言で表現し、本modはVaultPatcher不在でもlang層とconfig層が動く設計にする
- the_vaultのmodidは"the_vault" (実物jarのmods.tomlで実測)。mods.tomlに必須依存として宣言済み。読み込み順 (ordering) はthe_vaultのconfig読み込みタイミングの実測後に確定する
