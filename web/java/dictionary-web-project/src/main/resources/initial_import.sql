-- en: crux
INSERT INTO lexicalitem (id, createdDate, value, language) VALUES(NEXT VALUE FOR item_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'crux','en');
INSERT INTO explanation (id, item_id, createdDate, language, partOfSpeech, explanation) VALUES(NEXT VALUE FOR explanation_id_seq, item_id_seq.currval, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'en', 'N', 'the most important point');
INSERT INTO explanation (id, item_id, createdDate, language, partOfSpeech, explanation) VALUES(NEXT VALUE FOR explanation_id_seq, item_id_seq.currval, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'zh_CN', 'N', '关键');
-- en: take
INSERT INTO lexicalitem (id, createdDate, value, language) VALUES(NEXT VALUE FOR item_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'take','en');
INSERT INTO explanation (id, item_id, createdDate, language, partOfSpeech, explanation) VALUES(NEXT VALUE FOR explanation_id_seq, item_id_seq.currval, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'zh_CN', 'N', '看法，观点，意见');

INSERT INTO explanation (id, item_id, createdDate, language, partOfSpeech, explanation) VALUES(NEXT VALUE FOR explanation_id_seq, item_id_seq.currval, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'zh_CN', 'VT', '拿');
INSERT INTO explanation (id, item_id, createdDate, language, partOfSpeech, explanation) VALUES(NEXT VALUE FOR explanation_id_seq, item_id_seq.currval, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'zh_CN', 'VT', '执行');
-- en: manifest
--INSERT INTO lexicalitem (id, createdDate, value, language, poS) VALUES(NEXT VALUE FOR item_id_seq, PARSEDATETIME('09-10-2022', 'dd-MM-yyyy'), 'manifest','en','VT');
--INSERT INTO explanation VALUES(NEXT VALUE FOR explanation_id_seq, PARSEDATETIME('21-10-2022', 'dd-MM-yyyy'), '显示,证实,表露','zh_CN', item_id_seq.currval);
-- en: me
--INSERT INTO lexicalitem (id, createdDate, value, language, poS) VALUES(NEXT VALUE FOR item_id_seq, PARSEDATETIME('09-10-2022', 'dd-MM-yyyy'), 'me','en','PRON');
--INSERT INTO explanation VALUES(NEXT VALUE FOR explanation_id_seq, PARSEDATETIME('09-10-2022', 'dd-MM-yyyy'), '我(宾格)','zh_CN', item_id_seq.currval);
-- en: media
--INSERT INTO lexicalitem (id, createdDate, value, language, poS) VALUES(NEXT VALUE FOR item_id_seq, PARSEDATETIME('09-10-2022', 'dd-MM-yyyy'), 'media','en','N');
--INSERT INTO explanation VALUES(NEXT VALUE FOR explanation_id_seq, PARSEDATETIME('09-10-2022', 'dd-MM-yyyy'), '媒体','zh_CN', item_id_seq.currval);
-- en: recluse
--INSERT INTO lexicalitem (id, createdDate, value, language, poS) VALUES(NEXT VALUE FOR item_id_seq, PARSEDATETIME('20-10-2022', 'dd-MM-yyyy'), 'recluse','en','N');
--INSERT INTO explanation VALUES(NEXT VALUE FOR explanation_id_seq, PARSEDATETIME('20-10-2022', 'dd-MM-yyyy'), '隐士','zh_CN', item_id_seq.currval);

-- fi: palapeli
--INSERT INTO lexicalitem (id, createdDate, value, language, poS) VALUES(NEXT VALUE FOR item_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'palapeli','fi','N');
--INSERT INTO explanation VALUES(NEXT VALUE FOR explanation_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), '拼图','zh_CN', item_id_seq.currval);
-- fi: jodi
--INSERT INTO lexicalitem (id, createdDate, value, language, poS) VALUES(NEXT VALUE FOR item_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'jodi','fi','N');
--INSERT INTO explanation VALUES(NEXT VALUE FOR explanation_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), '碘','zh_CN', item_id_seq.currval);
-- fi: vaikku
--INSERT INTO lexicalitem (id, createdDate, value, language, poS) VALUES(NEXT VALUE FOR item_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'vaikku','fi','N');
--INSERT INTO explanation VALUES(NEXT VALUE FOR explanation_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), '蜡','zh_CN', item_id_seq.currval);
-- fi: kaluste
--INSERT INTO lexicalitem (id, createdDate, value,language, poS) VALUES(NEXT VALUE FOR item_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'kaluste','fi','N');
--INSERT INTO explanation VALUES(NEXT VALUE FOR explanation_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), '家具','zh_CN', item_id_seq.currval);