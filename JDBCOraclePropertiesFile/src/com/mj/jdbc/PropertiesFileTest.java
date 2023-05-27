package com.mj.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/** JDBC App to use properties file for database connection
 * @author Manoj
 *
 */
public class PropertiesFileTest {
	public static void main(String[] args) {
		Properties props=null;
		try(InputStream is=new FileInputStream("src/com/mj/commons/jdbc.properties")) {
			props=new Properties();
			props.load(is);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(props.getProperty("jdbc.url")+" "+props.getProperty("jdbc.username")+" "+props.getProperty("jdbc.password"));
		try(Connection con=DriverManager.getConnection(props.getProperty("jdbc.url"),props.getProperty("jdbc.username"),props.getProperty("jdbc.password"));
				Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet rs=st.executeQuery("SELECT SNO,SNAME FROM STUDENT")){
			rs.afterLast();
			while (rs.previous()) {
				System.out.println("SNO :"+rs.getString(1)+" SNAME:"+rs.getString(2));
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
