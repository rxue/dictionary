INSERT INTO lexical_item (language, value, creationdate) VALUES ('en','crux','2022-04-17 00:00:00');
SET @last_lexical_item_id := LAST_INSERT_ID();
INSERT INTO explanation (id, item_id, creationdate, language, partofspeech, explanation) VALUES (NEXTVAL(explanation_id_seq), @last_lexical_item_id, '2022-04-17 00:00:00','en','N','the most important point');

