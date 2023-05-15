package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * JDBC App to fetch candidate record from Candidate table of PostgreSQL DB s/w
 * @author Manoj
 *
 */
public class FetchRecord {
	//insert sql query string
	public static String POSTGRE_SELECT_QUERY="SELECT * FROM CANDIDATE WHERE CID=?";
	public static void main(String[] args) {
		int cid=0;
		//get input
		try(Scanner sc=new Scanner(System.in)){
			System.out.println("Enter Candidate ID:");
			cid=sc.nextInt();

			//establish connection and create prepared statement object
			try(Connection con=DriverManager.getConnection("jdbc:postgresql:tutorial_db","postgres","tiger");
					PreparedStatement ps=con.prepareStatement(POSTGRE_SELECT_QUERY)){
				//bind param values
				if (ps!=null) {
					ps.setInt(1, cid);
				}
				//execute query
				if (ps!=null) {
					try(ResultSet rs=ps.executeQuery()){
						//process result
						if(rs!=null) {
							if (rs.next()) {
								System.out.println("cid :"+rs.getInt(1)+" Name:"+rs.getString(2)+" Age:"+rs.getString(3)+" Avg:"+rs.getString(4));
							}
							else {
								System.out.println("Record not found");
							}
						}
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
