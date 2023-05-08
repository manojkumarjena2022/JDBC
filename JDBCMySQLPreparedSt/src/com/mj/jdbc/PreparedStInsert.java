package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * JDBC App to insert multiple records using PreparedStatement
 * @author Manoj
 *
 */
public class PreparedStInsert {
//sql query for insert
	private static final String CANDIDATE_INSERT_QUERY="INSERT INTO DT_CANDIDATES (CAN_CODE,FIRST_NAME,LAST_NAME,GENDER,EMAIL,MOBILE) VALUES(?,?,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		sc=new Scanner(System.in);
		//get input from user for no of record
		System.out.println("Enter no of candidates you want to insert :");
		int count=sc.nextInt();
		try {
			//establish connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_bultaminds","root","");
			//create preparedStatement object
			if (con!=null) {
				//store prepared statement object along with pre-compiled query
				ps=con.prepareStatement(CANDIDATE_INSERT_QUERY);
			}
			//read user input values ,set them to query param and execute pre-compiled sql query
			if (ps!=null) {
				while (count!=0) {
					//get each candidate details from end-user
					System.out.println("Enter "+count+" candidate details :");
					System.out.println("Enter candidate code :");
					String can_code=sc.next();
					System.out.println("Enter first name :");
					String first_name=sc.next();
					System.out.println("Enter last name :");
					String last_name=sc.next();
					System.out.println("Enter gender :");
					String gender=sc.next();
					System.out.println("Enter last email :");
					String email=sc.next();
					System.out.println("Enter last mobile :");
					String mobile=sc.next();
					//set each candidate details as pre-compiled sql query
					ps.setString(1, can_code);
					ps.setString(2, first_name);
					ps.setString(3, last_name);
					ps.setString(4, gender);
					ps.setString(5, email);
					ps.setString(6, mobile);
					//execute pre-compiled sql query each time
					int result=ps.executeUpdate();
					//process execution result of pre-compiled sql query
					if (result==0) {
						System.out.println("Candidate details not inserted");
					}
					else {
						System.out.println("Candidate details inserted");
					}
					count--;
				}
			}
			
		} catch (SQLException e) {//to handle known exception
			System.out.println(e.toString());//gives more details about raised known exception
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {//to handle unknown exception
			System.out.println(e.toString());//gives more details about raised unknown exception
			// TODO: handle exception
		}
		//close jdbc objects
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
					// TODO: handle exception
				}
			}
		}
	}
}
