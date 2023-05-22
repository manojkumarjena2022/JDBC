package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC App to fetch records to test InSensitive ResultSet
 * @author Manoj
 *
 */
public class InsensitiveRSTest {
	public static void main(String[] args) {
		//Establish connection and create statement object
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);){
			//execute query
			if (st!=null) {
				try(ResultSet rs=st.executeQuery("SELECT SNO,SNAME,SADD,AVG FROM STUDENT");){
					//process result
					int count=0;
					if (rs!=null) {
						while (rs.next()) {
								if(count==0)
									Thread.sleep(30000);//during this sleep period modify records of db table from sqlplus/sql developer
								//rs.refreshRow(); //Unsupported feature: refreshRow
								count++;
							System.out.println("Name:"+rs.getString(2)+" Address: "+rs.getString(3)+" Avg: "+rs.getString(4));
						}
					}
				}
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
