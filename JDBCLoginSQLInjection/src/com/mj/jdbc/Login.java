package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * JDBC App for login and sql injection example
 * @author Manoj
 * @input     for sqlInjection useername: incorrectusername' OR 1='1 ,  password: incorrectpassword
 */
public class Login {
public static void main(String[] args) {
	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	Scanner sc=null;
	String user_name="";
	String password="";
	//get user input
	sc=new Scanner(System.in);
	System.out.println("Enter username :");
	user_name=sc.nextLine();
	user_name="'"+user_name+"'";
	System.out.println("Enter password :");
	password=sc.nextLine();
	password="'"+password+"'";
	try {
		//establish connection
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_bultaminds","root","");
		//craete statement object
		if (con!=null) {
			st=con.createStatement();
		}
		//execute sql query
		if (st!=null) {
			String query="Select count(*) from mst_users where login_id="+user_name+" AND ref_pass="+password;
			rs=st.executeQuery(query);
		}
		//process resultSet object
		int count=0;
		if (rs!=null) {
			rs.next();
		    count=rs.getInt(1);
		}
		if (count==0) {
			System.out.println("Invalid credentials");
		}
		else {
			System.out.println("Valid credentials");
		}
	} catch (SQLException e) {//to handle known exception
		System.out.println(e.toString());//gives details about raised known exception
		e.printStackTrace();
	}
	catch (Exception e) {//to handle unkown exception
		System.out.println(e.toString());//gives details about raised known exception
		e.printStackTrace();
	}
	//close jdbc objects
	finally {
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (st!=null) {
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
		if (sc!=null) {
			try {
				sc.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
		}
	}
}
}
