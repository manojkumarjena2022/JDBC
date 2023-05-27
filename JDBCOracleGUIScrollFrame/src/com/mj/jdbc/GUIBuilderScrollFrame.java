package com.mj.jdbc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUIBuilderScrollFrame {

	private JFrame frame;
	private JTextField textField_sno;
	private JTextField textField_sname;
	private JTextField textField_sadd;
	private JTextField textField_avg;

	private Connection con;
	private PreparedStatement ps;
	private String SELECT_QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	private ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIBuilderScrollFrame window = new GUIBuilderScrollFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIBuilderScrollFrame() {
		initialize();
		initializeJDBC();
	}
	private void initializeJDBC() {
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			ps=con.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("closing jdbc objects");
				if(rs!=null)
				{
					try {
						rs.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(ps!=null)
				{
					try {
						ps.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(con!=null)
				{
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("SNO:");
		lblNewLabel.setBounds(90, 29, 61, 13);
		frame.getContentPane().add(lblNewLabel);

		textField_sno = new JTextField();
		textField_sno.setBounds(227, 26, 118, 19);
		frame.getContentPane().add(textField_sno);
		textField_sno.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("SNAME:");
		lblNewLabel_1.setBounds(90, 69, 45, 13);
		frame.getContentPane().add(lblNewLabel_1);

		textField_sname = new JTextField();
		textField_sname.setBounds(227, 66, 118, 19);
		frame.getContentPane().add(textField_sname);
		textField_sname.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("SADD:");
		lblNewLabel_2.setBounds(90, 107, 45, 13);
		frame.getContentPane().add(lblNewLabel_2);

		textField_sadd = new JTextField();
		textField_sadd.setBounds(227, 104, 118, 19);
		frame.getContentPane().add(textField_sadd);
		textField_sadd.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("AVG:");
		lblNewLabel_3.setBounds(90, 148, 45, 13);
		frame.getContentPane().add(lblNewLabel_3);

		textField_avg = new JTextField();
		textField_avg.setText("");
		textField_avg.setBounds(224, 145, 121, 19);
		frame.getContentPane().add(textField_avg);
		textField_avg.setColumns(10);

		JButton btnNewButton = new JButton("First");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs.first();
					textField_sno.setText(rs.getString(1));
					textField_sname.setText(rs.getString(2));
					textField_sadd.setText(rs.getString(3));
					textField_avg.setText(rs.getString(4));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(39, 195, 85, 21);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Next");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!rs.isLast()) {
						rs.next();
						textField_sno.setText(rs.getString(1));
						textField_sname.setText(rs.getString(2));
						textField_sadd.setText(rs.getString(3));
						textField_avg.setText(rs.getString(4));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(134, 195, 85, 21);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Previous");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!rs.isFirst()) {
						rs.previous();
						textField_sno.setText(rs.getString(1));
						textField_sname.setText(rs.getString(2));
						textField_sadd.setText(rs.getString(3));
						textField_avg.setText(rs.getString(4));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(227, 195, 85, 21);
		frame.getContentPane().add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Last");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs.last();
					textField_sno.setText(rs.getString(1));
					textField_sname.setText(rs.getString(2));
					textField_sadd.setText(rs.getString(3));
					textField_avg.setText(rs.getString(4));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(322, 195, 85, 21);
		frame.getContentPane().add(btnNewButton_3);
	}
}
