package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * JDBC App to delete candidate from dt_candidates by can_code
 * @author Manoj
 *
 */
public class DeleteRecord {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		String can_code="";
		System.out.println("Enter candidate code you want delete :");
		sc=new Scanner(System.in);
		//get input from user
		if (sc!=null) {
			can_code=sc.next();
			can_code="'"+can_code+"'";
		}
		try {
			//establish connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_bultaminds","root","");
			//create statement object
			if(con!=null)
				st=con.createStatement();
			int count=0;
			//send and execute sql query to DB s/w
			if(st!=null)
			{
				count=st.executeUpdate("DELETE FROM DT_CANDIDATES WHERE CAN_CODE="+can_code);
			}
			if(count!=0)
			{
				System.out.println("No of record affected : "+count);
			}
			else
			{
				System.out.println("No record found to delete");
			}
		} catch (SQLException e) {//to handle known exception
			if(e.getErrorCode()>=900 && e.getErrorCode()<=999)
				System.out.println("Invalid column name or table name or sql keyword");
			System.out.println(e.toString());//gives details about raised exception
		}
		catch(Exception e)//to handle unknown exception
		{
			System.out.println(e.toString());//gives details about raised exception
		}
		//close jdbc objects
		finally {
			if(st!=null)
			{
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
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
