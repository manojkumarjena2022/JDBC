package com.mj.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * JDBC App to fetch 10 candidate record from dt_candidates by using stored procedure
 * @author Manoj
 *
 */
/*
 * DELIMITER //
 * CREATE PROCEDURE PROCEDURE_GET_CANDIDATES(IN LIMIT_NO INT)
 *   BEGIN
 *    SELECT CAN_CODE, FIRST_NAME,DOB FROM dt_candidates WHERE DOB IS NOT NULL LIMIT LIMIT_NO;
 *   END //
 * DELIMITER ;
 */
public class FetchAllFromProcedure {
	private static String FETCH_QUERY="{CALL PROCEDURE_GET_CANDIDATES(?)}";
	public static void main(String[] args) {
		//get user inputs
		int no=0;
		try(Scanner sc=new Scanner(System.in)){
			System.out.println("Enter Limit:");
			no=sc.nextInt();
		}
		//establish connection and create callableStatement object
		try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_bultaminds","root","");
				CallableStatement cs=con.prepareCall(FETCH_QUERY);){
			//Set IN param values
			if (cs!=null) {
				cs.setInt(1, no);
			}
			//execute query
			if (cs!=null) {
				cs.execute();
				try(ResultSet rs= cs.getResultSet();){
					//process result
					if (rs!=null) {
						while(rs.next())
						{
							SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
							java.util.Date dob=rs.getDate(3);
							String sdob=sdf.format(dob);
							System.out.println("CAN CODE: "+rs.getString(1)+" CAN NAME: "+rs.getString(2)+" DOB:"+sdob);
						}
					}
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
