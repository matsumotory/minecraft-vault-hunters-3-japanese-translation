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

INSTALL: install this project from the CurseForge App; VaultPatcher is installed automatically as a required dependency. Then launch the game once, quit, and launch it again: the hardcoded UI translations activate on the second launch, and everything else works from the first launch. Untranslated strings simply stay in English, so the game never breaks. Some names intentionally remain in English because they double as internal identifiers (ability / talent names, god names, stat labels); translating them would break the game, so we don't. Setup steps with screenshots: https://github.com/matsumotory/minecraft-vault-hunters-3-japanese-translation

TARGET VERSION: Vault Hunters 3rd Edition 3.21.7. We keep tracking modpack updates.

RIGHTS & TRANSPARENCY: Unofficial, non-commercial fan project, not affiliated with Team Iskallia. Vault Hunters and the_vault are the property of Team Iskallia (All Rights Reserved). This project ships translations only; the VaultPatcher rules necessarily contain the minimal original English strings needed for matching, and nothing more. Built on the MIT-licensed community translation by suzu2469, with credit (see the bundled NOTICE). Our own work is MIT-licensed. Before publishing, we asked in the official Discord about the proper process and publicly disclosed the full technical approach. If the rights holder asks us to stop, we will take this project down immediately.

SOURCE & FEEDBACK: https://github.com/matsumotory/minecraft-vault-hunters-3-japanese-translation

--- 日本語 ---
Vault Hunters 3rd Edition (Minecraft 1.18.2 / Forge) を日本語で遊べるようにする、非公式の翻訳modです。訳文だけを同梱していて、原作のゲームデータは再配布していません。

このmodを入れると、アイテム名、スキルやクエストの説明文、画面に直接書き込まれたUIの文字、ゲーム内ガイドブックの合計約15,000件が日本語になります。ゲームバランスの数値には一切触れません。必要なVaultPatcherは、依存modとして自動で一緒に入ります。

導入したら、ゲームを1回起動して終了し、もう一度起動してください。画面に直接書き込まれた文字の翻訳は、2回目の起動から効きます (VaultPatcherが起動のいちばん最初に設定を読むためです)。スクリーンショットつきの導入手順と確認方法は、GitHubのREADMEにあります。翻訳が無い文字列は英語のまま表示され、ゲームは壊れません。アビリティ名や神の名前など一部の名前は、ゲーム内部の識別子を兼ねているため、意図して英語のまま残しています。

非公式・非収益のファンプロジェクトです。権利者から要請があれば配布を取り下げます。誤訳や不具合は、上のGitHubリンクから日本語で報告してください。
```

## 掲載の決め

- Class: Mods。Main category: Utility & QoL (無ければCosmetic)
- Related Projects: VaultPatcherをRequired Dependencyで宣言 (アプリ導入時に自動で入る)
- ファイルのGame Versionは1.18.2 + Forge。Release種別はRelease。Changelogは[CHANGELOG.md](../CHANGELOG.md)から貼る
- Rewards Programには参加しない (非収益の明言とセット。変えるときは説明文の書き直しとセットで判断)
- ロゴはscripts/make_logo.pyで再現できる (採用はC案・羊皮紙基調)。モデレーションからロゴ内の文字が指摘されたら、文字なし版へ差し替えて再提出する
