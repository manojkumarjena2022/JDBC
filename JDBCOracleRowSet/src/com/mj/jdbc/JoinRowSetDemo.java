package com.mj.jdbc;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetProvider;

/** JDBC App to test JoinRowSet
 * @author Manoj
 *
 */
public class JoinRowSetDemo {
	public static void main(String[] args) {
		// Create a JdbcRowSet instance 
		try(CachedRowSet cs1=RowSetProvider.newFactory().createCachedRowSet();
				CachedRowSet cs2=RowSetProvider.newFactory().createCachedRowSet();
				JoinRowSet jrowset=RowSetProvider.newFactory().createJoinRowSet();){
			
			cs1.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
			cs1.setUsername("system");
			cs1.setPassword("tiger");
			cs1.setMatchColumn(1);
			cs1.setCommand("SELECT SNO,SNAME,SADD,AVG FROM STUDENT");
			cs1.execute();
			
			cs2.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
			cs2.setUsername("system");
			cs2.setPassword("tiger");
			cs2.setMatchColumn(2);
			cs2.setCommand("SELECT ACCOUNT_NO,SNO,BALANCE FROM STUDENT_ACCOUNT");
			cs2.execute();
			
			//add multiple cachedrowset to joinrowset
			jrowset.addRowSet(cs1);
			jrowset.addRowSet(cs2);
			while (jrowset.next()) {
				System.out.println(jrowset.getString(1)+" "+jrowset.getString(2)+" "+jrowset.getString(3)+" "+jrowset.getString(4)+" "+jrowset.getString(5)+" "+jrowset.getString(6));
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
