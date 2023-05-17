package com.mj.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * JDBC App to fetch record from PERSON_INFO_DATES table by using PL/SQL function and CallableStatement
 * @author Manoj
 *
 */
public class FetchUsingProcedureTest {
	//SQL query
	private static final String PROCEDURE_FETCH_QUERY="{call PROCEDURE_GET_PERSON_DETAILS_BY_ID(?,?,?,?)}";
	public static void main(String[] args) {
		int pid=0;
		try(Scanner sc=new Scanner(System.in)){
			//get user input
			System.out.println("Enter person id:");
			pid=sc.nextInt();
		}
		//establish connection and create callableStatement object with pre-compiled sql query
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				CallableStatement cs=con.prepareCall(PROCEDURE_FETCH_QUERY);){
			//register OUT param
			if (cs!=null) {
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.DATE);
				cs.registerOutParameter(4, Types.DATE);
			}
			//bind IN param in sql query
			if (cs!=null) {
				cs.setInt(1, pid);
			}
			//execute query
			if (cs!=null) {
				cs.execute();
			}
			//process result
			if (cs!=null) {
				SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date dob=cs.getDate(3);
				String sdob=sdf.format(dob);
				java.util.Date doj=cs.getDate(4);
				String sdoj=sdf.format(doj);
				System.out.println("Name :"+cs.getString(2)+" DOB: "+sdob+" DOJ:"+sdoj);
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
