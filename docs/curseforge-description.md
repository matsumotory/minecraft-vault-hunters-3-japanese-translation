# CurseForge掲載文の正

プロジェクトページに載せる文面の正。ページを更新するときはここを直してから貼る。
Summaryは英語のみ (CurseForgeの要件)。Descriptionは英語必須+日本語併記可。

書式の決め: Descriptionは見出しと箇条書きと太字で構造を作る (エディタはリッチテキストで、markdownの貼り付けに対応)。【】などの記号でテキストを装飾しない (装飾記号は書式が無い場所の代用にすぎず、書式がある場所で使うと素人くさい。2026-08-17の本人の指摘)。

## Summary

```
Unofficial Japanese localization for the Vault Hunters 3rd Edition modpack. Translations only, no original content redistributed.
```

## Description

日本語主体+英語併記。主読者が日本人のため、日本語の情報量を英語と同等以上にする。

```
Vault Hunters 3rd Edition (Minecraft 1.18.2 / Forge) を日本語で遊べるようにする、非公式の翻訳modです。訳文だけを同梱していて、原作のゲームデータは再配布していません。

## 翻訳されるもの

合計 約14,400件の表示文字列が日本語になります。

- アイテム名、ブロック名、ツールチップ
- スキル、アビリティ、クエストの説明文 (configのテキスト。数値バランスには一切触れません)
- 画面に直接書き込まれたUIの文字 (VaultPatcherが実行時に置き換えます)
- ゲーム内ガイドブック

## 入れかた

1. プロファイルの設定で「コンテンツ管理」を許可します (新しいプロファイルでは最初はオフです)
2. CurseForgeアプリでこのプロジェクトをインストールします。必要なVaultPatcherは、依存modとして自動で一緒に入ります
3. ゲームを1回起動します。そのあいだに、Options から Language を開いて「日本語 (日本)」を選びます
4. ゲームをいったん終了して、もう一度起動します。画面に直接書き込まれた文字の翻訳は、この2回目の起動から効きます (VaultPatcherが起動のいちばん最初に設定を読むためです)。それ以外は初回の起動から日本語です

スクリーンショットつきの導入手順と確認方法は、GitHubのREADMEにあります。

## 翻訳されない文字列について

翻訳が無い文字列は英語のまま表示されるだけで、ゲームは壊れません。アビリティ名・神の名前・統計画面の一部のラベルは、ゲーム内部の識別子を兼ねているため、意図して英語のまま残しています (翻訳するとゲームが起動しなくなることを実機で確認したうえでの判断です)。

## 対象バージョン

Vault Hunters 3rd Edition 3.21.7。modpackの更新に追随していきます。

## 権利と透明性

非公式・非収益のファンプロジェクトで、Team Iskalliaとは無関係です。Vault Huntersとthe_vaultはTeam Iskallia (All Rights Reserved) の著作物で、このmodは訳文だけを配布しています (VaultPatcherの置換設定に、置換対象の特定に必要な最小限の英語原文だけを含みます)。日本語訳の一部は、MITライセンスの先行翻訳 (suzu2469版) を帰属表示つきで土台にしています (内訳は同梱のNOTICEにあります)。このmod自体の実装と訳文はMITライセンスです。公開前に公式Discordで正しい手続きを尋ね、技術的な方式も開示しました。権利者から要請があれば、配布をすぐに取り下げます。

## ソースと不具合報告

https://github.com/matsumotory/minecraft-vault-hunters-3-japanese-translation (誤訳や不具合の報告は日本語でどうぞ)

---

## English

Unofficial Japanese localization for the Vault Hunters 3rd Edition modpack (Minecraft 1.18.2 / Forge). Translations only. No original game content is redistributed.

### What gets translated

Roughly 14,400 display strings in total.

- Item, block and tooltip strings
- Skill, ability and quest descriptions (config text; original numeric values are kept untouched, zero balance changes)
- Hardcoded UI strings (replaced at runtime via VaultPatcher)
- The in-game guidebook

### Install

1. Enable content management for the profile (freshly installed profiles have it off)
2. Install this project from the CurseForge App; VaultPatcher is installed automatically as a required dependency
3. Launch the game once, and set the language to 日本語 (日本) in Options / Language
4. Quit and launch again: the hardcoded UI translations activate on the second launch, and everything else works from the first launch

Setup steps with screenshots: https://github.com/matsumotory/minecraft-vault-hunters-3-japanese-translation

### Untranslated strings

Untranslated strings simply stay in English, so the game never breaks. Some names intentionally remain in English because they double as internal identifiers (ability / talent names, god names, stat labels); translating them would break the game, so we don't.

### Target version

Vault Hunters 3rd Edition 3.21.7. We keep tracking modpack updates.

### Rights and transparency

Unofficial, non-commercial fan project, not affiliated with Team Iskallia. Vault Hunters and the_vault are the property of Team Iskallia (All Rights Reserved). This project ships translations only; the VaultPatcher rules necessarily contain the minimal original English strings needed for matching, and nothing more. Built on the MIT-licensed community translation by suzu2469, with credit (see the bundled NOTICE). Our own work is MIT-licensed. Before publishing, we asked in the official Discord about the proper process and publicly disclosed the full technical approach. If the rights holder asks us to stop, we will take this project down immediately.

### Source and feedback

https://github.com/matsumotory/minecraft-vault-hunters-3-japanese-translation
```

## 掲載の決め

- Class: Mods。Main category: Utility & QoL (無ければCosmetic)
- Related Projects: VaultPatcherをRequired Dependencyで宣言 (アプリ導入時に自動で入る)
- ファイルのGame Versionは1.18.2 + Forge。Release種別はRelease。Changelogは[CHANGELOG.md](../CHANGELOG.md)から貼る
- Rewards Programには参加しない (非収益の明言とセット。変えるときは説明文の書き直しとセットで判断)
- ロゴはscripts/make_logo.pyで再現できる (採用はC案・羊皮紙基調)。モデレーションからロゴ内の文字が指摘されたら、文字なし版へ差し替えて再提出する
