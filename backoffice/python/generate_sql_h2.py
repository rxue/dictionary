import csv
import re
def _getFieldValue(originalVal:str) -> str:
    def _convertGenNextSeqValueStatement(originalSeqStatement:str) -> str:
        seqName = originalSeqStatement[originalSeqStatement.index('(')+1:originalSeqStatement.index(')')]
        return 'NEXT VALUE FOR ' + seqName
    def _convertGenCurrentSeqValueStatement(originalSeqStatement:str) -> str:
        seqName = originalSeqStatement[originalSeqStatement.index('(')+1:originalSeqStatement.index(')')]
        return seqName + '.CURRVAL'
    def _convertDateString(dateStr:str) -> str:
        return 'PARSEDATETIME(\'' + dateStr + '\',\'yyyy-MM-dd\')'
    if originalVal.startswith('NEXTVAL'):
        return _convertGenNextSeqValueStatement(originalVal)
    elif originalVal.startswith('LASTVAL'):
        return _convertGenCurrentSeqValueStatement(originalVal)
    elif re.match('\d{4}-\d{2}-\d{2}', originalVal) != None:
        return _convertDateString(originalVal)
    else:
        return '\'' + originalVal + '\'' 


def _getInsertScript(row:dict) -> str:
    def getInsertPrefix(row:dict) -> str:
        insertStatement = []
        for k in row.keys():
            splittedKeys = k.split('.') # example: lexicalitem.id => ['lexicalitem','id']
            if row[k] != '':
                if insertStatement == []:
                    insertStatement.append('INSERT INTO ' + splittedKeys[0] + ' (' + splittedKeys[1])
                else:
                    insertStatement.append(', ' + splittedKeys[1])
        insertStatement.append(') VALUES ')    
        return ''.join(insertStatement)
    def getInsertedValues(row:dict) -> str:
        valueList = []
        valueList.append('(')
        for v in row.values():
            valueList.append(',' + _getFieldValue(v))
        valueList.append(')')
        return ''.join(valueList).replace('(,','(')
    rowDictWithValidValues = {key:value for key,value in row.items() if value != ''}
    return getInsertPrefix(rowDictWithValidValues) + getInsertedValues(rowDictWithValidValues) + ';'

if __name__ == '__main__':
    with open('lexical_items.tsv', newline='') as csvfile:
        reader = csv.DictReader(csvfile, delimiter="\t")
        tableNames = []
        for row in reader:
            print(_getInsertScript(row))