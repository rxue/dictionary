SET @current_dtime := NOW();
INSERT INTO lexical_item (language, value, creation_date, last_update_time) VALUES ('en','take','2022-04-17 00:00:00', @current_dtime);
SET @last_lexical_item_id := LAST_INSERT_ID();
INSERT INTO explanation (id, item_id, creation_date, last_update_time, language, partofspeech, explanation) VALUES (NEXTVAL(explanation_id_seq), @last_lexical_item_id, '2022-04-17 00:00:00', @current_dtime, 'en','N','the act of taking');
SET @last_explanation_id := LAST_INSERT_ID();
INSERT INTO sentence VALUES(@last_explanation_id, 'take me home, country road');
INSERT INTO explanation (id, item_id, creation_date, last_update_time, language, partofspeech, explanation) VALUES (NEXTVAL(explanation_id_seq), @last_lexical_item_id, '2022-04-17 00:00:00', @current_dtime, 'en','N','an opinion or assessment');
INSERT INTO explanation (id, item_id, creation_date, last_update_time, language, partofspeech, explanation) VALUES (NEXTVAL(explanation_id_seq), @last_lexical_item_id, '2022-04-17 00:00:00', @current_dtime, 'en','VT','to get into ones hold or possession by voluntary action');


