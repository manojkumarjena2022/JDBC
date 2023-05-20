package com.mj.jdbc;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIScrollFrameTest extends JFrame implements ActionListener,WindowListener {
	JLabel lb1,lb2,lb3,lb4;
	JTextField tsno,tsname,tsadd,tsavg;
	JButton bFirst,bNext,bLast,bPrevious;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	private static final String GET_STUDENT_QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	//constructor
	public GUIScrollFrameTest() {
		System.out.println("GUISCrollFrameTest :: 0-param costructor(start)");
		setTitle("GUI Front-end Scroll Test");
		setSize(300,300);
		setLayout(new FlowLayout());
		//add components
		lb1=new JLabel("sno");
		add(lb1);
		tsno=new JTextField(10);
		add(tsno);
		lb2=new JLabel("sname");
		add(lb2);
		tsname=new JTextField(10);
		add(tsname);
		lb3=new JLabel("sadd");
		add(lb3);
		tsadd=new JTextField(10);
		add(tsadd);
		lb4=new JLabel("savg");
		add(lb4);
		tsavg=new JTextField(10);
		add(tsavg);
		bFirst=new JButton("first");
		bNext=new JButton("next");
		bLast=new JButton("last");
		bPrevious=new JButton("previous");
		add(bFirst);add(bNext);add(bLast);add(bPrevious);
		//register and activate ActionListener for all 4 buttons
		bFirst.addActionListener(this);
		bNext.addActionListener(this);
		bLast.addActionListener(this);
		bPrevious.addActionListener(this);
		//add WindowListner to frame
		this.addWindowListener(this);
		//disable editing on text boxes
		tsno.setEditable(false);
		tsname.setEditable(false);
		tsadd.setEditable(false);
		tsavg.setEditable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closing of frame window will terminates Application flow
		initializeJDBC();
		System.out.println("GUISCrollFrameTest :: 0-param costructor(end)");
	}
	private void initializeJDBC() {
		// TODO Auto-generated method stub
		System.out.println("initializing JDBC");
		try {
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			if (con!=null) {
				System.out.println("Connection established");
			}
			//create prepare statement object
			ps=con.prepareStatement(GET_STUDENT_QUERY,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			//prepare scrollable resultset object
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		System.out.println("Main method start");
		new GUIScrollFrameTest();//Anonymous object
		System.out.println("Main method end	");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("action performed");
		boolean flag=false;
		if (e.getSource()==bFirst) {
			try {
				rs.first();
				flag=true;
				System.out.println("First button clicked");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource()==bNext) {
			try {
				if(!rs.isLast())
					rs.next();
				flag=true;
				System.out.println("Next button clicked");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource()==bLast) {
			try {
				rs.last();
				flag=true;
				System.out.println("Last button clicked");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource()==bPrevious) {
			try {
				if(!rs.isFirst())
					rs.previous();
				flag=true;
				System.out.println("Previous button clicked");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (flag) {
			try {
				tsno.setText(rs.getString(1));
				tsname.setText(rs.getString(2));
				tsadd.setText(rs.getString(3));
				tsavg.setText(rs.getString(4));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("ScrollFrameTest.WindowClosing()");
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (ps!=null) {
			try {
				ps.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}
}
