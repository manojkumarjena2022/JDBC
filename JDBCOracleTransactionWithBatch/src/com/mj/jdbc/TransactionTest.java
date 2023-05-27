package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/** JDBC App to test Transaction management
 * @author Manoj
 *
 */
/*
 * CREATE TABLE STUDENT_ACCOUNT ( id NUMBER GENERATED ALWAYS AS IDENTITY,
 *  sno NUMBER,
 *  account_no VARCHAR2(20) UNIQUE,
 *  balance NUMBER );
 */

public class TransactionTest {
private final static String SENDER_UPDATE_QUERY="UPDATE STUDENT_ACCOUNT SET BALANCE=BALANCE-? WHERE ACCOUNT_NO=?";
private final static String RECIEVER_UPDATE_QUERY="UPDATE STUDENT_ACCOUNT SET BALANCE=BALANCE+? WHERE ACCOUNT_NO=?";
public static void main(String[] args) {
	//get inputs
	long sender_acc_no,reciever_acc_no=0;
	double amount=0.0;
	try(Scanner sc=new Scanner(System.in)){
		System.out.println("Enter source account no :");
		sender_acc_no=sc.nextLong();
		System.out.println("Enter source account no :");
		reciever_acc_no=sc.nextLong();
		System.out.println("Enter amount you want to transfer :");
		amount=sc.nextDouble();
	}
	//establish connection create PreparedStatement object
	try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			Statement st=con.createStatement()){
		//begin Transaction
		//set auto commit to false
		if (con!=null) {
			con.setAutoCommit(false);
		}
		//add queries to batch
		st.addBatch("UPDATE STUDENT_ACCOUNT SET BALANCE=BALANCE-"+ amount +"WHERE ACCOUNT_NO="+sender_acc_no);
		st.addBatch("UPDATE STUDENT_ACCOUNT SET BALANCE=BALANCE+"+ amount +"WHERE ACCOUNT_NO="+reciever_acc_no);
		//execute batch queries
		if (st!=null) {
			int result[]=st.executeBatch();
			//process result
			boolean flag=true;
			for (int i = 0; i < result.length; i++) {
				int j = result[i];
				if (j==0) {
					flag=false;
				}
			}
			if (flag) {
				con.commit();
				System.out.println("Tx commited, money transfered");
			}
			else
			{
				con.rollback();
				System.out.println("Tx rolled back,money not transfered");
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
