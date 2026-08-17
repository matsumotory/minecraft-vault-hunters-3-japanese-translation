# CurseForge掲載文の正

プロジェクトページに載せる文面の正。ページを更新するときはここを直してから貼る。
Summaryは英語のみ (CurseForgeの要件)。Descriptionは英語必須+日本語併記可。

## Summary

```
Unofficial Japanese localization for the Vault Hunters 3rd Edition modpack. Translations only, no original content redistributed.
```

## Description

```
Unofficial Japanese localization for the Vault Hunters 3rd Edition modpack (Minecraft 1.18.2 / Forge). Translations only. No original game content is redistributed.

WHAT GETS TRANSLATED: item, block and tooltip strings, skill / ability / quest descriptions (config text; original numeric values are kept untouched, zero balance changes), hardcoded UI strings (replaced at runtime via VaultPatcher), and the in-game guidebook. Roughly 15,000 display strings in total.

INSTALL: install this project from the CurseForge App; VaultPatcher is installed automatically as a required dependency. Untranslated strings simply stay in English, so the game never breaks. Some names intentionally remain in English because they double as internal identifiers (ability / talent names, god names, stat labels); translating them would break the game, so we don't. Note: the hardcoded-UI layer activates from the SECOND launch after installing (everything else is translated from the first launch).

TARGET VERSION: Vault Hunters 3rd Edition 3.21.7. We keep tracking modpack updates.

RIGHTS & TRANSPARENCY: Unofficial, non-commercial fan project, not affiliated with Team Iskallia. Vault Hunters and the_vault are the property of Team Iskallia (All Rights Reserved). This project ships translations only; the VaultPatcher rules necessarily contain the minimal original English strings needed for matching, and nothing more. Built on the MIT-licensed community translation by suzu2469, with credit (see the bundled NOTICE). Our own work is MIT-licensed. Before publishing, we asked in the official Discord about the proper process and publicly disclosed the full technical approach. If the rights holder asks us to stop, we will take this project down immediately.

SOURCE & FEEDBACK: https://github.com/matsumotory/minecraft-vault-hunters-3-japanese-translation

--- 日本語 ---
Vault Hunters 3rd Edition (1.18.2 / Forge) を日本語で遊べるようにする非公式の翻訳modです。訳文のみを含み、原作のコンテンツは再配布しません。アイテム名・スキルやクエストの説明・UI直書き文字列 (VaultPatcher経由)・ゲーム内ガイドブックを日本語化します (表示文字列 約15,000件)。未訳の文字列は英語のまま表示され、ゲームは壊れません。一部の名前 (アビリティ名・神名・統計ラベル) は内部識別子を兼ねるため意図的に英語のままです。UI直書き文字列の層だけは、導入後2回目の起動から有効になります。非公式・非収益のファンプロジェクトであり、権利者の要請があれば配布を取り下げます。不具合報告はGitHubへどうぞ。
```

## 掲載の決め

- Class: Mods。Main category: Utility & QoL (無ければCosmetic)
- Related Projects: VaultPatcherをRequired Dependencyで宣言 (アプリ導入時に自動で入る)
- ファイルのGame Versionは1.18.2 + Forge。Release種別はRelease。Changelogは[CHANGELOG.md](../CHANGELOG.md)から貼る
- Rewards Programには参加しない (非収益の明言とセット。変えるときは説明文の書き直しとセットで判断)
- ロゴはscripts/make_logo.pyで再現できる (採用はC案・羊皮紙基調)。モデレーションからロゴ内の文字が指摘されたら、文字なし版へ差し替えて再提出する
