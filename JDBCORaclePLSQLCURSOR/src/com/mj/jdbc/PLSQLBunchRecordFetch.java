package com.mj.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.internal.OracleTypes;

/**
 * JDBC App to fetch bunch of records by using/calling PL/SQL procedure
 * @author Manoj
 *
 */
/*
 * CREATE OR REPLACE PROCEDURE P_GET_STUDENT_BY_INITIAL_CHAR ( INIT_CHAR IN VARCHAR2 , DETAILS OUT SYS_REFCURSOR ) AS
 *  BEGIN
 *   OPEN DETAILS FOR
 *    SELECT SNO,SNAME,SADD FROM STUDENT WHERE SNAME LIKE INIT_CHAR;
 *  END P_GET_STUDENT_BY_INITIAL_CHAR;
 */
public class PLSQLBunchRecordFetch {
	//PL/SQL query
	private static String FETCH_QUERY="{CALL P_GET_STUDENT_BY_INITIAL_CHAR(?,?)}";
	public static void main(String[] args) {
		String initChar="";
		try(Scanner sc=new Scanner(System.in)){
			System.out.println("Enter initial char of student name:");
			initChar=sc.next()+"%";
		}
		//establish connection and create CallableStatement object with pre-compiled PL/SQL query
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				CallableStatement cs=con.prepareCall(FETCH_QUERY);){
			//register OUT parameter with JDBC data Type
			if (cs!=null) {
				cs.registerOutParameter(2, OracleTypes.CURSOR);
			}
			//set IN param values
			if (cs!=null) {
				cs.setString(1, initChar);
			}
			//execute query
			if (cs!=null) {
				cs.execute();
			}
			//process result
			if (cs!=null) {
				try(ResultSet rs=(ResultSet) cs.getObject(2);){
					boolean flag=false;
					if (rs!=null) {
						while (rs.next()) {
							System.out.println(" SNO:"+rs.getString(1)+" Name:"+rs.getString(2)+" Add:"+rs.getString(3));
							flag=true;
						}
					}
					if (!flag) {
						System.out.println("No record found");
					}
				}
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
