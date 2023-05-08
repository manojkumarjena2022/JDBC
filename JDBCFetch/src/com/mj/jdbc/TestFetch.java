package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestFetch {

	public static void main(String[] args) {
		Connection con=null;
		try {
			//establish connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			 //create connection object
			 Statement stmt=con.createStatement();
			 //send and execute sql select query
			 ResultSet rs=stmt.executeQuery("select * from student");
			 while (rs.next()!=false) {
				System.out.println("SNo : "+rs.getInt("sno")+" SName : "+rs.getString("sname")+ " SAdd : "+rs.getString("sadd")+ " Avg: "+rs.getFloat("avg"));
				//System.out.println("SNo : "+rs.getInt(1)+" SName : "+rs.getString(2)+ " SAdd : "+rs.getString(3)+ " Avg: "+rs.getFloat(4));
				//System.out.println("SNo : "+rs.getString(1)+" SName : "+rs.getString(2)+ " SAdd : "+rs.getString(3)+ " Avg: "+rs.getString(4));
				//System.out.println("SNo : "+rs.getString("sno")+" SName : "+rs.getString("sname")+ " SAdd : "+rs.getString("sadd")+ " Avg: "+rs.getString("avg"));
				
			}
			 //close jdbc objects
			 rs.close();
			 stmt.close();
			 con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
