package com.mj.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/**
 * JDBC App to fetch record from PL/SQL stored function
 * @author Manoj
 *
 */
/*
 * DELIMITER // 
 * CREATE FUNCTION FF_GET_CANDIDATE_BY_ID(ID INT(11) UNSIGNED) RETURNS VARCHAR(11) 
 * BEGIN
 *  DECLARE CODE VARCHAR(11); 
 *  DECLARE NAME VARCHAR(11);
 *  DECLARE DOBB DATE; 
 *  SELECT CAN_CODE,FIRST_NAME,DOB INTO CODE,NAME,DOBB FROM dt_candidates WHERE CAN_ID=ID; 
 *  RETURN CODE;
 * END // 
 * DELIMITER ;
 */
public class FetchRecordFromFunction {
	//PL/SQL query to call procedure function
	private static String FETCH_QUERY="{?=CALL F_GET_CANDIDATE_BY_ID(?)}";
	public static void main(String[] args) {
		//get user input
		int can_id=-0;
		try(Scanner sc=new Scanner(System.in)){
			System.out.println("Enter candidate ID:");
			can_id=sc.nextInt();
		}
		//establish connection and create callableStatement object
		try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_bultaminds","root","");
				CallableStatement cs=con.prepareCall(FETCH_QUERY);){
			//register return value with JDBC Types
			if (cs!=null) {
				cs.registerOutParameter(1, Types.VARCHAR);
			}
			//set IN param values
			if (cs!=null) {
				cs.setInt(2, can_id);
			}
			//execute PL/SQL query
			if (cs!=null) {
				cs.execute();
			}
			//process result
			if(cs!=null) {
				System.out.println("CAN CODE:"+cs.getString(1));
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
