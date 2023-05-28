package com.mj.jdbc;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

/**
 * JDBC App to test WebRowSet
 * @author Manoj
 *
 */
public class WebRowSetDemo {
	public static void main(String[] args) {
		//create Webrowset instance
		try(WebRowSet wrowset=RowSetProvider.newFactory().createWebRowSet()) {
			//set database connection properties
			wrowset.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
			wrowset.setUsername("system");
			wrowset.setPassword("tiger");
			wrowset.setCommand("SELECT SNO,SNAME,SADD,AVG FROM STUDENT");
			//execute the query and populate the WebRowSet
			wrowset.execute();
			//process result from WebRowSet
			while (wrowset.next()) {
				System.out.println(wrowset.getString(1)+" "+wrowset.getString(2)+" "+wrowset.getString(3)+" "+wrowset.getString(4));
			}
			System.out.println("------------------");
			//write db table records as xml file content file
			OutputStream os=new FileOutputStream("student.xml");
			wrowset.writeXml(os);
			System.out.println("------------------");
			//writing db table records as xml content to console
			wrowset.writeXml(System.out);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
