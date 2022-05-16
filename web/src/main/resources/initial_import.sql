-- en_US: crux
INSERT INTO entry (id, createdDate, entry,language, poS) VALUES(NEXT VALUE FOR entry_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'crux','en_US','N');
INSERT INTO definition VALUES(NEXT VALUE FOR definition_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'the most important point','en_US', entry_id_seq.currval);
INSERT INTO definition VALUES(NEXT VALUE FOR definition_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), '关键','zh_CN', entry_id_seq.currval);
-- en_US: take
INSERT INTO entry (id, createdDate, entry,language, poS) VALUES(NEXT VALUE FOR entry_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'take','en_US','N');
INSERT INTO definition VALUES(NEXT VALUE FOR definition_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), '看法，观点，意见','zh_CN', entry_id_seq.currval);

INSERT INTO entry (id, createdDate, entry,language, poS) VALUES(NEXT VALUE FOR entry_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'take','en_US','VT');
INSERT INTO definition VALUES(NEXT VALUE FOR definition_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), '拿','zh_CN', entry_id_seq.currval);
INSERT INTO definition VALUES(NEXT VALUE FOR definition_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), '执行','zh_CN', entry_id_seq.currval);
-- fi: palapeli
INSERT INTO entry (id, createdDate, entry,language, poS) VALUES(NEXT VALUE FOR entry_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'palapeli','fi','N');
INSERT INTO definition VALUES(NEXT VALUE FOR definition_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), '拼图','zh_CN', entry_id_seq.currval);
-- fi: jodi
INSERT INTO entry (id, createdDate, entry,language, poS) VALUES(NEXT VALUE FOR entry_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'jodi','fi','N');
INSERT INTO definition VALUES(NEXT VALUE FOR definition_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), '碘','zh_CN', entry_id_seq.currval);
-- fi: vaikku
INSERT INTO entry (id, createdDate, entry,language, poS) VALUES(NEXT VALUE FOR entry_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'vaikku','fi','N');
INSERT INTO definition VALUES(NEXT VALUE FOR definition_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), '蜡','zh_CN', entry_id_seq.currval);
-- fi: kaluste
INSERT INTO entry (id, createdDate, entry,language, poS) VALUES(NEXT VALUE FOR entry_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), 'kaluste','fi','N');
INSERT INTO definition VALUES(NEXT VALUE FOR definition_id_seq, PARSEDATETIME('17-04-2022', 'dd-MM-yyyy'), '家具','zh_CN', entry_id_seq.currval);