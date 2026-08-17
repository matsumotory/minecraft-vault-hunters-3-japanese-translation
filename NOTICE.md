# NOTICE (権利と帰属)

## 原作について

Vault Huntersとthe_vaultは、Team Iskallia (85 Entertainment AB) の著作物です (All Rights Reserved)。本リポジトリと配布物は、原作の複製 (jar、mod本体、config原文の丸ごと、英語原文のコーパス) を含みません。含んでいるのは、日本語の訳文と、翻訳の適用に機能上欠かせない最小限の参照 (langのキー名、configのJSONパス、置換対象を特定するための原文文字列) だけです。権利者から要請があれば、配布を取り下げます。

## 日本語訳の由来 (どこまでが先行翻訳で、どこからがmatsumotoryの訳か)

この翻訳は、このmodの作者であるmatsumotory (まつもとりー) が作りました。MITライセンスで公開されている先行プロジェクト [suzu2469/vault_hunter_lang_jp](https://github.com/suzu2469/vault_hunter_lang_jp) を土台のひとつにしています。ただし、先行翻訳をそのまま取り込んだわけではありません。誤解のないように、作りかたから説明します。

### 作りかた

1. まず、先行翻訳を参照せずに、全文字列を英語の原文から翻訳しました。これを白紙翻訳と呼びます
2. 次に、白紙翻訳と先行翻訳を1件ずつ機械的に突き合わせました。訳が食い違う箇所は両方を比較して、より良いと判断した方を採用しました
3. 採用した箇所も含めて、全文字列を監査の対象にし、誤訳と不自然な直訳調は理由を記録したうえで直しました

### 分類の意味

比較の結果は、次の3つに分けて数えています。

- **先行翻訳を採用**: 白紙翻訳と先行翻訳が食い違い、比較の結果、先行翻訳の方が良いと判断して採用した箇所です。この数だけを先行翻訳由来として数えます
- **独立一致**: 白紙翻訳が、先行翻訳と同じ訳文に到達した箇所です。matsumotoryが独立に同じ結論へ着いたものなので、先行翻訳の採用には数えていません
- **matsumotoryの訳**: 比較で白紙翻訳の方を採用した箇所と、監査でmatsumotoryが書き直した箇所と、先行翻訳が存在しない範囲をmatsumotoryが新規に翻訳した箇所です

### 層ごとの実測 (2026-08-11の機械集計)

| 対象 | 全体 | 先行翻訳を採用 | 独立一致 | matsumotoryの訳 |
|---|---|---|---|---|
| アイテム名などのlang文字列 (the_vault) | 2,359キー | 423件 (17.9%) | 1,001件 (42.4%) | 935件 (39.6%) |
| lang文字列 (minecraft由来の9キー) | 9キー | 9件 (少数のため白紙比較は未実施の暫定扱い) | - | - |
| スキルやクエストの説明文 (config) | 8,036文字列 | 2,019件 (25.1%) | - | 6,017件 (74.9%)。内訳は、先行翻訳より白紙翻訳が良く置き換えた2,589件と、先行翻訳が存在せず新規に翻訳した3,428件 |
| ゲーム内ガイドブックの表示文 | 419文 | 39件 (9.3%) | 131件 (31.3%) | 249件 (59.4%) |
| 画面に直接書き込まれたUIの文字 (約3,600置換) | 約3,600 | 0件 | - | 全件。先行プロジェクトが扱っていない範囲で、すべてmatsumotoryの新規翻訳です |
| mod実装、生成と検証のプログラム、文書 | - | - | - | すべてmatsumotoryの作成です |

### まとめ

表示文字列の全体 約14,900件のうち、先行翻訳の採用は約2,490件で、全体の約17%です。残る約83%はmatsumotoryの白紙翻訳です (そのうち約1,100件は、先行翻訳と同じ訳文に独立に到達した独立一致です)。

数の割合とは別に、suzu2469版には用語の土台としての大きな貢献があります。ヴォールト、レリック、アーティファクトのような中核の訳語の一部は、suzu2469版で確立した訳を、matsumotoryの検証を経て引き継いだものです。この敬意を、本表とMITライセンスの帰属表示で明示します。

集計は、テキスト比較の機械判定で行いました。

## 同梱しないもの

- VaultPatcher (作者 3093FengMing、GPLv3) は同梱しません。Required Dependencyとして宣言していて、配布プラットフォームが利用者の環境へ自動で導入します
- 原作がjarに同梱している他言語の訳文は、訳語を判断するときの参照にだけ使い、内容は流用していません

## matsumotoryが作成した部分のライセンス

mod実装、matsumotoryが作成した訳文、文書は、MIT Licenseです ([LICENSE](LICENSE))。

## English

### About the original work

Vault Hunters and the_vault are the property of Team Iskallia (85 Entertainment AB), All Rights Reserved. This repository and the distributed files contain no copies of the original work (no jars, no mods, no full original config files, no English text corpus). They contain the Japanese translations plus only the minimal references technically required to apply them: lang key names, config JSON paths, and the original English strings that VaultPatcher needs in order to match its replacement targets. If the rights holder requests it, we will take the distribution down. See "For the rights holder" in [README.en.md](README.en.md) for the contact route.

### Where the translation comes from

The translation was written by matsumotory, the author of this mod. It builds on the MIT licensed prior project [suzu2469/vault_hunter_lang_jp](https://github.com/suzu2469/vault_hunter_lang_jp), but the prior work was not simply imported. The process was:

1. First, every string was translated from the English original without looking at the prior translation (the from-scratch translation)
2. Then the from-scratch translation and the prior translation were compared string by string; where they differed, the better one was adopted
3. Every string, including the adopted ones, went through an audit, and mistranslations and awkward literal wording were fixed with the reasons recorded

The results are counted in three categories:

- **Adopted from the prior work**: the two translations differed and the prior one was judged better. Only these count as coming from the prior work
- **Independent match**: the from-scratch translation arrived at the same wording as the prior one. These are not counted as adoption, because matsumotory reached the same conclusion independently
- **matsumotory's translation**: strings where the from-scratch translation won the comparison, strings rewritten in the audit, and strings the prior work never covered

Measured breakdown (machine counted on 2026-08-11):

| Target | Total | Adopted from prior work | Independent match | matsumotory's translation |
|---|---|---|---|---|
| lang strings such as item names (the_vault) | 2,359 keys | 423 (17.9%) | 1,001 (42.4%) | 935 (39.6%) |
| lang strings (9 minecraft keys) | 9 keys | 9 (provisional; too few for the comparison) | - | - |
| Skill and quest descriptions (config) | 8,036 strings | 2,019 (25.1%) | - | 6,017 (74.9%): 2,589 where the from-scratch translation replaced the prior one, plus 3,428 newly translated strings the prior work never covered |
| In-game guidebook display text | 419 | 39 (9.3%) | 131 (31.3%) | 249 (59.4%) |
| Hardcoded UI strings (about 3,600 replacements) | about 3,600 | 0 | - | all of them; the prior project did not cover this layer |
| Mod code, build and validation tooling, documents | - | - | - | all by matsumotory |

In total, out of roughly 14,900 display strings, about 2,490 (about 17 percent) are adopted from the prior work. The remaining about 83 percent is matsumotory's from-scratch translation, and about 1,100 of those independently matched the prior wording. Beyond the numbers, the prior work made a large contribution as the foundation of the terminology: several core terms were carried over from suzu2469's established wording after verification. We state this respect here and in the MIT attribution.

### Not bundled

- VaultPatcher (by 3093FengMing, GPLv3) is not bundled. It is declared as a required dependency, and the distribution platform installs it into the user's environment
- The other language translations bundled in the original jar were used only as references for terminology decisions; none of their content is reused

### License of matsumotory's work

The mod code, the translations written by matsumotory, and the documents are MIT licensed ([LICENSE](LICENSE)).
