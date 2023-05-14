package com.mj.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * JDBC App to insert CLOB(as Resume) into job_seekers_info table
 * @author Manoj
 */
public class CLOBInsert {
	public static String INSERT_CLOB_QUERY="INSERT INTO JOB_SEEKERS_INFO (JSID,NAME,RESUME) VALUES (JS_SEQ.NEXTVAL,?,?)";
	public static void main(String[] args) {
		//read inputs
		String name="";
		String resume_url="";
		try(Scanner sc=new Scanner(System.in)){
			System.out.println("Enter candidate name:");
			name=sc.next();
			System.out.println("Enter resume url:");
			resume_url=sc.next().replace("?", "");
		}
		//create stream pointing to resume file
		try(Reader reader=new FileReader(resume_url)){
			//establish connection
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
					//create preparedStatement object
					PreparedStatement ps=con.prepareStatement(INSERT_CLOB_QUERY)){
				//set param values
				if (ps!=null) {
					ps.setString(1, name);
					//ps.setCharacterStream(2, reader);
					ps.setClob(2, reader);
				}
				//execute query
				int count=0;
				if (ps!=null) {
					count=ps.executeUpdate();
				}
				if (count==0) {
					System.out.println("Record not inserted");
				}
				else
				{
					System.out.println("Record inserted");
				}

			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
