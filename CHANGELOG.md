# CHANGELOG

## 3.21.7-ja.1 (2026-08-16)

初回リリース。対象はVault Hunters 3rd Edition 3.21.7 (Minecraft 1.18.2 / Forge 40.3.11)。

- lang層 2,368キー (the_vault 2,359 + minecraft 9) をmodのassetsとして同梱。リソースパックの手動有効化が不要
- config層 21ファイル (約7,900文字列) を起動時に手元のconfigへ差し込み。数値バランスには一切触れず、差し込み前にconfig/vhjapanese/backup/へ自動バックアップ
- ハードコードUI層 約3,500置換をVaultPatcher (Required Library) へ供給。導入後2回目の起動から有効
- ゲーム内ガイドブックのja_jpを配置
- 未訳の文字列は英語のまま表示される (正常な動作)。一部の名前は内部識別子を兼ねるため意図して英語のまま
