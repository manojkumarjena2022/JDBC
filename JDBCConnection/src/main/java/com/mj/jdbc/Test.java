package com.mj.jdbc;
import java.sql.*;
public class Test {

//	public static void main(String[] args) {
//		oracle.jdbc.driver.OracleDriver obj=new oracle.jdbc.driver.OracleDriver();
//        Connection con = null;
//        try {
//             DriverManager.registerDriver(obj);
//             con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if(con==null)
//        {
//            System.out.println("Not connected");
//        }
//        else
//        {
//            System.out.println("Connected");
//        }
//	}
	
	//using class.forName()
//	public static void main(String[] args) {
//		
//        Connection con = null;
//        try {
//        	Class.forName("oracle.jdbc.OracleDriver");
//             con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        if(con==null)
//        {
//            System.out.println("Not connected");
//        }
//        else
//        {
//            System.out.println("Connected");
//        }
//	}

	//auto load driver
public static void main(String[] args) {
		
        Connection con = null;
        try {
             con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(con==null)
        {
            System.out.println("Not connected");
        }
        else
        {
            System.out.println("Connected");
            System.out.println("connection class name :"+con.getClass());
            System.out.println("connection class name :"+con.hashCode());
        }
	}

}
