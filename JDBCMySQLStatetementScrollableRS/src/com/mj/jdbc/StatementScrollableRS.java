package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC App to fetch candidate records with Scrollable ResultSet
 * @author Manoj
 *
 */
public class StatementScrollableRS {
public static void main(String[] args) {
	//establish connection and create statement object in Scrollable mode
	try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_bultaminds","root","");
			Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);){
		//execute sql query
		if (st!=null) {
			try(ResultSet rs=st.executeQuery("SELECT * FROM DT_CANDIDATES LIMIT 20")){
				//process result
				if (rs!=null) {
					System.out.println("Records TOP to BOTTOM ");
					System.out.println("----------------------------------------------");
					while (rs.next()) {
						System.out.println("Row: "+rs.getRow()+" Code: "+rs.getString(2)+" Name:"+rs.getString(3));
					}
					System.out.println("----------------------------------------------");
					System.out.println("Records BOTTOM to TOP");
					System.out.println("----------------------------------------------");
					rs.afterLast();
					while (rs.previous()) {
						System.out.println("Row: "+rs.getRow()+" Code: "+rs.getString(2)+" Name:"+rs.getString(3));
					}
					System.out.println("----------------------------------------------");
					System.out.println("First Record");
					rs.first();
					System.out.println("Row: "+rs.getRow()+" Code: "+rs.getString(2)+" Name:"+rs.getString(3));
					System.out.println("----------------------------------------------");
					System.out.println("Last Record");
					rs.last();
					System.out.println("Row: "+rs.getRow()+" Code: "+rs.getString(2)+" Name:"+rs.getString(3));
					System.out.println("----------------------------------------------");
					System.out.println("Absolute forward direction");
					rs.absolute(3);
					System.out.println("Row: "+rs.getRow()+" Code: "+rs.getString(2)+" Name:"+rs.getString(3));
					System.out.println("----------------------------------------------");
					System.out.println("Absolute backward direction");
					rs.absolute(-6);
					System.out.println("Row: "+rs.getRow()+" Code: "+rs.getString(2)+" Name:"+rs.getString(3));
					System.out.println("----------------------------------------------");
					System.out.println("Relative forward direction");
					rs.relative(3);
					System.out.println("Row: "+rs.getRow()+" Code: "+rs.getString(2)+" Name:"+rs.getString(3));
					System.out.println("----------------------------------------------");
					System.out.println("Relative backward direction");
					rs.relative(-6);
					System.out.println("Row: "+rs.getRow()+" Code: "+rs.getString(2)+" Name:"+rs.getString(3));
					System.out.println("----------------------------------------------");
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
