package com.mj.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/**
 * JDBC APP to call a procedure present DB server using CallableStatement
 * @author Manoj
 *
 */
/*
 * create or replace procedure first_procedure(x in number,y in number,z out
 * number) as begin z:=x+y; end;
 */
public class CallableStatementProcedureTest {
	private static final String PROCEDURE_QUERY="{call first_procedure(?,?,?)}";
public static void main(String[] args) {
		//read inputs
	int first,second=0;
	try(Scanner sc=new Scanner(System.in)){
		System.out.println("Enter first value x:");
		first=sc.nextInt();
		System.out.println("Enter second value y:");
		second=sc.nextInt();
	}
	//establish connection and create CallableStatement object
	try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			//craete CallableStatement object having the query calling PL/SQL procedure as the pre-compiled sql query
			CallableStatement cs=con.prepareCall(PROCEDURE_QUERY)){
		//register OUT params with JDBC Data types
		if(cs!=null)
		{
			cs.registerOutParameter(3, Types.INTEGER);
		}
		//set values to IN params
		if (cs!=null) {
			cs.setInt(1, first);
			cs.setInt(2, second);
		}
		//execute or call PL/SQL function
		if (cs!=null) {
			cs.execute();
		}
		//get result from OUT param
		int output=0;
		if(cs!=null)
		{
			output=cs.getInt(3);
			System.out.println("OUTPUT :"+output);
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
