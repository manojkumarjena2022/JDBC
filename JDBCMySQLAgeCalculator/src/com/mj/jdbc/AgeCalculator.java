package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * JDBC App to calculate age of person from their respective DOB in DT_CANDIDATES table
 * @author Manoj
 *
 */
public class AgeCalculator {
	//public static final String SQL_QUERY="SELECT CAN_ID,FIRST_NAME,DOB,TIMESTAMPDIFF(YEAR,DOB,CURDATE()) AS AGE FROM DT_CANDIDATES WHERE CAN_ID=?";	
	public static final String SQL_QUERY="SELECT CAN_ID,FIRST_NAME,DOB,TIMESTAMPDIFF(DAY,DOB,CURDATE())/365.25 AS AGE FROM DT_CANDIDATES WHERE CAN_ID=?";	
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		sc=new Scanner(System.in);
		//get input from user
		String can_id="";
		if (sc!=null) {
			System.out.println("Enter can_id");
			can_id=sc.next();
		}
		try {
			//establish connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_bultaminds","root","");
			//create PreparedStatement object by passing SQL query and convert sql query to pre-compiled sql query
			if (con!=null) {
				ps=con.prepareStatement(SQL_QUERY);
			}
			//set sql query param value
			if (ps!=null) {
				ps.setString(1, can_id);
			}
			//execute query
			if (ps!=null) {
				rs=ps.executeQuery();
			}
			//process result
			if (rs!=null) {
				while (rs.next()) {
					//convert database date format to required date format
					SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
					java.sql.Date dob=rs.getDate(3);
					String sdob=sdf.format(dob);
					//print output
					System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+sdob+" "+rs.getString(4));
				}
			}
		} catch (SQLException e) {//to handle known exception
			System.out.println(e.toString());//gives more details about raised exception
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {//to handle unknown exception
			System.out.println(e.toString());//gives more details about raised unknown excetion
			// TODO: handle exception
		}
		//close jdbc objects
		finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
		}
	}
}
