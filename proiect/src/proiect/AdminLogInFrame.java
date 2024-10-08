package proiect;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Cursor;
import java.awt.Toolkit;
public class AdminLogInFrame extends JFrame implements ActionListener, MouseListener{
	
	private int flag1 = 0, flag2 = 0, flagH = 0;
	private ImageIcon Picon = new ImageIcon("Book_JE2_BE2.png");
	private ImageIcon book = new ImageIcon(new ImageIcon("Book_JE2_BE2.png").getImage().getScaledInstance(150,150, Image.SCALE_SMOOTH));
	private JLabel potion = new JLabel();
 	private JLabel username = new JLabel("Username");
	private JLabel password = new JLabel("Password");
	private JPanel logInP = new JPanel();
	private JButton login = new JButton("Login");
	private JButton reset = new JButton("Reset");
	private JTextField user = new JTextField("admin");
	private JPasswordField pass = new JPasswordField("parola");
	private JOptionPane mess = new JOptionPane();
	private ImageIcon backgroundIMG = new ImageIcon("hexagons-pattern-geometric-abstract-background-simple-hexagonal-elements-medical-technology-science-design-beautiful-176591444.jpg"); 
	private JLabel background = new JLabel();
	private JLabel back = new JLabel("Inapoi la ecranul principal");
	
	@Override
	public void mouseClicked(MouseEvent e) {
	
		if(e.getSource() == user && flag1 == 0) {
			user.setText("");
			user.setFont(new Font("Courier", Font.BOLD, 15));
			flag1 = 1;
		}
		if(e.getSource() == pass && flag2 == 0) {
			pass.setText("");
			user.setFont(new Font("Courier", Font.BOLD, 15));
			flag2 = 2;
		}
		if(e.getSource() == back) {
			dispose();
			new MainFFrame();
		}
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
			
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == back) {
			back.setForeground(Color.PINK);
		}
	}
		
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == back) {
			back.setForeground(Color.RED);
		}
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == login){
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect", "root", "pacotheparrot22@@@");
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery("select * from admin where nume like '"+ user.getText() +"' and pass like '"+ pass.getText() + "'");
				if(!res.next()) {
					mess.showMessageDialog(null, "Numele de admin sau parola introduse sunt incorecte!", "Eroare", JOptionPane.PLAIN_MESSAGE);
					
				}
				else {
					stmt.close();
					con.close();
					res.close();
					mess.showMessageDialog(null, "Conectare realizata cu succes", "Succes", JOptionPane.PLAIN_MESSAGE);
					dispose();
					new AdminHomeFrame(user.getText(), flagH);
				}
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
			
		}
		if(e.getSource() == reset) {
			user.setText("");
			pass.setText("");		
		}
	}
	
	public AdminLogInFrame(){
		
		// frame setup
		setLayout(null);
		setVisible(true);
		setSize(1280, 800);
		setTitle("LibrarYO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setIconImage(Picon.getImage());
		setBackground(new Color(0x123456));
		setLocationRelativeTo(null);
		// frame setup
		
		//panel setup
		logInP.setBackground(Color.white);
		logInP.setBounds(380, 200, 520, 400);
		logInP.setLayout(null);
		logInP.setBorder(new LineBorder(Color.BLACK, 2));
		//pane setup
		
		//buttons
		back.addMouseListener(this);
		back.setBounds(15, 370, 230, 15);
		back.setFont(new Font("Courier", Font.BOLD, 13));
		back.setForeground(Color.RED);
		back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		login.setBounds(40, 270, 150, 55);
		reset.setBounds(330, 270, 150, 55);
		login.setFocusable(false);
		reset.setFocusable(false);
		login.addActionListener(this);
		reset.addActionListener(this);
		login.addMouseListener(this);
		reset.addMouseListener(this);
		login.setFont(new Font("Courier", Font.BOLD, 15));
		reset.setFont(new Font("Courier", Font.BOLD, 15));
		login.setBackground(Color.gray);
		reset.setBackground(Color.gray);
		login.setCursor(new Cursor(Cursor.HAND_CURSOR));
		reset.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//buttons
		
		//labels
		potion.setIcon(book);
		potion.setBounds(850, 320, 150, 150);
		background.setIcon(backgroundIMG);
		background.setBounds(0, 0, 1280, 800);
		username.setFont(new Font("Courier", Font.BOLD, 30));
		username.setForeground(Color.black);
		username.setBounds(40, 80, 160, 50);
		password.setFont(new Font("Courier", Font.BOLD, 30));
		password.setForeground(Color.black);
		password.setBounds(40, 170, 160, 50);
		//labels
		
		//textf
		user.setBounds(210, 93, 220, 30);
		pass.setBounds(210, 183, 220, 30);
		user.addMouseListener(this);
		pass.addMouseListener(this);
		user.setFont(new Font("Courier", Font.ITALIC, 15));
		pass.setFont(new Font("Courier", Font.ITALIC, 15));
		//textf
		
		//components
		add(background);	
		background.add(potion);
		background.add(logInP);
		logInP.add(back);
		logInP.add(username);
		logInP.add(password);
		logInP.add(login);
		logInP.add(reset);
		logInP.add(user);
		logInP.add(pass);
		//components

	}

}

