# HANDOFF

いまの作業状態と次の作業だけを現在形で書く。経緯はgitログが正。

## 現在地 (2026-08-17)

- **3.21.7-ja.2の実装と実機検証が完了。GitHub ReleaseとCurseForgeへのファイル追加 (本人操作) を待っている状態**
- ja.2の内容: CurseForge経由のまっさら導入でハードコード層だけが効かない問題の修正。原因は、依存解決が配る現行VaultPatcher (全ローダー版1.5系) がモジュール一覧をconfig.jsonの"modules"キーから読むのに対し、ja.1は旧1.2系の"mods"キーだけを書いていたこと。修正は (1) config.jsonへ新旧両キーを書く (テンプレートとマージ処理の両方)、(2) モジュール本体を旧位置 config/vaultpatcher_asm/ と新位置 <ゲームdir>/vaultpatcher/modules/ の両方へ配置する (1.5系は一度移行したら新位置しか見ないため、訳の更新も新位置の上書きで伝搬させる)。VaultPatcherの系統仕様の実測は翻訳作業側リポジトリのdocs/調査結果.md第4節が正
- ja.2の実機検証 (2026-08-17): まっさらE2E (インスタンス削除→CFアプリでmodpack再インストール→アプリ内検索から本modインストール→VaultPatcher 1.5.3自動導入) の後、VaultPatcher関連状態を白紙化してja.2 jarで2回起動し、(1) 1回目起動でconfig.json両キー+モジュール両位置の配置 (適用187ms)、(2) 2回目起動でクリスタルツールチップの日本語化 (レベル/容量/目標/テーマ/レイアウト) とクエストブックのハードコード部分 (クエスト概要/説明:/未達成) を画面で確認。FATAL 0、冪等性も確認 (再起動で書き込みゼロ)
- E2Eで見つけたREADMEの欠落2点も修正済み: (1) 新規プロファイルは「コンテンツ管理の許可」が既定オフでmodを追加できない (手順3に有効化手順を追加)、(2) 言語設定を1回目起動のあいだに行う順序へ手順4を組み替え (本人の指摘)。README.en.mdとdocs/curseforge-description.mdにも同じ変更を反映済み
- CurseForgeで公開済み (2026-08-17、初回審査で一発承認): https://www.curseforge.com/minecraft/mc-mods/vault-hunters-3-japanese-translation 。**現在公開されているja.1はCF経由の新規導入でハードコード層が効かないため、ja.2の掲載を急ぐ**

## 次の作業 (順番どおり)

1. 本人操作: GitHub Releaseの作成 (タグ3.21.7-ja.2、検証済みjarを添付) と、CurseForgeへのファイル追加 (Game Version 1.18.2+Forge、Release種別、ChangelogはCHANGELOG.mdのja.2節を貼る)。Descriptionの【入れかた】もdocs/curseforge-description.mdの最新へ差し替える
2. 次版 (ja.3) の候補機能: 初回起動の適用が終わったら、ゲーム内 (タイトル画面のトースト等) で「再起動すると翻訳がすべて有効になります」と利用者へ通知する。1回目の起動のまま遊び続ける利用者をなくすため (2026-08-17の本人の指摘。READMEは再起動を手順に組み込む形で対応済み)
3. modpack更新 (3.21.8以降やRemastered対応) が来たら、翻訳作業側で差分を訳してから、生成スクリプトでこちらへ取り込み、バージョンを上げて配布する

リリース物はバージョン3.21.7-ja.2。jarは`gradlew build`でbuild/libs/へ再現できる (実機検証したjarはmainのbuild/libs/に置いてある)。

## 決まっていること

- 名前: Vault Hunters 3 Japanese Translation (日本語化)。CurseForgeスラッグ vault-hunters-3-japanese-translation、modid vhjapanese。GitHubリポジトリ名は手元のリポジトリ群の慣行に合わせminecraft-接頭辞つき (2026-08-15の本人指示)
- 対象: modpack 3.21.7 (Forge 1.18.2-40.3.11)
- 権利の姿勢: 非公式・非収益・訳文のみ・権利者の要請があれば取り下げ (CLAUDE.md第2節)
- VaultPatcherはどの系統でも動くよう設定を二重化する。1.2系 (forge-asm、GitHubのみ) は"mods"キーとconfig/vaultpatcher_asm/を読む。現行1.5系 (CurseForgeの依存解決が配る全ローダー版。Forge 1.18.2で動作することを2026-08-17実機確認) は"modules"キーとvaultpatcher/modules/を読む。どちらもmods.tomlのmodidを持たない (TransformationService型) ため依存はCurseForgeのRequired Dependency宣言で表現し、本modはVaultPatcher不在でもlang層とconfig層が動く設計にする
- the_vaultのmodidは"the_vault" (実物jarのmods.tomlで実測)。必須依存・versionRange [0,) (実バージョン文字列がMC版接頭辞つきのため範囲指定はしない)・ordering=BEFORE (本modの構築とconfig差し込みをthe_vaultより先行させる) で宣言済み
- 実機検証の型: 機械チェックだけで「できた」と言わず、実機の2回起動とゲーム画面の視認 (クリスタルツールチップ、クエストブック) までを完了条件にする。動いていたものが動かないときは、外部要因より先に自分の生成物 (設定、リソース) のdiffを取る (2026-08-17の本人の指摘)
