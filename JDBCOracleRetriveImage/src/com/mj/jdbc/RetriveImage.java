package com.mj.jdbc;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

/**
 * JDBC App to retrieve image or blob from STUDENT table
 * @author Manoj
 *
 */
public class RetriveImage {
	public static String RETRIEVE_QUERY="SELECT * FROM STUDENT WHERE SNO=?";
	public static void main(String[] args) {
		String sno="";
		try(Scanner sc=new Scanner(System.in))
		{
			System.out.println("Enter student No:");
			sno=sc.next();	
			//create connection and prepared statement objects
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
					//create prepared statement object and send sql query for compile
					PreparedStatement ps=con.prepareStatement(RETRIEVE_QUERY);
					){

				if (ps!=null) {
					//bind param in value in sql query
					ps.setString(1, sno);
					try(ResultSet rs=ps.executeQuery())
					{
						//process result
						if (rs.next()) {
							System.out.println("SNO :"+rs.getString(1)+" Name :"+rs.getString(2));
							//create input stream pointing to blob column
							try(InputStream is=rs.getBinaryStream(5);
									//create output stream pointing to destination file
									OutputStream os=new FileOutputStream("retrirve_image.jpg");)
							{
								//copy blob col values to destination file
								IOUtils.copy(is, os);
								System.out.println("Blob or Image is retrieved and stored in destination file");

							}//try4

						}//if2
						else
						{
							System.out.println("No record found");
						}//else
					}//try3
				}//if1
			}//try2
		}//try1
		catch (Exception e) {
			// TODO: handle exception
		}
	}
}
