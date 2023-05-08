package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * JDBC App for login with prevention SQL injection with the help PreparedStatement
 * @author Manoj
 * @implNote for sqlInjection useername: incorrectusername' OR 1='1 ,  password: incorrectpassword
 */
public class LoginPreventingSQLInjection {
	//login sql query with place holder
private static String USER_LOGIN_QUERY="SELECT COUNT(*) FROM MST_USERS WHERE LOGIN_ID=? AND REF_PASS=?";
public static void main(String[] args) {
	Scanner sc=null;
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	sc=new Scanner(System.in);
	//read inputs from end user
	System.out.println("Enter user name :");
	String user_name=sc.nextLine();
	System.out.println("Enter password :");
	String password=sc.nextLine();
	try {
		//establish connection
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_bultaminds","root","");
		//create ps object and get pre-compile sql query
		if (con!=null) {
			ps=con.prepareStatement(USER_LOGIN_QUERY);
		}
		if (ps!=null) {
			//set param to pre-compiled sql query
			ps.setString(1, user_name);
			ps.setString(2, password);
			//execute pre-compiled sql query
			rs=ps.executeQuery();
		}
		//process result
		int result=0;
		if (rs!=null) {
			rs.next();
			result=rs.getInt(1);
		}
		if (result==0) {
			System.out.println("Invalid credentials");
		}
		else {
			System.out.println("Valid credentials");
		}
	} catch (SQLException e) {//to handle known exception
		System.out.println(e.toString());//gives more details about raised known exception
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (Exception e) {//to handle unknown exception
		System.out.println(e.toString());//gives more details about raised exception
		// TODO: handle exception
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
		if (ps!=null) {
			try {
				ps.close();
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
