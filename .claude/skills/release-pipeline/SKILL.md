---
name: release-pipeline
description: 新しいバージョンを配布するときの一連の手順。版上げ、ビルド、実機検証、GitHub Release、CurseForgeアップロードまで
---

# リリースの手順

新しいバージョン (訳データの更新、修正、modpack対応) を配布するときの手順。2026-08-17の3.21.7-ja.2リリースで実際に通した形が土台。バージョンの規則と公開基準はCLAUDE.mdが正。

## 0. 前提

- バージョンは「対象modpackバージョン-ja.通し番号」(CLAUDE.md第3節)。タグ名はバージョン名そのまま (例 3.21.7-ja.2)。軽量タグで統一する
- 訳文データの更新を含むリリースは、先に別の作業環境の生成スクリプトで`src/main/resources/`を再生成する (生成先にはこのリポジトリのworktreeのパスを指定する。作業環境側の手順はそちらの文書が正)
- Windowsでの再生成の罠: 直前の実行が作ったディレクトリをスキャナ等が一時的に掴み、生成スクリプト内の削除がPermissionErrorになることがある。対象ディレクトリ (assets/the_vault、assets/minecraft、assets/vhjapanese) をリトライつきで先に消してから、生成を一発で走らせる

## 1. 版上げと文書

- `gradle.properties`の`mod_version`を上げる
- `CHANGELOG.md`へ敬体で追記する。何が誰にどう影響し、何を直したかを利用者の言葉で書く (原因や仕組みは簡潔に)
- 手順や既知の問題が変わるときは、README.md、README.en.md、docs/curseforge-description.mdを同じコミットで整合させる

## 2. ビルドと実機検証

- ビルドは必ず`&&`で門にする: `./gradlew build --no-daemon && ...`。`;`で流すと失敗を見逃す
- 実機検証の型 (完了条件。機械チェックだけで「できた」と言わない):
  1. 実機インスタンスの旧jarを外し、ビルドしたjarを`mods/`へ置く。VaultPatcher層を触った変更なら、関連状態 (`config/vaultpatcher_asm/`、ゲームディレクトリ直下の`vaultpatcher/`、`config/vhjapanese/`) を白紙化してから検証する
  2. 1回目起動: ログの`[vhjapanese]`行で各層の配置を確認する。VaultPatcher設定はconfig.jsonの`mods`と`modules`の両キー、モジュール本体の新旧両位置 (`config/vaultpatcher_asm/`と`vaultpatcher/modules/`) を実ファイルで確認する
  3. 2回目起動: ハードコード層の画面視認。クリスタルツールチップ (レベル/容量/目標/テーマ/レイアウト) とクエストブックのラベル (クエスト概要/説明:/未達成) が日本語になっていること。`FATAL`が0件であること
  4. もう一度起動して冪等性 (書き込みゼロ) を確認する
- 動いていたものが動かないときは、外部要因 (VaultPatcherの版など) より先に、自分の生成物 (設定、リソース) を動いていた状態とdiffして疑う

## 3. コミットとタグ

- 公開基準の検査と`&&`直結でコミットし (CLAUDE.md第4節)、mainへff-mergeしてpushする
- リリースへ添付するjarは、実機検証に使ったその実物を使う (mainの`build/libs/`へ置く)
- タグを打ってpushする: `git tag <バージョン> <コミット> && git push origin <バージョン>`

## 4. GitHub Release (API)

gh CLIは入っていない前提で、REST APIを使う。

- トークンは`git credential fill` (protocol=https、host=github.com) の`password=`行から変数へ取り、画面に表示しない
- リリース作成: `POST https://api.github.com/repos/<owner>/<repo>/releases`。本文はCHANGELOGの当該節をそのまま使う。日本語を含むJSONはインラインで渡さずファイルに書いて`--data-binary @ファイル`で送る (インラインの日本語は化けて400になる)
- jar添付: `POST https://uploads.github.com/repos/<owner>/<repo>/releases/<リリースID>/assets?name=<jar名>` に`Content-Type: application/java-archive`で`--data-binary @jar`
- 応答のHTTPコードとhtml_url、添付のsizeがビルド物と一致することを確認する
- 公開に関わる操作は途中で許可の確認に止められることがある。止まったら本人へ明示の許可をもらってから再試行する

## 5. CurseForge (Upload API)

ファイルの追加だけAPIでできる。Descriptionの編集APIは無い。

- トークンは本人がauthors.curseforge.comのAPI Tokensで発行する (アカウント操作なので本人だけ)。チャットへ貼らずローカルの一時ファイルへ置いてもらい、パスを教えてもらって読み込む。表示しない、コミットしない。チャットへ貼られてしまったら、リリース後にトークンの作り直しを勧める
- プロジェクトIDは公開ページのAbout欄にある (このプロジェクトは1656274)
- ゲームバージョンIDを都度取得する: `GET https://minecraft.curseforge.com/api/game/versions` (ヘッダ`X-Api-Token`)。名前と型で選ぶ: 1.18.2 (Minecraft 1.18型)、Forge (Modloader型)、Client と Server (Environment型)
- metadataのJSONをファイルに書く: `changelog` (markdown。日本語主体+英語の短い要約)、`changelogType: "markdown"`、`gameVersions` (取得したID配列)、`releaseType: "release"`、`relations: {"projects": [{"slug": "vault-patcher", "type": "requiredDependency"}]}`
- アップロード: `POST https://minecraft.curseforge.com/api/projects/<ID>/upload-file` に multipart で `-F "metadata=<メタファイル"` と `-F "file=@jar"`。部品に`;type=`を付けるとcurlが(26)で失敗するので付けない
- 応答`{"id": <ファイルID>}`が受理。公開はモデレーション審査の承認後で、承認まで公開ページには出ない
- Descriptionの差し替えが必要なリリースでは、docs/curseforge-description.mdを直した上で、貼り付けは本人へ依頼する

## 6. 締め

- HANDOFF.mdを現在形へ書き直す (何を配布したか、審査待ちか、本人操作の残りは何か)
- 別の作業環境側の作業状態にも同じ現在地を引き継ぐ
