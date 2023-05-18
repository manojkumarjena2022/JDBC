package com.mj.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/**
 * JDBC App to check authentication by username and password by using/calling PL/SQL procedure
 * @author Manoj
 *
 */
/*
 * CREATE OR REPLACE PROCEDURE P_AUTHENTICATE (USER_NAME IN VARCHAR2 , PASS IN
 * VARCHAR2 , RESULT OUT VARCHAR2) AS RES_COUNT NUMBER(5); BEGIN SELECT COUNT(*)
 * INTO RES_COUNT FROM STUDENT WHERE USERNAME=USER_NAME AND PASSWORD=PASS;
 * IF(RES_COUNT<>0) THEN 
 *  RESULT:='VALID CREDENTIAL'; 
 * ELSE 
 *  RESULT:='INAVALID CREDENTIAL';
 * END IF; 
 * END P_AUTHENTICATE;
 */
public class ProcedureAuthenticate {
	//PL/SQL query
	private static String AUTHENTICATE_QUERY="{CALL P_AUTHENTICATE(?,?,?)}";
	public static void main(String[] args) {
		String user_name="";
		String password="";
		try(Scanner sc=new Scanner(System.in)){
			System.out.println("Enter user name:");
			user_name=sc.next();
			System.out.println("Enter password:");
			password=sc.next();
		}
		//establish connection and create CallableStatement object with pre-compiled PL/SQL query
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				CallableStatement cs=con.prepareCall(AUTHENTICATE_QUERY);){
			//register OUT parameter with JDBC data Type
			if (cs!=null) {
				cs.registerOutParameter(3, Types.VARCHAR);
			}
			//set IN param values
			if (cs!=null) {
				cs.setString(1, user_name);
				cs.setString(2, password);
			}
			//execute query
			if (cs!=null) {
				cs.execute();
			}
			//process result
			if (cs!=null) {
				String result=cs.getString(3);
				System.out.println(result);
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
