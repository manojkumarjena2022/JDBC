package com.mj.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * JDBC App to fetch candidate record from dt_candidates by using stored procedure
 * @author Manoj
 *
 */
/*
 * DELIMITER // 
 *  CREATE PROCEDURE PP_GET_CANDIDATE_BY_ID(IN ID INT, OUT CODE VARCHAR(40), OUT NAME VARCHAR(100), OUT CAN_DOB DATE) 
 *   BEGIN
 *    SELECT CAN_CODE,FIRST_NAME, DOB INTO CODE, NAME, CAN_DOB FROM dt_candidates WHERE CAN_ID = ID;
 *   END // 
 * DELIMITER ;
 */
public class FetchRecordUsingProcedure {
	private static String FETCH_QUERY="{CALL P_GET_CANDIDATE_BY_ID(?,?,?,?)}";
	public static void main(String[] args) {
		//get user inputs
		int can_id=0;
		try(Scanner sc=new Scanner(System.in)){
			System.out.println("Enter Candidate ID:");
			can_id=sc.nextInt();
		}
		//establish connection and create callableStatement object
		try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_bultaminds","root","");
				CallableStatement cs=con.prepareCall(FETCH_QUERY);){
			//register OUT parameters
			if (cs!=null) {
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.registerOutParameter(4, Types.DATE);
			}
			//Set IN param values
			if (cs!=null) {
				cs.setInt(1, can_id);
			}
			//execute query
			if (cs!=null) {
				cs.execute();
			}
			//process result
			if (cs!=null) {
				SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date dob=cs.getDate(4);
				String sdob=sdf.format(dob);
				System.out.println("CAN CODE: "+cs.getString(2)+" CAN NAME: "+cs.getString(3)+" DOB:"+sdob);
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
