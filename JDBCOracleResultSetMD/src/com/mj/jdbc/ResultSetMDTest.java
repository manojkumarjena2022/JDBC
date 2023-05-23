package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC App to fetch resilt set meta data
 * @author Manoj
 *
 */
public class ResultSetMDTest {
	public static void main(String[] args) {
		//establish connection
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("SELECT SNO,SNAME,SADD,AVG FROM STUDENT")){
			//create ResultSetMetaData object
			if (rs!=null) {
				ResultSetMetaData rsmd=rs.getMetaData();
				if (rsmd!=null) {
					int colCount=rsmd.getColumnCount();
					//print column names
					for (int i = 1; i <= colCount; i++) {
						System.out.print(rsmd.getColumnName(i)+" ");
					}
					System.out.println();
					//print column data type name
					for (int i = 1; i <= colCount; i++) {
						System.out.print(rsmd.getColumnTypeName(i)+" ");
					}
					System.out.println();
					//process result set records
					while (rs.next()) {
						for (int i = 1; i <= colCount; i++) {
							System.out.print(rs.getString(i)+" ");
						}
						System.out.println();
					}
				}
			}

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
