package proiect;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NewAccReg extends JFrame implements ActionListener, MouseListener{
	
	private int flag1 = 0, flag2 = 0, flag3 = 0, flag4 = 0, flag5 = 0, flag6 = 0, flag7 = 0, flag8 = 0;
	private ImageIcon catLogo = new ImageIcon(new ImageIcon("catlogo2.png").getImage().getScaledInstance(400, 150, Image.SCALE_SMOOTH));
	private ImageIcon Picon = new ImageIcon("Book_JE2_BE2.png"); 
	private JTextField nume = new JTextField("nume");
	private JTextField prenume = new JTextField("prenume");
	private JTextField nrtelefon = new JTextField("numar de telefon");
	private JTextField email = new JTextField("adresa de email");
	private JPasswordField password1 = new JPasswordField("pas");
	private JPasswordField password2 = new JPasswordField("pas");
	private JTextField nickname = new JTextField("nume de utilizator");
	private JLabel numeL = new JLabel("Nume");
	private JLabel prenumeL = new JLabel("Prenume");
	private JLabel nrtelefonL = new JLabel("Numar de telefon");
	private JLabel emailL = new JLabel("Adresa de email");
	private JLabel passwordL = new JLabel("Parola");
	private JLabel nicknameL = new JLabel("Nickname");
	private JPanel panou = new JPanel();
	private JButton submit = new JButton("Submit");
	private JLabel back = new JLabel("Intoarcere la meniul principal");
	private JButton reset = new JButton("Reset");
	private JOptionPane mesaj = new JOptionPane();
	private ImageIcon backgroundIMG = new ImageIcon("124383.jpg"); 
	private JLabel background = new JLabel();
	private JLabel welcomemess = new JLabel();
	private JLabel preferinteL = new JLabel("Preferinte");
	private JTextField preferinte = new JTextField("genurile cautate");
	private JLabel formatP = new JLabel("Formatul preferintelor*");
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == back) {
			dispose();
			new LogInFrame();
		}
		if(e.getSource() == formatP) {
			mesaj.showMessageDialog(null, "Preferintele trebuie alese dintr-o lista prestabilita (istorie, drama, actiune, horror, romance, comedie, politist, psihologic, sci-fi, mister, contemporan, biografie) pana la un maxim de doua, separate de o ',' si de un spatiu, ca in urmatorul exemplu : 'comedie, mister'.", "Info", JOptionPane.PLAIN_MESSAGE);
		}
		if(e.getSource() == nume && flag1 == 0) {
			nume.setText("");
			nume.setFont(new Font("Courier", Font.BOLD, 15));
			flag1 = 1;
		}
		if(e.getSource() == prenume && flag2 == 0) {
			prenume.setText("");
			prenume.setFont(new Font("Courier", Font.BOLD, 15));
			flag2 = 1;
		}
		if(e.getSource() == nrtelefon && flag3 == 0) {
			nrtelefon.setText("");
			nrtelefon.setFont(new Font("Courier", Font.BOLD, 15));
			flag3 = 1;
		}
		if(e.getSource() == email && flag4 == 0) {
			email.setText("");
			email.setFont(new Font("Courier", Font.BOLD, 15));
			flag4 = 1;
		}
		if(e.getSource() == password1 && flag5 == 0) {
			password1.setText("");
			password1.setFont(new Font("Courier", Font.BOLD, 15));
			flag5 = 1;
		}
		if(e.getSource() == password2 && flag6 == 0) {
			password2.setText("");
			password2.setFont(new Font("Courier", Font.BOLD, 15));
			flag6 = 1;
		}
		if(e.getSource() == nickname && flag7 == 0) {
			nickname.setText("");
			nickname.setFont(new Font("Courier", Font.BOLD, 15));
			flag7 = 1;
		}
		if(e.getSource() == preferinte && flag8 == 0) {
			preferinte.setText("");
			preferinte.setFont(new Font("Courier", Font.BOLD, 15));
			flag8 = 1;
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == back) {
			back.setForeground(Color.CYAN);
		}
		if(e.getSource() == formatP) {
			formatP.setForeground(Color.PINK);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == back) {
			back.setForeground(Color.BLUE);
		}
		if(e.getSource() == formatP) {
			formatP.setForeground(Color.RED);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submit) {
			if(!password1.getText().equals(password2.getText())) {
					mesaj.showMessageDialog(null, "Parolele trebuie sa fie identice", "Eroare", JOptionPane.PLAIN_MESSAGE);
			}
			else {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect", "root", "pacotheparrot22@@@");
					Statement stmt = con.createStatement();
					ResultSet res = stmt.executeQuery("select user_nickname from users where user_nickname like '"+ nickname.getText() +"' ");
					if(res.next()) {
						mesaj.showMessageDialog(null, "Numele de utilizator exista deja, incercati un alt nume", "Eroare", JOptionPane.PLAIN_MESSAGE);
					}
					else {
						res.close();
						String none = new String("none");
						stmt.execute("insert into users values(default, '" + prenume.getText() + "', '" + nume.getText() + "', '" + email.getText() + "', '" + nrtelefon.getText() + "', 0, '" + nickname.getText() + "', '" + password1.getText() + "', '" + preferinte.getText() + "', 0) ");
						mesaj.showMessageDialog(null, "Cont creat cu succes!", "Succes", JOptionPane.PLAIN_MESSAGE);
						con.close();
						stmt.close();
						dispose();
						new LogInFrame();
					}
						
				}catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		if(e.getSource() == reset) {
			nume.setText("");
			prenume.setText("");
			nrtelefon.setText("");
			email.setText("");
			password1.setText("");
			password2.setText("");
			nickname.setText("");
			preferinte.setText("");
		}
	}
	
	public NewAccReg() {
		
		setVisible(true);
		setSize(1280, 800);
		setTitle("LibrarYO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setIconImage(Picon.getImage());
		getContentPane().setBackground(new Color(0x123456));
		setLocationRelativeTo(null);
		setLayout(null);
		
		
		//panel
		panou.setBounds(290, 100, 700, 650);
		panou.setBackground(Color.WHITE);
		panou.setLayout(null);
		//panel
		
		//buttons
		submit.setBounds(320, 545, 100, 50);
		submit.setBackground(Color.GRAY);
		submit.setFont(new Font("Courier", Font.BOLD, 13));
		submit.addActionListener(this);
		submit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		submit.setFocusable(false);
		reset.setBounds(440, 545, 100, 50);
		reset.setFocusable(false);
		reset.setFont(new Font("Courier", Font.BOLD, 13));
		reset.setBackground(Color.gray);
		reset.setCursor(new Cursor(Cursor.HAND_CURSOR));
		reset.setFocusable(false);
		reset.addActionListener(this);
		//buttons
		
		//textfields
		nume.setBounds(340, 55, 220, 40);
		nume.setFont(new Font("Courier", Font.ITALIC, 15));
		nume.addMouseListener(this);
		prenume.setBounds(340, 110, 220, 40);
		prenume.setFont(new Font("Courier", Font.ITALIC, 15));
		prenume.addMouseListener(this);
		nrtelefon.setBounds(340, 165, 220, 40);
		nrtelefon.setFont(new Font("Courier", Font.ITALIC, 15));
		nrtelefon.addMouseListener(this);
		email.setBounds(340, 220, 220, 40);
		email.setFont(new Font("Courier", Font.ITALIC, 15));
		email.addMouseListener(this);
		password1.setBounds(340, 275, 220, 40);
		password1.setFont(new Font("Courier", Font.ITALIC, 15));
		password1.addMouseListener(this);
		password2.setBounds(340, 330, 220, 40);
		password2.setFont(new Font("Courier", Font.ITALIC, 15));
		password2.addMouseListener(this);
		nickname.setBounds(340, 385, 220, 40);
		nickname.setFont(new Font("Courier", Font.ITALIC, 15));
		nickname.addMouseListener(this);
		preferinte.setBounds(340, 440, 220, 40);
		preferinte.setFont(new Font("Courier", Font.ITALIC, 15));
		preferinte.addMouseListener(this);
		//textfields
		
		
		//labels
		background.setIcon(backgroundIMG);
		background.setBounds(0, 0, 1280, 800);
		welcomemess.setIcon(catLogo);
		welcomemess.setBounds(430, 10, 400, 150);
		numeL.setBounds(40, 60, 200, 30);
		numeL.setFont(new Font("Courier", Font.BOLD, 25));
		numeL.setForeground(Color.black);
		prenumeL.setBounds(40, 115, 200, 30);
		prenumeL.setFont(new Font("Courier", Font.BOLD, 25));
		prenumeL.setForeground(Color.black);
		nrtelefonL.setBounds(40, 165, 250, 30);
		nrtelefonL.setFont(new Font("Courier", Font.BOLD, 25));
		nrtelefonL.setForeground(Color.black);
		emailL.setBounds(40, 220, 230, 30);
		emailL.setFont(new Font("Courier", Font.BOLD, 25));
		emailL.setForeground(Color.black);
		passwordL.setBounds(40, 275, 200, 30);
		passwordL.setFont(new Font("Courier", Font.BOLD, 25));
		passwordL.setForeground(Color.black);
		nicknameL.setBounds(40, 385, 200, 30);
		nicknameL.setFont(new Font("Courier", Font.BOLD, 25));
		nicknameL.setForeground(Color.black);
		back.setBounds(25, 575, 270, 20);
		back.setFont(new Font("Courier", Font.BOLD, 15));
		back.setForeground(Color.BLUE);
		back.addMouseListener(this);
		back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		preferinteL.setBounds(40, 440, 200, 30);
		preferinteL.setFont(new Font("Courier", Font.BOLD, 25));
		preferinteL.setForeground(Color.black);
		formatP.addMouseListener(this);
		formatP.setBounds(340, 485, 185, 15);
		formatP.setFont(new Font("Courier", Font.BOLD, 13));
		formatP.setForeground(Color.RED);
		formatP.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//labels
		
		//components
		add(background);
		background.add(welcomemess);
		background.add(panou);
		panou.add(back);
		panou.add(numeL);
		panou.add(prenumeL);
		panou.add(nrtelefonL);
		panou.add(emailL);
		panou.add(passwordL);
		panou.add(nicknameL);
		panou.add(nume);
		panou.add(prenume);
		panou.add(nrtelefon);
		panou.add(email);
		panou.add(password1);
		panou.add(password2);
		panou.add(nickname);
		panou.add(submit);
		panou.add(reset);
		panou.add(preferinte);
		panou.add(preferinteL);
		panou.add(formatP);
		//components
	}

}
