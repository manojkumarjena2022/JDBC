package com.mj.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/**
 * JDBC App to fetch records from student table by using PL/SQL function
 * @author Manoj
 *
 */
/*
 * CREATE OR REPLACE FUNCTION F_GET_STUDENT_DETAILS_BY_NO ( NO IN NUMBER , NAME OUT VARCHAR2 , ADDRESS OUT VARCHAR2 ) RETURN FLOAT AS 
 * AVERAGE FLOAT; 
 * BEGIN
 * SELECT SNAME,SADD,AVG INTO NAME,ADDRESS,AVERAGE FROM STUDENT WHERE SNO=NO;
 * RETURN AVERAGE; 
 * END F_GET_STUDENT_DETAILS_BY_NO;
 */
public class FetchRecordFromPLSQLFunction {
	private static String FETCH_QUERY="{?=CALL F_GET_STUDENT_DETAILS_BY_NO(?,?,?)}";
	public static void main(String[] args) {
		//get user inputs
		int sno=0;
		try(Scanner sc=new Scanner(System.in)){
			System.out.println("Enter student no:");
			sno=sc.nextInt();
		}
		//establish connection and create callableStatement object with pre-compiled sql query.
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				CallableStatement cs=con.prepareCall(FETCH_QUERY);){
			//register OUT parameter with JDBC data types
			if (cs!=null) {
				cs.registerOutParameter(1, Types.FLOAT);//return parameter
				cs.registerOutParameter(3, Types.VARCHAR);//OUT parameter
				cs.registerOutParameter(4, Types.VARCHAR);//OUT parameter
			}
			//set values for IN Parameter
			if (cs!=null) {
				cs.setInt(2, sno);
			}
			//execute query
			if (cs!=null) {
				cs.execute();
			}
			//process result
			if (cs!=null) {
				String name=cs.getString(3);
				String address=cs.getString(4);
				float average=cs.getFloat(1);
				System.out.println("Name: "+name+" Address:"+address+" Average: "+average);
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
