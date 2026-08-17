# Vault Hunters 3 Japanese Translation (日本語化)

Minecraftのmodpack「Vault Hunters 3rd Edition」(Minecraft 1.18.2 / Forge) を日本語で遊べるようにする、非公式の翻訳modです。訳文だけを同梱していて、原作のゲームデータは再配布していません。

**ダウンロード**: [GitHub Releases](https://github.com/matsumotory/minecraft-vault-hunters-3-japanese-translation/releases) / CurseForge (審査が終わりしだいリンクを載せます)

## これは何ですか

Vault Huntersは、スキルやクエストの説明文の多くをmod独自の場所に持っています。そのため、Minecraftの言語設定を日本語にしても、大部分が英語のまま残ります。このmodを入れると、次の表示文字列 約15,000件が日本語になります。

- アイテム名、ブロック名、ツールチップ
- スキル、アビリティ、クエストの説明文 (ゲームバランスの数値には一切触れません)
- 画面に直接書き込まれたUIの文字 (VaultPatcherというmodが実行時に置き換えます)
- ゲーム内ガイドブック

## 入れかた

### CurseForgeアプリで入れる (おすすめ)

1. Vault Hunters Third Editionのプロファイルで「Add More Content」を開き、「Vault Hunters 3 Japanese Translation」を検索して追加します
2. 必要なVaultPatcherは、依存modとして自動で一緒に入ります
3. ゲームを起動し、言語設定が「日本語 (日本)」になっているか確認します

### 手動で入れる

1. [GitHub Releases](https://github.com/matsumotory/minecraft-vault-hunters-3-japanese-translation/releases) から `vhjapanese-*.jar` をダウンロードします
2. [VaultPatcher](https://www.curseforge.com/minecraft/mc-mods/vaultpatcher) のForge 1.18.2向けASM版もダウンロードします
3. 2つのjarを、インスタンスの `mods` フォルダへ入れます

## 先に知っておいてほしいこと

- VaultPatcherは、ゲーム起動のいちばん最初に翻訳の設定を読みます。そのため、画面に直接書き込まれたUIの文字だけは、導入後2回目の起動から日本語になります。それ以外は初回の起動から日本語です
- 翻訳が無い文字列は英語のまま表示されます。表示が英語に戻るだけで、ゲームは壊れません
- アビリティ名 (Fireballなど)、神の名前 (Velaraなど)、統計画面の6つのラベル (Armorなど) は、ゲーム内部の識別子を兼ねているため、意図して英語のまま残しています。ここを翻訳するとゲームが起動しなくなることを、実機で確認したうえでの判断です
- このmodは起動時に、書き換える前のconfigファイルを `config/vhjapanese/backup/` へ自動で保存します。元に戻したいときは、modを外してから、このバックアップのファイルを `config/the_vault/` へ書き戻してください

## 誤訳や不具合を見つけたら

[GitHub Issues](https://github.com/matsumotory/minecraft-vault-hunters-3-japanese-translation/issues) へ日本語で報告してください。誤訳は「どの画面の」「どの文字か」を書いてもらえると、早く直せます。

## 権利について

- Vault Huntersとthe_vaultは、Team Iskallia (85 Entertainment AB) の著作物です (All Rights Reserved)。このmodは非公式・非収益のファンプロジェクトで、原作のデータを複製していません。権利者から要請があれば、配布を取り下げます
- 日本語訳の一部は、MITライセンスの先行翻訳 [suzu2469/vault_hunter_lang_jp](https://github.com/suzu2469/vault_hunter_lang_jp) を土台にしています。どの部分がどれだけ先行翻訳に由来するかは、[NOTICE.md](NOTICE.md) の表に書いてあります
- このmodの実装と、当方が作成した訳文は、MITライセンスです ([LICENSE](LICENSE))

## English summary

Unofficial Japanese localization companion mod for the Vault Hunters 3rd Edition modpack (Minecraft 1.18.2 / Forge). Translations only; no original game content is redistributed. Install it from CurseForge and VaultPatcher will be pulled in automatically as a required dependency. The hardcoded UI layer activates from the second launch; everything else is translated from the first launch. Untranslated strings simply stay in English. Non-commercial fan project; we will take it down if the rights holder requests it. Our own work is MIT licensed. Parts build on the MIT licensed community translation by suzu2469 (see [NOTICE.md](NOTICE.md)).
