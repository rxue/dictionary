WITH merged_lexical_item AS (
    MERGE INTO lexical_item
    USING (SELECT 'en' AS language,'test' AS value) AS u
        ON lexical_item.language = u.language AND lexical_item.value = u.value
    WHEN MATCHED THEN
        UPDATE SET last_update_time = '2025-08-17 00:00:12'
    WHEN NOT MATCHED THEN
        INSERT (language,value,last_update_time) VALUES('en','test','2025-08-17 00:00:12')
    RETURNING id)
MERGE INTO explanation
USING (SELECT id AS lexical_item_id,'en' AS language,'N' AS partofspeech,'1' AS serialnumber FROM merged_lexical_item) AS u
    ON explanation.lexical_item_id = u.lexical_item_id AND explanation.language = u.language AND explanation.partofspeech = u.partofspeech AND explanation.serialnumber = u.serialnumber
WHEN MATCHED THEN
    UPDATE SET definition = 'test definition',last_update_time = '2025-08-14 08:00:00'
WHEN NOT MATCHED THEN
    INSERT (lexical_item_id,language,partofspeech,serialnumber,definition,last_update_time) VALUES(lexical_item_id,'en','N','1','test definition','2025-08-14 08:00:00');