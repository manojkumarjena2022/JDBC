package com.mj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * JDBC App to fetch Parameter meta data of query
 * @author Manoj
 *
 */
public class ParameterMDTest {
	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				PreparedStatement ps=con.prepareStatement("INSERT INTO STUDENT (SNO,SNAME,SADD,AVG) VALUES(?,?,?,?)")){
			//create ParameterMetaData object
			if (ps!=null) {
				ParameterMetaData pmd=ps.getParameterMetaData();
				if (pmd!=null) {
					int parameterCount=pmd.getParameterCount();
					for (int i = 1; i < parameterCount; i++) {
						System.out.print("Parameter no :"+i+" ");
						System.out.print("Parameter name mode :"+pmd.getParameterMode(i)+" ");
						System.out.print("Parameter type name :"+pmd.getParameterTypeName(i)+" ");
						System.out.print("Parameter is signed :"+pmd.isSigned(i)+" ");
						System.out.println();
					}
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
