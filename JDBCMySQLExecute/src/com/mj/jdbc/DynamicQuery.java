package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * JDBC App to execute select and non-select query based on user input
 * @author Manoj
 *
 */
public class DynamicQuery {
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		Scanner sc=null;
		String query="";
		sc=new Scanner(System.in);
		//get user input
		System.out.println("Enter sql query you want to execute :");
		//query=sc.next(); //this will not work as it doesn't read the complete string
		query=sc.nextLine();
		try {
			//establish connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_bultaminds","root","");
			//create statement object
			if (con!=null) {
				st=con.createStatement();
			}
			//send and execute sql query
			boolean flag=false;
			if (st!=null) {
				flag=st.execute(query);
			}
			//process result
			if (flag) {
				System.out.println("SELECT-Query executed");
				rs=st.getResultSet();
				//process resultSet object
				while (rs.next()!=false) {
					System.out.println(rs.getString(1)+ " "+rs.getString(2)+ " "+rs.getString(3));
				}
			}
			else
			{
				System.out.println("Non-Select query executed");
				int count=st.getUpdateCount();
				System.out.println("Now of record affected : "+count);
			}
		} catch (SQLException e) {//to handle known exception
			System.out.println(e.toString());//gives more details about raised exception
		}
		catch (Exception e) {//to handle unknown exception
			System.out.println(e.toString());//gives more details about raised exception
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
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	}
}
