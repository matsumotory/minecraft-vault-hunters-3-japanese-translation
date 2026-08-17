# Vault Hunters 3 Japanese Translation

Unofficial Japanese localization companion mod for the Vault Hunters 3rd Edition modpack (Minecraft 1.18.2 / Forge). It ships translations only and never redistributes original game content.

**Downloads**: [GitHub Releases](https://github.com/matsumotory/minecraft-vault-hunters-3-japanese-translation/releases) / CurseForge (link will be added after review)

**日本語のドキュメントはこちら**: [README.md](README.md)

## What this mod does

Vault Hunters keeps most of its skill and quest descriptions in mod specific places, so switching the Minecraft language to Japanese leaves most of the text in English. This mod translates roughly 15,000 display strings into Japanese:

- Item, block and tooltip strings
- Skill, ability and quest descriptions (config text; numeric balance values are never touched)
- Hardcoded UI strings (replaced at runtime by the VaultPatcher mod)
- The in-game guidebook

## Requirements

- The Vault Hunters 3rd Edition modpack by Iskall85Team, available for free on CurseForge. This mod does nothing on its own
- VaultPatcher (by 3093FengMing, GPLv3) handles the hardcoded string replacement. It is declared as a required dependency and the CurseForge App installs it automatically; it is not bundled in our jar

## Install

1. In the CurseForge App, open your Vault Hunters Third Edition profile and click "Add More Content"
2. Search for "Vault Hunters 3 Japanese Translation" and install it. VaultPatcher comes along automatically
3. Launch the game once. On this first launch the mod places the translation data into the modpack
4. Quit the game and launch it again. The hardcoded UI translations activate on this second launch; everything else works from the first launch
5. If the game is not in Japanese yet, open Options, then Language, and select 日本語 (日本)

Manual install: download `vhjapanese-*.jar` from [Releases](https://github.com/matsumotory/minecraft-vault-hunters-3-japanese-translation/releases) and the Forge 1.18.2 ASM build of [VaultPatcher](https://www.curseforge.com/minecraft/mc-mods/vaultpatcher), put both jars into the `mods` folder of the instance, then do the same two launches.

## Verify that it works

The title menu should show Japanese labels, and the quest book should look like this:

![Quest book in Japanese](docs/images/quest-book-japanese.png)

## Troubleshooting

1. The title menu is still in English: the Minecraft language is not set to Japanese. Select 日本語 (日本) in Options, then Language
2. Items and quests are in Japanese but stat labels and some buttons are not: you are still on the first launch. Quit and launch again
3. Everything is in English after a manual install: check that both `vhjapanese-*.jar` and `vaultpatcher-*.jar` are in the `mods` folder
4. Ability names (such as Fireball), god names (such as Velara) and six stat labels (such as Armor) stay in English: this is intentional. Those names double as internal identifiers, and translating them breaks the game (verified on a real instance)
5. Still broken: report it on [GitHub Issues](https://github.com/matsumotory/minecraft-vault-hunters-3-japanese-translation/issues), in English or Japanese. Attaching `logs/latest.log` from the instance helps a lot

## Behavior notes

- Untranslated strings simply stay in English; the game never breaks
- On launch, the mod backs up the original config files to `config/vhjapanese/backup/` before merging translations. To revert, remove the mod and copy the backup files back into `config/the_vault/`

## Rights, attribution and takedown

- Vault Hunters and the_vault are the property of Team Iskallia (85 Entertainment AB), All Rights Reserved. This is an unofficial, non-commercial fan project and is not affiliated with Team Iskallia
- This project does not copy original game content. The repository and the distributed files contain Japanese translations plus only the minimal references technically required to apply them: lang key names, config JSON paths, and the original English strings that VaultPatcher needs in order to match its replacement targets
- Parts of the translation build on the MIT licensed community translation by [suzu2469/vault_hunter_lang_jp](https://github.com/suzu2469/vault_hunter_lang_jp). The detailed provenance breakdown, including exactly how much comes from the prior work (about 17 percent) and how much is our own from-scratch translation, is in [NOTICE.md](NOTICE.md), which is written in both Japanese and English
- The mod code and the translations written by matsumotory are MIT licensed ([LICENSE](LICENSE))

### For the rights holder

If you are a member of Team Iskallia or represent the rights holder, and you want this project changed or taken down, please open an issue on [GitHub Issues](https://github.com/matsumotory/minecraft-vault-hunters-3-japanese-translation/issues) or contact [@matsumotory](https://github.com/matsumotory). We will comply promptly and without dispute. Before publishing, we asked about the proper process in the official Vault Hunters Discord and publicly disclosed the full technical approach there.

## Reporting issues

Bug reports and mistranslation reports are welcome on [GitHub Issues](https://github.com/matsumotory/minecraft-vault-hunters-3-japanese-translation/issues), in English or Japanese.
