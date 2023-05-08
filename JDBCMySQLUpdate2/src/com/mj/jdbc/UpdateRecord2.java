package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * JDBC App to update candidate ctc in dt_candidates table
 * @author Manoj
 *
 */
public class UpdateRecord2 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		String hike_percentage="";
		String location="";
		sc=new Scanner(System.in);
		//get user inputs
		System.out.println("Enter location of candidate to hike their CTC :");
		location=sc.next();
		location="'"+location+"'";
		System.out.println("Enter hike percentage :");
		hike_percentage=sc.next();
		//pepare sql query to be executed
		String query="UPDATE DT_CANDIDATES SET MAX_CTC_LAKHS=MAX_CTC_LAKHS+(MAX_CTC_LAKHS*"+hike_percentage+")/100 WHERE PREFERRED_LOCATION ="+location;
		try {
			//establish connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_bultaminds","root","");
			//create statement object
			if (con!=null) {
				st=con.createStatement();
			}
			int count=0;
			//send and execute sql query to db s/w
			if (st!=null) {
				count=st.executeUpdate(query);
			}
			//print result
			if (count==0) {
				System.out.println("No record found to update");
			}
			else {
				System.out.println("Record updated");
			}
		} catch (SQLException e) {//handle raised known exception
			if (e.getErrorCode()>=900 && e.getErrorCode()<=999) {
				System.out.println("Invalid column names or table name or SQL Keywords");
			}
			else if (e.getErrorCode()==12899) {
				System.out.println("Can not insert more than col size data");
			}
			else if (e.getErrorCode()==1400) {
				System.out.println("Null can not be inserted to PK col");
			}
			else if (e.getErrorCode()==1) {
				System.out.println("Duplicates are not allowed for PK col");
			}
			System.out.println(e.toString());//gives more details about the raised exception
		}
		catch (Exception e) {//to handle unknown exception
			System.out.println(e.toString());//gives more details about the raised exception
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
