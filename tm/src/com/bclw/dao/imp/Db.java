package com.bclw.dao.imp;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/*import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;*/

public class Db/* implements Idb*/ {
	private static DataSource dataSource;
	static{
	dataSource = new ComboPooledDataSource("userApp");
	}
	public static Connection getConn() throws SQLException { // 连接数据库
		return dataSource.getConnection();
	}
}
