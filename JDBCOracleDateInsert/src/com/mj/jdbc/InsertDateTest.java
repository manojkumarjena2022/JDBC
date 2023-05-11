package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * JDBC App to insert record to PERSON_INFO_DATES with sequence
 * This App shows how to insert date with database date format
 * @author Manoj
 *
 */
public class InsertDateTest {
	private static String INSERT_STRING_QUERY="INSERT INTO PERSON_INFO_DATES VALUES(PID_SEQ.NEXTVAL,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		String name="";
		String dob="";
		String doj="";
		String dom="";
		sc=new Scanner(System.in);
		//get inputs from end user
		if(sc!=null) {
			System.out.println("Enter person details");
			System.out.println("Enter name :");
			name=sc.next();
			System.out.println("Enter DOB (dd-MM-yyyy) :");
			dob=sc.next();
			System.out.println("Enter DOJ (yyyy-MM-dd) :");
			doj=sc.next();
			System.out.println("Enter DOM (MMM-dd-yyyy)");
			dom=sc.next();
		}

		try {
			//convert date string to java.sql.Date class objects
			//for DOB (dd-MM-yyyy)
			//convert string date value to java.util.Date class object
			SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date udob = sdf1.parse(dob);
			//converting java.util.Date class object to java.sql.Date class object
			long ms=udob.getTime();
			java.sql.Date sqdob=new java.sql.Date(ms);
			//for DOJ (yyyy-MM-dd)
			//converting string date value to java.sql.Date class object
			java.sql.Date sqdoj=java.sql.Date.valueOf(doj);
			//for DOM (MMM-dd-yyyy)
			//converting date string to java.util.Date class object
			SimpleDateFormat sdf2=new SimpleDateFormat("MMM-dd-yyyy");
			java.util.Date udom=sdf2.parse(dom);
			//converting java.util.Date class object to java.sql.Date class object
			long ms2=udom.getTime();
			java.sql.Date sqdom=new java.sql.Date(ms2);
			
			//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			//create prepared statement object
			if (con!=null) {
				ps=con.prepareStatement(INSERT_STRING_QUERY);
			}
			//bind param values to sql query
			if (ps!=null) {
				ps.setString(1, name);
				ps.setDate(2, sqdob);
				ps.setDate(3, sqdoj);
				ps.setDate(4, sqdom);
			}
			//execute sql query
			int count=0;
			if (ps!=null) {
				count=ps.executeUpdate();
			}
			//process result
			if (count==0) {
				System.out.println("Record not inserted");
			}
			else {
				System.out.println("Record inserted");
			}
		} catch (SQLException e) {//to handle known exception
			System.out.println(e.toString());//gives more details about raised known exception
			e.printStackTrace();
		} catch (ParseException e) {//to handle known exception
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		catch (Exception e) {//to handle unknown exception
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		//close jdbc objects
		finally {
			if(ps!=null) {
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
