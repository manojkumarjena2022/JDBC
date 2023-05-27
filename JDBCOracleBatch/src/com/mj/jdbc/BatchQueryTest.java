package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/** JDBC App to execute batch of query (insert,update,delete)
 * @author Manoj
 *
 */
public class BatchQueryTest {
	//establish connection and create statement object
	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				Statement st=con.createStatement();){
			//add sql non-select queries to statement object
			if(st!=null)
			{
				st.addBatch("INSERT INTO STUDENT (SNO,SNAME,SADD,AVG) VALUES(1005,'mj kumar 44','sorooo',65)");
				st.addBatch("UPDATE STUDENT SET AVG=100 WHERE SNO=1000");
				st.addBatch("DELETE STUDENT WHERE SNO=1003");
			}
			//execute batch queries
			if (st!=null) {
				int result[]=st.executeBatch();
				//process result
				for (int i = 0; i < result.length; i++) {
					int j = result[i];
					System.out.println("No of records affected :"+j);
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
