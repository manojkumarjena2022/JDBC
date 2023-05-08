package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * JDBC App that gets candidate details from ats_bultaminds database based on given 2 preferred location
 * version 1.0
 * @author Manoj
 *
 */
public class Select2MySQL {
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		//read inputs from end user
		Scanner sc=null;
		String preferred_location1=null;
		String preferred_location2=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				System.out.println("Enter first prefered location :");
				preferred_location1=sc.next();
				System.out.println("Enter second prefered location :");
				preferred_location2=sc.next();
				//convert input values as required in sql query
				preferred_location1="'"+preferred_location1+"'";
				preferred_location2="'"+preferred_location2+"'";
			}
			//load and register jdbc driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_bultaminds","root","");
			//create statement object
			if(con!=null)
			{
				st=con.createStatement();
			}
			//prepare sql query
			String query="SELECT * FROM DT_CANDIDATES WHERE PREFERRED_LOCATION IN ("+preferred_location1+","+preferred_location2+")";
			//send and execute sql query in DB s/w
			if (st!=null) {
				rs=st.executeQuery(query);
			}
			if (rs!=null) {
				boolean flag=false;
				while (rs.next()!=false) {
					flag=true;
					System.out.println("Candidate code: "+rs.getString(2)+" Candidate name: "+rs.getString(3)+" Max CTC Lakhs: "+rs.getString(21)+" Location: "+rs.getString(25));
				}//while
				if(!flag)
				{
					System.out.println("No record found.");
				}
			}//if

		}//try
		catch (ClassNotFoundException ce) {//to handle known exception
			System.out.println(ce.toString());//gives details info about the raised exception
		} catch (SQLException se) {//to handle known exception
			System.out.println(se.toString());//gives details about the raised exception
		}
		catch (Exception e) {//to handle unknown exception
			e.printStackTrace();
		}
		finally {
			try {
				if (rs!=null) {
					rs.close();
				}
			} catch (SQLException se2) {
			}
			try {
				if (st!=null) {
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (con!=null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (sc!=null) {
					sc.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}
}
