# Repository Design with JPA
## Interface design: `LexicalItemRepository` and `ExplanationRepository`

Think first from the frontend point of view:

**CREATE**
 * ~add a full *lexical entry* including *lexical item*, *defintion*, *part of speech*, *example sentences* etc.~
 * ~add a single *explanationEntity* to an existing *lexical entry*~

**READ** 

 * Given an keyword with value, language and definition language, get all the possible results along with all meanings
 * ~Given a *lexical item id* get the all the explanationEntities~ (no real case realized yet)

**UPDATE**

 * ~Given a *lexical item*, update it (explanationEntities can be added or removed)~
 * ~Given an *explanationEntity*, update it~

**DELETE**

 * Given a *lexical item* delete it (not implemented yet)
 * ~Given an *explanationEntity*, delete it~

**Unidirectional `@ManyToOne` Association (`Explanation` > `LexicalItem`)**

**CREATE**
 * add a full *lexical entry* including *lexical item*, *defintion*, *part of speech*, *example sentences* etc. : (can be done purely through `ExplanationRepository`)

**READ** 

 * Given an input, get all the possible results along with all meanings: OK (can be done purely through `ExplanationRepository`)
 * ~Given a *lexical item id* get the all the explanationEntities : OK but the query on `ExplanationRepository` is not trivial~

**UPDATE**

 * ~Given a *lexical item*, update it (explanationEntities can be added or removed) : need separate merge on both `LexicalItem` and `Explanation`~

**DELETE**

 * ~Given a *lexical item* delete it : need first `LexicalItemRepository.find`/`EntityManager.find` to search for the managed entity and thEN call `EntityManager.remove`~

~This design strategy cannot simply meet the repositories.~ 

## Current Design Conclusion on base of *prototyping* trial

After trying the bidirectional `@ManyToOne` association from `Explanation` back to `DictionaryEntry`, and then gradually tweaking back to unidirectional, unidirectional `@ManyToOne` association is eventually decided to be used

