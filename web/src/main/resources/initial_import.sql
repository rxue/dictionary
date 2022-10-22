-- en_US: crux
INSERT INTO lexicalitem (id, createdDate, value, language, poS) VALUES(NEXT VALUE FOR item_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'crux','en_US','N');
INSERT INTO explanation VALUES(NEXT VALUE FOR explanation_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'the most important point','en_US', item_id_seq.currval);
INSERT INTO explanation VALUES(NEXT VALUE FOR explanation_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), '关键','zh_CN', item_id_seq.currval);
-- en_US: take
INSERT INTO lexicalitem (id, createdDate, value, language, poS) VALUES(NEXT VALUE FOR item_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'take','en_US','N');
INSERT INTO explanation VALUES(NEXT VALUE FOR explanation_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), '看法，观点，意见','zh_CN', item_id_seq.currval);

INSERT INTO lexicalitem (id, createdDate, value, language, poS) VALUES(NEXT VALUE FOR item_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'take','en_US','VT');
INSERT INTO explanation VALUES(NEXT VALUE FOR explanation_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), '拿','zh_CN', item_id_seq.currval);
INSERT INTO explanation VALUES(NEXT VALUE FOR explanation_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), '执行','zh_CN', item_id_seq.currval);
-- en_US: me
INSERT INTO lexicalitem (id, createdDate, value, language, poS) VALUES(NEXT VALUE FOR item_id_seq, PARSEDATETIME('09-10-2022', 'dd-MM-yyyy'), 'me','en_US','PRON');
INSERT INTO explanation VALUES(NEXT VALUE FOR explanation_id_seq, PARSEDATETIME('09-10-2022', 'dd-MM-yyyy'), '我(宾格)','zh_CN', item_id_seq.currval);
-- en_US: media
INSERT INTO lexicalitem (id, createdDate, value, language, poS) VALUES(NEXT VALUE FOR item_id_seq, PARSEDATETIME('09-10-2022', 'dd-MM-yyyy'), 'media','en_US','N');
INSERT INTO explanation VALUES(NEXT VALUE FOR explanation_id_seq, PARSEDATETIME('09-10-2022', 'dd-MM-yyyy'), '媒体','zh_CN', item_id_seq.currval);
-- en_US: recluse
INSERT INTO lexicalitem (id, createdDate, value, language, poS) VALUES(NEXT VALUE FOR item_id_seq, PARSEDATETIME('20-10-2022', 'dd-MM-yyyy'), 'recluse','en_US','N');
INSERT INTO explanation VALUES(NEXT VALUE FOR explanation_id_seq, PARSEDATETIME('20-10-2022', 'dd-MM-yyyy'), '隐士','zh_CN', item_id_seq.currval);

-- fi: palapeli
INSERT INTO lexicalitem (id, createdDate, value, language, poS) VALUES(NEXT VALUE FOR item_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'palapeli','fi','N');
INSERT INTO explanation VALUES(NEXT VALUE FOR explanation_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), '拼图','zh_CN', item_id_seq.currval);
-- fi: jodi
INSERT INTO lexicalitem (id, createdDate, value, language, poS) VALUES(NEXT VALUE FOR item_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'jodi','fi','N');
INSERT INTO explanation VALUES(NEXT VALUE FOR explanation_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), '碘','zh_CN', item_id_seq.currval);
-- fi: vaikku
INSERT INTO lexicalitem (id, createdDate, value, language, poS) VALUES(NEXT VALUE FOR item_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'vaikku','fi','N');
INSERT INTO explanation VALUES(NEXT VALUE FOR explanation_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), '蜡','zh_CN', item_id_seq.currval);
-- fi: kaluste
INSERT INTO lexicalitem (id, createdDate, value,language, poS) VALUES(NEXT VALUE FOR item_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'kaluste','fi','N');
INSERT INTO explanation VALUES(NEXT VALUE FOR explanation_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), '家具','zh_CN', item_id_seq.currval);