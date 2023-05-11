package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.driver.OracleDriver;

/**
 * JDBC App to insert record to STUDENT table with auto sequence value in SNO Primary key column
 * To achieve this you must create a sequence by using either SQL PLUS with sql query or ORACLE SQLSERVER
 * @author Manoj
 *
 */
public class InsertWithSequence {
public static void main(String[] args) {
	Scanner sc=null;
	Connection con=null;
	PreparedStatement ps=null;
	String name="";
	String address="";
	Double avg=0.0;
	//create scanner object
	sc=new Scanner(System.in);
	//get user inputs
	System.out.println("Enter student details:");
	System.out.println("Enter sname :");
	name=sc.next();
	System.out.println("Enter sadd :");
	address=sc.next();
	System.out.println("Enter average :");
	avg=sc.nextDouble();
	try {
		//load driver
		//OracleDriver driver=new OracleDriver();
		//register driver
		//DriverManager.registerDriver(driver);
		//OR load and register driver
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//establish connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
		//create prepared statement object
		if (con!=null) {
			ps=con.prepareStatement("INSERT INTO STUDENT (SNO,SNAME,SADD,AVG) VALUES(SNOSEQ.NEXTVAL,?,?,?)");
		}
		//set values to sql query param
		if (ps!=null) {
			ps.setString(1, name);
			ps.setString(2, address);
			ps.setDouble(3, avg);
		}
		//execute query
		int count=0;
		if (ps!=null) {
			count=ps.executeUpdate();
		}
		//process the result
		if (count==0) {
			System.out.println("Record not inserted");
		}
		else {
			System.out.println("Record inserted");
		}
	} catch (SQLException e) {//to handle known exception
		System.out.println(e.toString());//gives more details about raised known exception
	}
	catch (Exception e) {//to handle unknown exception
		e.printStackTrace();
	}
	//close jdbc object
	finally {
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
			}
		}
	}
}
}
