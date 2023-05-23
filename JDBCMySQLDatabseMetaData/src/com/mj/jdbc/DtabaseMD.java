package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC App to fetch database meta data
 * @author Manoj
 *
 */
public class DtabaseMD {
	public static void main(String[] args) {
		//establish connection
		try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_bultaminds","root","")){
			//create DatabaseMetaData object
			if(con!=null)
			{
				DatabaseMetaData dbmd=con.getMetaData();
				if(dbmd!=null)
				{
					System.out.println("db s/w name :"+dbmd.getDatabaseProductName());
					System.out.println("db s/w version :"+dbmd.getDatabaseProductVersion());
					System.out.println("jdbc drive name :"+dbmd.getDriverName());
					System.out.println("jdbc driver version :"+dbmd.getDriverVersion());
					System.out.println("All numeric functions :"+dbmd.getNumericFunctions());
					System.out.println("All system functions :"+dbmd.getSystemFunctions());
					System.out.println("All string functions :"+dbmd.getStringFunctions());
					System.out.println("MAx char in table name :"+dbmd.getMaxTableNameLength());
					System.out.println("max tables in a select query :"+dbmd.getMaxTablesInSelect());
					System.out.println("max Row size :"+dbmd.getMaxRowSize());
					System.out.println("supports PL/SQL procedures ? :"+dbmd.supportsStoredProcedures());
					System.out.println("All SQL keywords :"+dbmd.getSQLKeywords());
				}
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
