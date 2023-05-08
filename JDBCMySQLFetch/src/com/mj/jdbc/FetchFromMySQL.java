package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FetchFromMySQL {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Connection con= DriverManager.getConnection("jdbc:mysql:///ats_bultaminds","root","");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_bultaminds","root","");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("SELECT * FROM DT_CANDIDATES LIMIT 10");
			while (rs.next()!=false) {
				System.out.println("Candidate ID : "+rs.getString(1)+" Candidate Code : "+rs.getString(2)+" Candidate Name : "+rs.getString(3));
				
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
