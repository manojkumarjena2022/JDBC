package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC App to test Updatable ResultSet
 * @author Manoj
 *
 */
public class UpdatableRSTest {
	public static void main(String[] args) {
		//Establish connection and create statement object
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);){
			//execute query
			if (st!=null) {
				try(ResultSet rs=st.executeQuery("SELECT SNO,SNAME,SADD,AVG FROM STUDENT");){
					//process result
					if (rs!=null) {
						while (rs.next()) {
							System.out.println(rs.getString(1)+" Name:"+rs.getString(2)+" Address: "+rs.getString(3)+" Avg: "+rs.getString(4));
						}
					}
					//insert operation
					/*
					 * rs.moveToInsertRow(); rs.updateInt(1, 1003);
					 * rs.updateString(2, "Manoj Kumar Jena");
					 * rs.updateString(3, "Balasore");
					 * rs.updateFloat(4, 9.8f); rs.insertRow();
					 * System.out.println("Record inserted");
					 */
					//update operation
					/*
					 * rs.absolute(4); rs.updateFloat(4, 7.6f); 
					 * rs.updateRow();
					 * System.out.println("Record updated");
					 */
					//delete operation
					rs.absolute(3);
					rs.deleteRow();
					System.out.println("Record deleted");
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
