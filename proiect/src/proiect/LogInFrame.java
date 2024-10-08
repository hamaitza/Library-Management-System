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
public class LogInFrame extends JFrame implements ActionListener, MouseListener{
	
	private int flag1 = 0, flag2 = 0, flagH = 0;
	private ImageIcon Picon = new ImageIcon("Book_JE2_BE2.png");
	private ImageIcon librarian = new ImageIcon(new ImageIcon("Plains_Librarian.png").getImage().getScaledInstance(350, 685, Image.SCALE_SMOOTH));
	private ImageIcon catLogo = new ImageIcon(new ImageIcon("welcome_cat.png").getImage().getScaledInstance(400, 150, Image.SCALE_SMOOTH));
	private ImageIcon bspeech = new ImageIcon (new ImageIcon("bubblespeech.png").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
	private JLabel leftImg = new JLabel();
 	private JLabel username = new JLabel("Username");
	private JLabel password = new JLabel("Password");
	private JLabel welcomemess = new JLabel();
	private JPanel logInP = new JPanel();
	private JButton login = new JButton("Login");
	private JButton reset = new JButton("Reset");
	private JTextField user = new JTextField("utilizator");
	private JPasswordField pass = new JPasswordField("parola");
	private JLabel newacc = new JLabel("Nu aveti inca un cont? Creati unul gratuit!");
	private JLabel bubbleSpeech = new JLabel();
	private JOptionPane mess = new JOptionPane();
	private ImageIcon backgroundIMG = new ImageIcon("124383.jpg"); 
	private JLabel background = new JLabel();
	private JLabel back = new JLabel("Inapoi la ecranul principal");
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == newacc) {
			dispose();
			new NewAccReg();
		}
		if(e.getSource() == back) {
			dispose();
			new MainFFrame();
		}
		if(e.getSource() == bubbleSpeech) {
			dispose();
			Random rand = new Random();
			try {
				flagH = 1;
				new HomeFrame("guest_" + rand.nextInt(1000), flagH);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
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
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == newacc) {
			newacc.setForeground(Color.CYAN);
		}
		if(e.getSource() == back) {
			back.setForeground(Color.PINK);
		}
	}
		
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == newacc) {
			newacc.setForeground(Color.BLUE);
		}
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
				ResultSet res = stmt.executeQuery("select user_nickname, user_pass from users where user_nickname like '"+ user.getText() +"' and user_pass like '"+ pass.getText() + "'");
				if(!res.next()) {
					mess.showMessageDialog(null, "Numele de utilizator sau parola introduse sunt incorecte!", "Eroare", JOptionPane.PLAIN_MESSAGE);
					
				}
				else {
					stmt.close();
					con.close();
					res.close();
					mess.showMessageDialog(null, "Conectare realizata cu succes", "Succes", JOptionPane.PLAIN_MESSAGE);
					dispose();
					new HomeFrame(user.getText(), flagH);
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
	
	public LogInFrame(){
		
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
		logInP.setBounds(605, 235, 520, 400);
		logInP.setLayout(null);
		logInP.setBorder(new LineBorder(Color.BLACK, 2));
		//pane setup
		
		//buttons
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
		background.setIcon(backgroundIMG);
		background.setBounds(0, 0, 1280, 800);
		leftImg.setIcon(librarian);
		leftImg.setBounds(0, 55, 350, 685);
		newacc.addMouseListener(this);
		newacc.setBounds(95, 340, 350, 15);
		newacc.setFont(new Font("Courier", Font.BOLD, 13));
		newacc.setForeground(Color.BLUE);
		newacc.setCursor(new Cursor(Cursor.HAND_CURSOR));
		back.addMouseListener(this);
		back.setBounds(15, 370, 230, 15);
		back.setFont(new Font("Courier", Font.BOLD, 13));
		back.setForeground(Color.RED);
		back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		welcomemess.setIcon(catLogo);
		welcomemess.setBounds(680, 150, 400, 150);
		username.setFont(new Font("Courier", Font.BOLD, 30));
		username.setForeground(Color.black);
		username.setBounds(40, 80, 160, 50);
		password.setFont(new Font("Courier", Font.BOLD, 30));
		password.setForeground(Color.black);
		password.setBounds(40, 170, 160, 50);
		bubbleSpeech.setIcon(bspeech);
		bubbleSpeech.setBounds(335, 10, 300, 300);
		bubbleSpeech.addMouseListener(this);
		bubbleSpeech.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
		background.add(leftImg);	
		background.add(welcomemess);
		background.add(logInP);
		background.add(bubbleSpeech);
		logInP.add(back);
		logInP.add(username);
		logInP.add(password);
		logInP.add(login);
		logInP.add(reset);
		logInP.add(user);
		logInP.add(pass);
		logInP.add(newacc);
		//components

	}

}
