# Design Flaw in the *jpa* Project

In the unidriectional one-to-many association, i.e. from One-`LexicalItem`-to-manay-`Explanation`, when the [foreign key - `lexicalItemID` - is defined explicitly in the `Explanation` entity](https://github.com/rxue/dictionary/commit/0ebf29eea031f309ef784fed61e8b52a3c8cbf5e), it is kinda against the intention of *unidirectional*, say the target of *many* should not have any knowledge about the *one* (`LexicalItem` in this case) at least in Java code.

Moreover, by defining foreign key explicitly in the `Explanation` entity, when inserting a new `LexicalItem` entity with `Explantions` collections, the *Hibernate* framework is not able to cascade the insertion of `Explanation` collection when inserting. This inconvinience causes more code and effort on persisting the `LexicalItem` entity, esp. it is compulsory to set the `lexicalItemId` of the `Explanation` entity manually and thus an extra `setLexicalItemId` is needed.

Both cases above are against the intention of *unidiractional association*, say except for the database level, where the `explanation` table has to have a foreign key column `lexical_item_id`, the target of *many* party should not have any knowledge about the *one* party (`LexialItem` in this casse) in the Java code.


