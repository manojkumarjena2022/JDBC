package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC App to create a table in ats_bultaminds database
 * @author Manoj
 *
 */
public class CreateTable {
public static void main(String[] args) {
	Connection con=null;
	Statement st=null;
	try {
		//establish connection
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_bultaminds","root","");
		//create statement object
		if (con!=null) {
			st=con.createStatement();
		}
		//execute DDL query
		int count=0;
		if (st!=null) {
			//DDL query
			String query="CREATE TABLE TEMP_STUDENT (SNO int(10) PRIMARY KEY NOT NULL,SNAME VARCHAR(50) NOT NULL,SADD VARCHAR(100))";
			//send or execute DDL query to db s/w
			 count=st.executeUpdate(query);
			 System.out.println("Count is :"+count);
		}
		if (count==0) {
			System.out.println("Table created");
		}
		else {
			System.out.println("Table is not created");
		}
	} catch (SQLException e) {//to handle raised unknown exception
		// TODO Auto-generated catch block
		System.out.println(e.toString());//gives more details about raised exception
		e.printStackTrace();
	}
	catch (Exception e) {//to handle raised unknown exception
		// TODO: handle exception
		System.out.println(e.toString());//gives more details about raised unknown exception
		e.printStackTrace();
	}
	//close jdbc object
	finally {
		if(st!=null)
		{
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
}
