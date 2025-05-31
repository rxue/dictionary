INSERT INTO LEXICAL_ITEM (id, language, value, creation_date) values(NEXT VALUE FOR lexical_item_seq, 'en_US', 'curate', '2025-05-29');
SET @last_lexical_item_id = CURRVAL('lexical_item_seq');
INSERT INTO EXPLANATION (lexical_item_id, language, definition) values(@last_lexical_item_id, 'zh_CN', '操持（收藏品或展品的）展出');
INSERT INTO EXPLANATION (lexical_item_id, language, definition) values(@last_lexical_item_id, 'zh_CN', '（某教区的）助理牧师');
INSERT INTO EXPLANATION (lexical_item_id, language, definition) values(@last_lexical_item_id, 'en_US', 'a person authorized to conduct religious worship');
INSERT INTO LEXICAL_ITEM (id, language, value, creation_date) values(NEXT VALUE FOR lexical_item_seq, 'en_US', 'out of thin air', '2025-05-29');
SET @last_lexical_item_id = CURRVAL('lexical_item_seq');
INSERT INTO EXPLANATION (lexical_item_id, language, definition) values(@last_lexical_item_id, 'zh_CN', '突然出现：用于表示某人或某物突然而意外地出现');
