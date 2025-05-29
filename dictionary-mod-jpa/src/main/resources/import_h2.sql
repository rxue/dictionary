INSERT INTO LEXICAL_ITEM (id, language, value, creation_date) values(NEXT VALUE FOR lexical_item_seq, 'en_US', 'curate', '2025-05-29');
SET @last_lexical_item_id = CURRVAL('lexical_item_seq');
INSERT INTO EXPLANATION (lexical_item_id, language, definition) values(@last_lexical_item_id, 'zh_CN', '操持（收藏品或展品的）展出');
