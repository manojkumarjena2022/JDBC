package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * JDBC App to fetch date(DOB) range records from PERSON_INFO_DATES table
 * @author Manoj
 *
 */
public class DateRangeFetch {
	public static final String SQL_QUERY="SELECT NAME,DOB FROM PERSON_INFO_DATES WHERE DOB>=? AND DOB<=?";	
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		sc=new Scanner(System.in);
		//get user inputs
		String from_date="";
		String to_date="";
		if (sc!=null) {
			System.out.println("Enter from date(dd-MM-yyyy):");
			from_date=sc.next();
			System.out.println("Enter to date(dd-MM-yyyy):");
			to_date=sc.next();
		}
		//convert string date values to java.util.Date objects
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		java.sql.Date start_date=null;
		java.sql.Date end_date=null;
		try {
			start_date = new java.sql.Date(sdf.parse(from_date).getTime());
			end_date=new java.sql.Date(sdf.parse(to_date).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			//create PreparedStatement object by passing SQL query and convert sql query to pre-compiled sql query
			if (con!=null) {
				ps=con.prepareStatement(SQL_QUERY);
			}
			//bind parameters for sql query
			ps.setDate(1, start_date);
			ps.setDate(2, end_date);
			//execute query
			if (ps!=null) {
				rs=ps.executeQuery();
			}
			//process result
			if (rs!=null) {
				while (rs.next()) {
					//convert database date format to required date format
					SimpleDateFormat sdf2=new SimpleDateFormat("dd-MM-yyyy");
					java.sql.Date dob=rs.getDate(2);
					String sdob=sdf2.format(dob);
					//print output
					System.out.println(rs.getString(1)+" "+sdob);
				}
			}
		} catch (SQLException e) {//to handle known exception
			System.out.println(e.toString());//gives more details about raised exception
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {//to handle unknown exception
			System.out.println(e.toString());//gives more details about raised unknown excetion
			// TODO: handle exception
		}
		//close jdbc objects
		finally {
			if(rs!=null) {
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
			if (sc!=null){
				try {
					sc.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
