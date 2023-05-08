package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * JDBC App to insert new candidate to dt_candidates table
 * @author Manoj
 *
 */
public class InsertRecord {
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		String can_code="";
		String first_name="";
		String last_name="";
		String gender="";
		String email="";
		String mobile="";
		sc=new Scanner(System.in);
		System.out.println("Enter candidate code: ");
		can_code=sc.next();
		can_code="'"+can_code+"'";
		System.out.println("Enter first name :");
		first_name=sc.next();
		first_name="'"+first_name+"'";
		System.out.println("Enter last name :");
		last_name=sc.next();
		last_name="'"+last_name+"'";
		System.out.println("Enter Gender :");
		gender=sc.next();
		gender="'"+gender+"'";
		System.out.println("Enter Email :");
		email=sc.next();
		email="'"+email+"'";
		System.out.println("Enter mobile :");
		mobile=sc.next();
		mobile="'"+mobile+"'";
		try {
			//establish database s/w connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_bultaminds","root","");
			if (con!=null) {
				st=con.createStatement();
			}
			String query="insert into dt_candidates (can_code,first_name,last_name,gender,email,mobile) values("+can_code+","+first_name+","+last_name+","+gender+","+email+","+mobile+")";
			int count=0;
			//send and execute sql query to db s/w
			if (st!=null) {
				count=st.executeUpdate(query);
			}
			if (count==0) {
				System.out.println("Record not inserted");
			}else
			{
				System.out.println("Record inserted");
			}
		} catch (SQLException e) {//handle known raised exception
			if(e.getErrorCode()>=900 && e.getErrorCode()<=999)
			{
				System.out.println("Invalid column names or table name or sql keywords");
			}
			else if (e.getErrorCode()==12899) {
				System.out.println("Do not insert more than col size data in to table");
			}
			else
			{
				System.out.println(e.toString());//gives details about raised application
			}

		}
		catch (Exception e) {//handle unknown raised exception
			System.out.println(e.toString());
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
