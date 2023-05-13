package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC App to test Try With Resource
 * @author Manoj
 *
 */
public class SelectTestTWR {

	public static void main(String[] args) {

		String query="SELECT * FROM PERSON_INFO_DATES";
		try (
				//establish connection
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				//create statement object
				Statement st=con.createStatement();
				//send and execute query
				ResultSet rs=st.executeQuery(query);) {
			//process result
			boolean flag=false;
			if (rs!=null) {
				while (rs.next()) {
					System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
					flag=true;
				}
			}//while
			if (!flag) {
				System.out.println("No record found");
			}


		} //try  // all jdbc objects will be closed here
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
