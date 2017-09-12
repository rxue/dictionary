package ruixue.dependency.hibernate5.fix;

import org.hibernate.dialect.HSQLDialect;
/**
 * In case of using embedded in-memory HSQLDB in the development or test phase,
 * and the hibernate property - hibernate.hbm2ddl.auto - is set to - create/create-drop, 
 * as the first step of Hibernate's automated SQL execution process is to drop the 
 * existing constraint. However, the HSQL syntax of dropping constraint - 
 * alter table <table name> drop constraint; - doesn't contain 'if exists', and 
 * initially the HSQL in-memory database doesn't contain the constraint at all. 
 * Therefore, the 'drop constraint' statement is bound to cause WARN message in 
 * the log. 
 * 
 * In order to remove the WARN message caused by the 'drop constraint', the HSQLDialect
 * is overrided by this class:
 * 	- 'drop constraint' syntax is disabled
 *  - in 'drop table' syntax, add 'if exists' before table name and add 'CASCADE'
 * 
 * Usage: set the value of Hibernate property - hibernate.dialect - with this class
 *  
 * reference: https://hibernate.atlassian.net/browse/HHH-7002
 * @author Baptiste Mathus
 */
public class MyHSQLDialect extends HSQLDialect {
	//drop constraint syntax disabled
	@Override
	public boolean dropConstraints()
	{
		return false;
	}

	@Override
	public boolean supportsIfExistsBeforeTableName()
	{
		return true;
	}

	@Override
	public boolean supportsIfExistsAfterTableName()
	{
		return false;
	}

	@Override
	public String getCascadeConstraintsString()
	{
		return " CASCADE ";
	}
}
