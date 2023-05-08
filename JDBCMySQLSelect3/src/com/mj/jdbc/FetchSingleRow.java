package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * JDBC App that fetch candidate name from dt_candidates by comparing candidate code
 * @author Manoj
 *
 */
public class FetchSingleRow {
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			sc =new Scanner(System.in);
			String can_code="";
			//read inputs
			if (sc!=null) {
				System.out.println("Enter candidate Code :");
				can_code=sc.next();
				can_code="'"+can_code+"'";//convert input values as required for the sql query
			}
			String query="SELECT FIRST_NAME FROM DT_CANDIDATES WHERE CAN_CODE="+can_code;
			//load and register driver is not mandatory
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//establish connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_bultaminds","root","");
			//create statement object
			if (con!=null) {
				st=con.createStatement();
			}
			//send and execute sql query in DB s/w
			if (st!=null) {
				rs=st.executeQuery(query);
			}
			//process the resultSet object
			if (rs!=null) {
				//checks whether resultset object is empty or not
				boolean flag=false;
				if(rs.next()!=false)
				{
					flag=true;
					System.out.println(rs.getString(1));
				}
				if(!flag)
				{
					System.out.println("No record found");
				}
			}
		} 
		/*
		 * catch (ClassNotFoundException e) { e.printStackTrace(); }
		 */
		catch (SQLException e) {//to handle known exception
			System.out.println(e.toString());//gives details about raised exception
		}
		//close jdbc objects
		finally {
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			if(st!=null)
			{
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(sc!=null)
			{
				try {
					sc.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
}
