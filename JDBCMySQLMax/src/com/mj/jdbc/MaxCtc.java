package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC App to get Max CTC from dt_candidates
 * @author Manoj
 *
 */
public class MaxCtc {
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;

		try {
			//establish db connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_bultaminds","root","");
			//create statement object
			if(con!=null)
				st=con.createStatement();
			//send and execute sql query
			if(st!=null)
				//rs=st.executeQuery("select can_code,first_name,max_ctc_lakhs from dt_candidates where max_ctc_lakhs=(select Max(max_ctc_lakhs) from dt_candidates)");
				//rs=st.executeQuery("select can_code,first_name,max_ctc_lakhs from dt_candidates where max_ctc_lakhs=(select Min(max_ctc_lakhs) from dt_candidates)");
			rs=st.executeQuery("select can_code,first_name,max_ctc_lakhs from dt_candidates where max_ctc_lakhs=(select max_ctc_lakhs from dt_candidates group by max_ctc_lakhs order by max_ctc_lakhs desc limit 9,1)");
			//process result set object
			if(rs!=null)
			{
				boolean flag=false;//to check result set is empty or not
				while(rs.next()!=false)
				{
					flag=true;
					System.out.println("Candidate code : "+rs.getString(1)+" Name : "+rs.getString(2)+" Max CTC Lakhs : "+rs.getString(3));
				}
				if(!flag)
				{
					System.out.println("No record");
				}
			}
		} catch (SQLException e) {//handle known exception
			System.out.println(e.toString());//gives details about raised exception
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
