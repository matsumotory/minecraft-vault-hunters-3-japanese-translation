# Vault Hunters 3 Japanese Translation (日本語化)

Unofficial Japanese localization companion mod for the Vault Hunters 3rd Edition modpack (Minecraft 1.18.2 / Forge). Translations only — no original game content is redistributed. **Work in progress; not yet released.**

Minecraftのmodpack「Vault Hunters 3rd Edition」(1.18.2 / Forge) を日本語で遊べるようにする非公式の翻訳companion mod。訳文のみを含み、原作のコンテンツは一切再配布しない。**開発中で未リリース。**

## What it does / 何をするか

- Items, blocks and tooltips (lang) / アイテム・ブロック・ツールチップ (lang層)
- Skill, ability and quest descriptions (config text; original numeric values untouched) / スキル・アビリティ・クエスト説明 (config層。数値バランスには触れない)
- Hardcoded UI strings, replaced at runtime via [VaultPatcher](https://github.com/3093FengMing/VaultPatcher) (required library, not bundled) / ハードコードUI文字列 (VaultPatcherで実行時置換。同梱しない)
- In-game Patchouli guidebook / ゲーム内ガイドブック

Untranslated strings simply stay in English; the game never breaks. Some names intentionally remain in English because they double as internal identifiers.

Note: the hardcoded-UI layer activates from the **second** launch after installing (VaultPatcher reads its config at very early startup, before this mod can supply it). Everything else is translated from the first launch. / ハードコードUI層だけは導入後2回目の起動から有効になる (VaultPatcherが起動最初期に設定を読むため)。他の層は初回起動から日本語になる。

## Rights / 権利

Unofficial, non-commercial fan project. Vault Hunters and the_vault are the property of Team Iskallia (85 Entertainment AB, All Rights Reserved). This project ships translations only and will be taken down if the rights holder requests it. Parts of the translation build on the MIT-licensed community translation by [suzu2469/vault_hunter_lang_jp](https://github.com/suzu2469/vault_hunter_lang_jp) — see [NOTICE.md](NOTICE.md). Our own work is MIT-licensed ([LICENSE](LICENSE)).

非公式・非収益のファンプロジェクト。原作の権利はTeam Iskalliaに帰属し、権利者の要請があれば配布を取り下げる。帰属の正は [NOTICE.md](NOTICE.md)。
