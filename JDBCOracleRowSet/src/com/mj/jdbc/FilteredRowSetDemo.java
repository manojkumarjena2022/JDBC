package com.mj.jdbc;

import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.Predicate;
import javax.sql.rowset.RowSetProvider;

/** JDBC App to test FilteredRowSet
 * @author Manoj
 *
 */
public class FilteredRowSetDemo {
	private static class Filter1 implements Predicate{
		private String cond;
		public Filter1(String cond)
		{
			this.cond=cond;
		}
		@Override
		public boolean evaluate(RowSet rs) {
			try {
				if (rs.getString("SNAME").startsWith(cond)) {
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean evaluate(Object value, int column) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean evaluate(Object value, String columnName) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

	}
	public static void main(String[] args) {
		try(FilteredRowSet frowset=RowSetProvider.newFactory().createFilteredRowSet()){
			frowset.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
			frowset.setUsername("system");
			frowset.setPassword("tiger");
			frowset.setCommand("SELECT SNO,SNAME,SADD,AVG FROM STUDENT");
			frowset.setFilter(new Filter1("Ma"));
			frowset.execute();
			while (frowset.next()) {
				System.out.println(frowset.getString(1)+" "+frowset.getString(2)+" "+frowset.getString(3)+" "+frowset.getString(4));
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
