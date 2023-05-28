package com.mj.jdbc;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

/**
 * JDBC App to test JDBCRowSet
 * @author Manoj
 *
 */
/*
 * No, Oracle JDBC drivers starting from version 18c do not support the
 * oracle.jdbc.rowset package, including the OracleJDBCRowSet class. 
 * The oracle.jdbc.rowset package was deprecated and removed in Oracle Database 18c.
 * 
 * If you are looking for rowset functionality in Oracle JDBC, you can use the standard javax.sql.rowset package, 
 * which provides a set of interfaces and classes for working with rowsets. 
 * The javax.sql.rowset package is part of the Java SE platform and is supported by Oracle JDBC drivers.
 */
public class JDBCRowSetDemo {
	public static void main(String[] args) {
		// Create a JdbcRowSet instance 
		try(JdbcRowSet jrowset=RowSetProvider.newFactory().createJdbcRowSet()){
			//bean style programming
			// Set the connection properties
			jrowset.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
			jrowset.setUsername("system");
			jrowset.setPassword("tiger");
			// Set the SQL query
			jrowset.setCommand("SELECT * FROM STUDENT");
			// Set the SQL query
			jrowset.execute();
			// Process the rowset
			while (jrowset.next()) {
				// Process the row data
				System.out.println(jrowset.getString(1)+" "+jrowset.getString(2)+" "+jrowset.getString(3)+" "+jrowset.getString(4));
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
