package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC App to count no of records in dt_candidates table
 * @author Manoj
 *
 */
public class CountRecord {
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//establish connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_bultaminds","root","");
			//create statement object
			if(con!=null)
				st=con.createStatement();
			//create resultset object
			if(st!=null)
				rs=st.executeQuery("SELECT COUNT(*) FROM DT_CANDIDATES");
			if(rs!=null)
			{
				//process result set object
				if(rs.next())
				{
					System.out.println(rs.getString(1));
				}
				else
				{
					System.out.println("No record");//for empty table
				}
			}

		} catch (SQLException e) {//to handle raised exception
			System.out.println(e.toString());
		} 
		//close jdbc objects
		finally {
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(st!=null)
			{
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
