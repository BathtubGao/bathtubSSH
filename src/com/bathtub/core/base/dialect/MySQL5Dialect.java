package com.bathtub.core.base.dialect;

import java.sql.Types;

import org.hibernate.dialect.MySQLDialect;
import org.hibernate.type.StringType;
import org.hibernate.type.TextType;

/**
 * MySQL数据库方言处理
 * 避免hibernate不能将这种数据类型映射到你的java类
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
