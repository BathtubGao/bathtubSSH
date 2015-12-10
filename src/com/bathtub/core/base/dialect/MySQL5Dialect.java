package com.bathtub.core.base.dialect;

import java.sql.Types;

import org.hibernate.dialect.MySQLDialect;
import org.hibernate.type.StringType;
import org.hibernate.type.TextType;

/**
 * MySQL���ݿⷽ�Դ���
 * ����hibernate���ܽ�������������ӳ�䵽���java��
 *
 */
public class MySQL5Dialect extends MySQLDialect 
{
	public MySQL5Dialect() {
        super();
        registerHibernateType(Types.LONGVARCHAR, StringType.INSTANCE.getName());
        registerHibernateType(Types.LONGVARCHAR, TextType.INSTANCE.getName());
    }

    protected void registerVarcharTypes() {
		registerColumnType( Types.VARCHAR, "longtext" );
		registerColumnType( Types.VARCHAR, 16777215, "mediumtext" );
		registerColumnType( Types.VARCHAR, 65535, "varchar($l)" );
		
	}
}
