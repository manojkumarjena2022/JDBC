package com.mj.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.driver.OracleDriver;

/**
 * JDBC App to update image in STUDENT table
 * @author Manoj
 *
 */
public class UpdateImage {
	public static void main(String[] args) {
		Scanner sc=null;
		int sno=0;
		String image_url="";
		//create scanner object
		sc=new Scanner(System.in);
		//get user inputs
		System.out.println("Enter student id:");
		sno=sc.nextInt();
		System.out.println("Enter image url :");
		image_url=sc.next();


		try (InputStream is=new FileInputStream(image_url);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				PreparedStatement ps=con.prepareStatement("UPDATE STUDENT SET IMAGE=? WHERE SNO=?");) {
			//set values to sql query param
			if (ps!=null) {
				
				ps.setBlob(1, is);
				//OR
				//ps.setBinaryStream(1, is);
				ps.setInt(2, sno);
				
			}
			//execute query
			int count=0;
			if (ps!=null) {
				count=ps.executeUpdate();
			}
			//process the result
			if (count==0) {
				System.out.println("Record not updated");
			}
			else {
				System.out.println("Record updated");
			}
		}//jdbc objects will be closed
		catch (SQLException e) {//to handle known exception
			System.out.println(e.toString());//gives more details about raised known exception
			e.printStackTrace();
		}
		catch (Exception e) {//to handle unknown exception
			e.printStackTrace();
		}

	}
}
