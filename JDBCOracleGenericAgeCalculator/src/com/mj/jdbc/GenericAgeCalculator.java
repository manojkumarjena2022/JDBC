package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * JDBC App to calculate age of person from their respective DOB in PERSON_INFO_DATES table in generic way without calculating in sql query
 * @author Manoj
 *
 */
public class GenericAgeCalculator {
	public static final String SQL_QUERY="SELECT NAME,DOB FROM PERSON_INFO_DATES";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			//create PreparedStatement object by passing SQL query and convert sql query to pre-compiled sql query
			if (con!=null) {
				ps=con.prepareStatement(SQL_QUERY);
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
					java.sql.Date dob=rs.getDate(2);
					String sdob=sdf.format(dob);
					
					//calculate age using sysdate and dob
					java.util.Date sysDate=new java.util.Date();
					float age=(sysDate.getTime()-dob.getTime())/(1000.0f*60.0f*60.0f*24.0f*365.25f);
					DecimalFormat df=new DecimalFormat("#.##");
					String sage=df.format(age);
					//print output
					System.out.println(rs.getString(1)+" "+sdob+" "+sage);
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
