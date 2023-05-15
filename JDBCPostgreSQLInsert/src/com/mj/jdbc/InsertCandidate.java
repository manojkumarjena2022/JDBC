package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * JDBC App to insert candidate record to Candidate table of PostgreSQL DB s/w
 * @author Manoj
 *
 */
public class InsertCandidate {
	//insert sql query string
	public static String POSTGRE_INSERT_QUERY="INSERT INTO CANDIDATE VALUES(NEXTVAL('candidate_cid_seq'),?,?,?)";
	public static void main(String[] args) {
		String name="";
		int age=0;
		float avg=0.0f;
		//get inputs
		try(Scanner sc=new Scanner(System.in)){
			System.out.println("Enter Candidate Name:");
			name=sc.next();
			System.out.println("Enter age:");
			age=sc.nextInt();
			System.out.println("Enter avg:");
			avg=sc.nextFloat();
			//establish connection and create prepared statement object
			try(Connection con=DriverManager.getConnection("jdbc:postgresql:tutorial_db","postgres","tiger");
					PreparedStatement ps=con.prepareStatement(POSTGRE_INSERT_QUERY)){
				//bind param values
				if (ps!=null) {
					ps.setString(1, name);
					ps.setInt(2, age);
					ps.setFloat(3, avg);
				}
				//execute query
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
			} 
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
