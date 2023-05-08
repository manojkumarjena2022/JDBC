package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * JDBC App to update candidate data in dt_candidates
 * @author Manoj
 *
 */
public class UpdateRecord {
public static void main(String[] args) {
	Scanner sc=null;
	Connection con=null;
	Statement st=null;
	String can_code="";
	String first_name="";
	String last_name="";
	sc=new Scanner(System.in);
	//get inputs from user and prepare for sql query
	System.out.println("Enter candidate code you want to update :");
	can_code=sc.next();
	can_code="'"+can_code+"'";
	System.out.println("Enter first name you want to update :");
	first_name=sc.next();
	first_name="'"+first_name+"'";
	System.out.println("Enter last name you want to update :");
	last_name=sc.next();
	last_name="'"+last_name+"'";
	//setup sql query
	String query="UPDATE dt_candidates SET FIRST_NAME="+first_name+",LAST_NAME="+last_name+" WHERE CAN_CODE="+can_code;
	try {
		//establish connection
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_bultaminds","root","");
		//create statement object
		if (con!=null) {
			st=con.createStatement();
		}
		//send and execute sql query to db s/w
		int count=0;
		if (st!=null) {
			count=st.executeUpdate(query);
		}
		if (count==0) {
			System.out.println("No record found to update");
		}
		else {
			System.out.println("Record updated");
		}
	} catch (SQLException e) {//to handle known exception
		if (e.getErrorCode()>=900 && e.getErrorCode()>=999) {
			System.out.println("Invalid column name or table name or SQL keyword");
		}
		else if (e.getErrorCode()==12899) {
			System.out.println("Do not insert more than col size data");
		}
		else if (e.getErrorCode()==1400) {
			System.out.println("Null can not be inserted to PK col");
		}
		else if (e.getErrorCode()==1) {
			System.out.println("Duplicate data can not be inserted to PK col");
		}
		else {
			System.out.println(e.toString());//gives more details about raised exception
		}
	}
	catch (Exception e) {//to handle unknown exception
		System.out.println(e.toString());//gives to details about raised exception
	}
	//close jdbc objects
	finally {
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
		if(sc!=null)
		{
			try {
				sc.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
}
