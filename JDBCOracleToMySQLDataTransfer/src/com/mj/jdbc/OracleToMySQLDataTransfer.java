package com.mj.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC App to insert records to MySQL DB by fetching from Oracle DB
 * @author Manoj
 *
 */
public class OracleToMySQLDataTransfer {
	private static String ORACLE_FETCH_QUERY="SELECT PID,NAME,DOB,DOJ,DOM FROM PERSON_INFO_DATES";
	private static String MYSQL_INSERT_QUERY= "INSERT INTO PERSON_INFO_DATES (PID, NAME, DOB, DOJ, DOM) VALUES (?, ?, ?, ?, ?)";
	public static void main(String[] args) {
		Connection oracle_con=null;
		Connection mysql_con=null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//establish both connections
			oracle_con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			mysql_con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_bultaminds","root","");
			//create statement and preparedstatement object
			if (oracle_con!=null) {
				st=oracle_con.createStatement();
			}
			if (mysql_con!=null) {
				ps=mysql_con.prepareStatement(MYSQL_INSERT_QUERY);
			}
			//execute oracle query to fetch records
			if (st!=null) {
				rs=st.executeQuery(ORACLE_FETCH_QUERY);
			}
			//process result and insert records to MySQL table
			int count=0;
			if (rs!=null) {
				while (rs.next()) {
					int pid=rs.getInt(1);
					String name=rs.getString(2);
					java.util.Date dob=rs.getDate(3);
					java.util.Date doj=rs.getDate(4);
					java.util.Date dom=rs.getDate(5);
					//bind params to mysql query
					if(ps!=null)
					{
						ps.setInt(1, pid);
						ps.setString(2, name);
						ps.setDate(3, new java.sql.Date(dob.getTime()));
						ps.setDate(4, new java.sql.Date(doj.getTime()));
						ps.setDate(5, new java.sql.Date(dom.getTime()));
						//execute mysql insert query
						ps.executeUpdate();
						count++;
					}
				}
			}
			if (count==0) {
				System.out.println("Records not inserted");
			}
			else
			{
				System.out.println("Records inserted");
			}
		} catch (SQLException e) {//to handle known exception
			System.out.println(e.toString());//gives more details about raised exception
			e.printStackTrace();
		}
		catch (Exception e) {//to handle unknown exception
			System.out.println(e.toString());//gives more details about raised exception
		}
		//close jdbc objects
		finally {
			if (rs!=null) {
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
			if (st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (mysql_con!=null) {
				try {
					mysql_con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (oracle_con!=null) {
				try {
					oracle_con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
