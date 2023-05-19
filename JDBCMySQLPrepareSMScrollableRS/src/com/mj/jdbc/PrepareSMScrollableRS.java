package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * JDBC App to fetch candidate records with PrepareStatement scrollable ResultSet
 * @author Manoj
 *
 */
public class PrepareSMScrollableRS {
private static String FETCH_QUERY="SELECT * FROM DT_CANDIDATES LIMIT ?";
public static void main(String[] args) {
	//get user input
	int limit=0;
	try(Scanner sc=new Scanner(System.in)){
		System.out.println("Enter limt :");
		limit=sc.nextInt();
	}
	//establish connection and create prepareStatement object with type,mode and pre-compiled query
	try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_bultaminds","root","");
			PreparedStatement ps=con.prepareStatement(FETCH_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)){
		//set param value for sql query
		if (ps!=null) {
			ps.setInt(1, limit);
		}
		//execute query
		if (ps!=null) {
			try(ResultSet rs=ps.executeQuery()){
				//process result
				if (rs!=null) {
					System.out.println("Records from top to down");
					System.out.println("--------------------------------");
					while (rs.next()) {
						System.out.println("Row :"+rs.getRow()+" Code :"+rs.getString(2)+" Name:"+rs.getString(3));
					}
					System.out.println("--------------------------------");
					System.out.println("Records from down to top");
					System.out.println("--------------------------------");
					rs.afterLast();
					while (rs.previous()) {
						System.out.println("Row :"+rs.getRow()+" Code :"+rs.getString(2)+" Name:"+rs.getString(3));
					}
					System.out.println("--------------------------------");
					System.out.println("First record");
					rs.first();
					System.out.println("Row :"+rs.getRow()+" Code :"+rs.getString(2)+" Name:"+rs.getString(3));
					System.out.println("--------------------------------");
					System.out.println("Last record");
					rs.last();
					System.out.println("Row :"+rs.getRow()+" Code :"+rs.getString(2)+" Name:"+rs.getString(3));
					System.out.println("--------------------------------");
					System.out.println("Absolute Forward");
					rs.absolute(3);
					System.out.println("Row :"+rs.getRow()+" Code :"+rs.getString(2)+" Name:"+rs.getString(3));
					System.out.println("--------------------------------");
					System.out.println("Absolute Backward");
					rs.absolute(-4);
					System.out.println("Row :"+rs.getRow()+" Code :"+rs.getString(2)+" Name:"+rs.getString(3));
					System.out.println("--------------------------------");
					System.out.println("Relative Forward");
					rs.relative(3);
					System.out.println("Row :"+rs.getRow()+" Code :"+rs.getString(2)+" Name:"+rs.getString(3));
					System.out.println("--------------------------------");
					System.out.println("Relative Backward");
					rs.relative(-2);
					System.out.println("Row :"+rs.getRow()+" Code :"+rs.getString(2)+" Name:"+rs.getString(3));
				}
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
