package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * JDBC App to fetch Date type values in two different ways
 * 1.Printing database default format
 * 2.Printing as per required format
 * @author Manoj
 *
 */
public class FetchDateData {
	private static String SQL_QUERY="SELECT PID,NAME,DOB,DOJ,DOM FROM PERSON_INFO_DATES";
	public static void main(String[] args) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			//create PreparedStatement object with input sql query
			if (con!=null) {
				ps=con.prepareStatement(SQL_QUERY);
			}
			//execute to get result set
			if (ps!=null) {
				rs=ps.executeQuery();
			}
			//process default result
			/*
			 * while (rs.next()) {
			 * System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "
			 * +rs.getString(4)+" "+rs.getString(5)); }
			 */
			//process custom result
			while (rs.next()) {
				java.util.Date dob=rs.getDate(3);
				java.util.Date doj=rs.getDate(4);
				java.util.Date dom=rs.getDate(5);
				SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
				String sdob=sdf.format(dob);
				String sdoj=sdf.format(rs.getDate(4));
				String sdom=sdf.format(rs.getDate(5));
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+sdob+" "+sdoj+" "+sdom);
			}
		} catch (SQLException e) {//used to handle known exception
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {//to handle unknown exception
			e.printStackTrace();
			// TODO: handle exception
		}
		//close jdbc objects
		finally {
			if(rs!=null) {
				rs.close();
			}
			if (ps!=null) {
				ps.close();
			}
			if (con!=null) {
				con.close();
			}
			
		}
	}
}
