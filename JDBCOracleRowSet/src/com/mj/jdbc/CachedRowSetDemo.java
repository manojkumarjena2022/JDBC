package com.mj.jdbc;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
/**
 * JDBC App to test CachedRowSet
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
public class CachedRowSetDemo {
	public static void main(String[] args) {
		// Create a JdbcRowSet instance 
		try(CachedRowSet crowset=RowSetProvider.newFactory().createCachedRowSet()){
			//bean style programming
			// Set the connection properties
			crowset.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
			crowset.setUsername("system");
			crowset.setPassword("tiger");
			// Set the SQL query
			crowset.setCommand("SELECT * FROM STUDENT");
			// Set the SQL query
			crowset.execute();
			// Process the rowset
			while (crowset.next()) {
				// Process the row data
				System.out.println(crowset.getString(1)+" "+crowset.getString(2)+" "+crowset.getString(3)+" "+crowset.getString(4));
			}
			System.out.println("Stop db s/w from service.msc");
			Thread.sleep(80000);
			crowset.absolute(3);
			crowset.updateFloat(4, 30);
			crowset.updateRow();
			System.out.println("offline modification happened");
			System.out.println("start db s/w from service.msc");
			Thread.sleep(80000);
			crowset.acceptChanges();
			while (crowset.next()) {
				System.out.println(crowset.getString(1)+" "+crowset.getString(2)+" "+crowset.getString(3)+" "+crowset.getString(4));
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
