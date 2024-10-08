package proiect;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Graphics2D;
import java.awt.Cursor;

public class HomeFrame extends JFrame implements ActionListener, MouseListener{
	
	private ImageIcon Picon = new ImageIcon("Book_JE2_BE2.png"); 
	private JLabel background = new JLabel();
	private ImageIcon backgroundIMG = new ImageIcon("124383.jpg");
	private JLabel mesajBV = new JLabel();
	private DefaultTableModel model = new DefaultTableModel();
	private JTable t = new JTable(model);
	private JScrollPane catalogCarti = new JScrollPane(t);
	private int i = 0, flag;
	private JMenuBar mb = new JMenuBar();
	private JMenu setari = new JMenu("Utilizator");
	private JMenu ajutor = new JMenu("Ajutor");
	private JMenu logout = new JMenu("Logout");
	private JMenu termeniDeUtilizare = new JMenu("Termeni De Utilizare");
	private JMenuItem utilizator  = new JMenuItem("Utilizator");
	private JMenuItem aplicatie = new JMenuItem("Setari Aplicatie");
	private JMenuItem logoutt = new JMenuItem("Logout");
	private JMenuItem schimbaU = new JMenuItem("Schimba utilizator");
	private JButton butonFiltru = new JButton("Filtru");
	private JButton butonImprumuta = new JButton("Imprumuta");
	private JButton butonDetalii = new JButton("Detalii");
	private JOptionPane mess = new JOptionPane();
	private DefaultTableModel model2 = new DefaultTableModel();
	private JTable t2 = new JTable(model2);
	private JScrollPane news = new JScrollPane(t2);
	private DefaultTableModel model3 = new DefaultTableModel();
	private JTable t3 = new JTable(model3);
	private JScrollPane reco = new JScrollPane(t3);
	private ImageIcon noutatiI = new ImageIcon(new ImageIcon("noutati.png").getImage().getScaledInstance(450, 253, Image.SCALE_SMOOTH));
	private JLabel noutati = new JLabel();
	private ImageIcon catalogI = new ImageIcon(new ImageIcon("catalogCarti.png").getImage().getScaledInstance(450, 253, Image.SCALE_SMOOTH));
	private JLabel catalogL = new JLabel();
	private ImageIcon recomandariI = new ImageIcon(new ImageIcon("recomandari.png").getImage().getScaledInstance(450, 253, Image.SCALE_SMOOTH));
	private JLabel recomandari = new JLabel();
	private JButton but = new JButton("ID");
	private JTextField txt = new JTextField();
	private JFrame x;
	private JFrame det;
	private JLabel temp = new JLabel();
	private JButton search = new JButton("Cauta");
	private JButton gen = new JButton("Gen");
	private JTextField txt2 = new JTextField();
	private JButton crescatorID = new JButton("Ordonat crescator dupa ID");
	private JButton descrescatorID = new JButton("Ordonat descrescator dupa ID");
	private JButton crescatorNRC = new JButton("Ordonat crescator dupa numarul de carti disponibile");
	private JButton descrescatorNRC = new JButton("Ordonat descrescator dupa numarul de carti disponibile");
	private String p1 = "", p2 = "";
	private JButton reset = new JButton("Reset");
	private JTextField id1 = new JTextField("ID");
	private JTextField perioada = new JTextField("Perioada de imprumut");
	private JButton imprumuta = new JButton("Imprumuta");
	private String numeT;
	private JButton returnare = new JButton("Returnare");
	private JTextField id4 = new JTextField("ID");
	private JTextField status2 = new JTextField("Status");
	private JButton actualizeaza = new JButton("Actualizeaza");
	private JLabel temp2 = new JLabel();
	private Connection ConnectingToDB () throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect", "root", "pacotheparrot22@@@");
		return con;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == logoutt) {
			dispose();
			new LogInFrame();
		}
		if(e.getSource() == returnare) {
			x = new JFrame();
			JLabel xbackg = new JLabel();
			x.setVisible(true);
			x.setSize(420, 100);
			x.setTitle("LibrarYO");
			x.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			x.setResizable(false);
			x.setIconImage(Picon.getImage());
			x.getContentPane().setBackground(new Color(0x123456));
			x.setLocationRelativeTo(null);
			x.setLayout(null);
			xbackg.setIcon(backgroundIMG);
			xbackg.setBounds(0, 0, 420, 100);
			x.add(xbackg);
			xbackg.add(id4);
			xbackg.add(actualizeaza);
			xbackg.add(status2);
			
			id4.setBounds(10, 20, 100, 25);
			status2.setBounds(130, 20, 110, 25);
			actualizeaza.setBounds(270, 20, 120, 25);
			actualizeaza.addActionListener(this);
			actualizeaza.setBackground(Color.GRAY);
		}
		if(e.getSource() == actualizeaza) {
			
				try {
					DefaultTableModel model4 = new DefaultTableModel();
					JTable t4 = new JTable(model4);
					JScrollPane y = new JScrollPane(t4);
					model4.addColumn("ID");
					model4.addColumn("Numele");
					model4.addColumn("Prenume");
					model4.addColumn("Carte");
					model4.addColumn("ID_Carte");
					model4.addColumn("Perioada");
					model4.addColumn("Data_Returnare");
					model4.addColumn("Status");
					Connection conn = ConnectingToDB();
					Statement stmt = conn.createStatement();
					stmt.execute("update imprumut set Status = '" + status2.getText() + "' where ID = '" + Integer.parseInt(id4.getText()) + "'");
					ResultSet res = stmt.executeQuery("select * from imprumut where ID like '%" + Integer.parseInt(id4.getText()) + "%' order by ID DESC");
					i=0;
					while(res.next()) {
						model4.insertRow(i, new Object[] {res.getInt("ID"), res.getString("Nume"), res.getString("Prenume"), res.getString("User_ID"), res.getString("Carte_ID"), res.getString("Perioada"), res.getString("Data_Returnare"), res.getString("Status")});
						i++;
					}
					y.setBounds(0, 0, 750, 150);
					temp2.removeAll();
					temp2.add(y);
					temp2.revalidate();
					temp2.repaint();
					
					conn.close();
					stmt.close();
					res.close();
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
		}
		if(e.getSource() == utilizator) {
			x = new JFrame();
			JLabel xbackg = new JLabel();
			x.setVisible(true);
			x.setSize(1000, 700);
			x.setTitle("LibrarYO");
			x.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			x.setResizable(false);
			x.setIconImage(Picon.getImage());
			x.getContentPane().setBackground(new Color(0x123456));
			x.setLocationRelativeTo(null);
			x.setLayout(null);
			xbackg.setIcon(backgroundIMG);
			xbackg.setBounds(0, 0, 1000, 700);
			xbackg.add(temp2);
			temp2.setBounds(100, 420, 750, 150);
			x.add(xbackg);
			x.add(returnare);
			returnare.setBounds(375, 590, 200, 50);
			returnare.setBackground(Color.GRAY);
			returnare.addActionListener(this);
			Connection conn;
			try {
				conn = ConnectingToDB();
				Statement stmt = conn.createStatement();
				ResultSet res = stmt.executeQuery("select * from users where user_nickname like '%" + numeT + "%'");
				res.next();
				int h = res.getInt("ID");
				JLabel nume = new JLabel("Numele utilizatorului: " + res.getString("user_name"));
				JLabel prenume = new JLabel("Prenumele utilizatorului: " + res.getString("user_surname"));
				JLabel email = new JLabel("E-mailul utilizatorului: " + res.getString("user_email"));
				JLabel mbnr = new JLabel("Numarul de telefon al utilizatorului: " + res.getString("user_mbnr") );
				JLabel nickname = new JLabel("Nickname-ul utilizatorului: " + res.getString("user_nickname"));
				JLabel preferinte = new JLabel("Preferintele utilizatorului: " + res.getString("user_preferinte"));
				
				xbackg.add(nume);
				xbackg.add(prenume);
				xbackg.add(email);
				xbackg.add(mbnr);
				xbackg.add(nickname);
				xbackg.add(preferinte);
				
				nume.setBounds(15, 5, 500, 30);
				nume.setFont(new Font("Courier", Font.BOLD, 20));
				nume.setForeground(Color.white);
				prenume.setBounds(15, 75, 500, 30);
				prenume.setFont(new Font("Courier", Font.BOLD, 20));
				prenume.setForeground(Color.white);
				email.setBounds(15, 145, 500, 30);
				email.setFont(new Font("Courier", Font.BOLD, 20));
				email.setForeground(Color.white);
				mbnr.setBounds(15, 215, 600, 30);
				mbnr.setFont(new Font("Courier", Font.BOLD, 20));
				mbnr.setForeground(Color.white);
				nickname.setBounds(15, 285, 500, 30);
				nickname.setFont(new Font("Courier", Font.BOLD, 20));
				nickname.setForeground(Color.white);
				preferinte.setBounds(15, 355, 600, 30);
				preferinte.setFont(new Font("Courier", Font.BOLD, 20));
				preferinte.setForeground(Color.white);
				
				DefaultTableModel model5 = new DefaultTableModel();
				JTable t5 = new JTable(model5);
				JScrollPane y5 = new JScrollPane(t5);
				model5.addColumn("ID");
				model5.addColumn("Numele");
				model5.addColumn("Prenume");
				model5.addColumn("Carte");
				model5.addColumn("ID_Carte");
				model5.addColumn("Perioada");
				model5.addColumn("Data_Returnare");
				model5.addColumn("Status");
				res = stmt.executeQuery("select * from imprumut where User_ID like '%" + h + "%' order by ID DESC");
				i=0;
				while(res.next()) {
					model5.insertRow(i, new Object[] {res.getInt("ID"), res.getString("Nume"), res.getString("Prenume"), res.getString("User_ID"), res.getString("ID_Carte"), res.getString("Perioada"), res.getString("Data_Returnare"), res.getString("Status")});
					i++;
				}
				
				y5.setBounds(0, 0, 750, 150);
				temp2.add(y5);
			} catch (ClassNotFoundException | SQLException e1) {
				
				e1.printStackTrace();
			}
			
			
		}
		if(e.getSource() == butonFiltru) {
			x = new JFrame();
			JLabel xbackg = new JLabel();
			x.setVisible(true);
			x.setSize(400, 560);
			x.setTitle("LibrarYO");
			x.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			x.setResizable(false);
			x.setIconImage(Picon.getImage());
			x.getContentPane().setBackground(new Color(0x123456));
			x.setLocationRelativeTo(null);
			x.setLayout(null);
			xbackg.setIcon(backgroundIMG);
			xbackg.setBounds(0, 0, 400, 560);
			x.add(xbackg);
			xbackg.add(txt);
			xbackg.add(txt2);
			xbackg.add(search);
			xbackg.add(gen);
			xbackg.add(crescatorID);
			xbackg.add(descrescatorID);
			xbackg.add(crescatorNRC);
			xbackg.add(descrescatorNRC);
			xbackg.add(reset);
			
			txt.setBounds(30, 15, 180, 40);
			search.setBounds(280, 15, 75, 40);
			txt2.setBounds(30, 90, 180, 40);
			gen.setBounds(280, 90, 75, 40);
			crescatorID.setBounds(15, 165, 360, 40);
			descrescatorID.setBounds(15, 240, 360, 40);
			crescatorNRC.setBounds(15, 315, 360, 40);
			descrescatorNRC.setBounds(15, 390, 360, 40);
			reset.setBounds(15, 465, 360, 40);
			reset.setBackground(Color.GRAY);
			search.setBackground(Color.GRAY);
			gen.setBackground(Color.GRAY);
			crescatorID.setBackground(Color.GRAY);
			descrescatorID.setBackground(Color.GRAY);
			crescatorNRC.setBackground(Color.GRAY);
			descrescatorNRC.setBackground(Color.GRAY);
			search.addActionListener(this);
			gen.addActionListener(this);
			crescatorID.addActionListener(this);
			descrescatorID.addActionListener(this);
			crescatorNRC.addActionListener(this);
			descrescatorNRC.addActionListener(this);
			reset.addActionListener(this);
			
		}
		if(e.getSource() == reset) {
			temp.removeAll();
			temp.add(catalogCarti);
			temp.revalidate();
			temp.repaint();
		}
		if(e.getSource() == search) {
			p1 = txt.getText();
			DefaultTableModel modelP = new DefaultTableModel();
			JTable y = new JTable(modelP);
			JScrollPane tempo = new JScrollPane(y);
			try {
				Connection conn = ConnectingToDB();
				Statement stmt = conn.createStatement();
				ResultSet res = stmt.executeQuery("select * from books where book_name like '%" + p1 + "%' order by rand()");
				if(!res.next() || p1 == "") {
					mess.showMessageDialog(null, "Niciun rezultat gasit", "Eroare", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					temp.removeAll();
					modelP.addColumn("ID");
					modelP.addColumn("Numele Cartii");
					modelP.addColumn("Gen");
					modelP.addColumn("Numar exemplare");
					i=0;
					if(p2 != "") {
						res = stmt.executeQuery("select * from books where book_name like '%" + p1 + "%' and book_genre like '%" + p2 + "%' order by rand()");
					}
					else {
						res = stmt.executeQuery("select * from books where book_name like '%" + p1 + "%' order by rand()");
					}
					while(res.next()) {
						modelP.insertRow(i, new Object[] {res.getInt("book_no"), res.getString("book_name"), res.getString("book_genre"), res.getInt("book_nr")});
						i++;
					}
					tempo.setBounds(0, 0, 750, 450);
					temp.add(tempo);
					temp.revalidate();
					temp.repaint();
					conn.close();
					res.close();
					stmt.close();
				}
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
			
		}
		if(e.getSource() == gen ) {
			p2 = txt2.getText();
			DefaultTableModel modelP = new DefaultTableModel();
			JTable y = new JTable(modelP);
			JScrollPane tempo = new JScrollPane(y);
			try {
				Connection conn = ConnectingToDB();
				Statement stmt = conn.createStatement();
				ResultSet res = stmt.executeQuery("select * from books where book_genre like '%" + p2 + "%' order by rand()");
				if(!res.next() || p2 == "") {
					mess.showMessageDialog(null, "Niciun rezultat gasit", "Eroare", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					temp.removeAll();
					modelP.addColumn("ID");
					modelP.addColumn("Numele Cartii");
					modelP.addColumn("Gen");
					modelP.addColumn("Numar exemplare");
					i=0;
					if(p1 != "") {
						res = stmt.executeQuery("select * from books where book_name like '%" + p1 + "%' and book_genre like '%" + p2 + "%' order by rand()");
					}
					else {
						res = stmt.executeQuery("select * from books where book_genre like '%" + p2 + "%' order by rand()");
					}
					while(res.next()) {
						modelP.insertRow(i, new Object[] {res.getInt("book_no"), res.getString("book_name"), res.getString("book_genre"), res.getInt("book_nr")});
						i++;
					}
					tempo.setBounds(0, 0, 750, 450);
					temp.add(tempo);
					temp.revalidate();
					temp.repaint();
					conn.close();
					res.close();
					stmt.close();
				}
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource() == crescatorID) {
			DefaultTableModel modelP = new DefaultTableModel();
			JTable y = new JTable(modelP);
			JScrollPane tempo = new JScrollPane(y);
			try {
				Connection conn = ConnectingToDB();
				Statement stmt = conn.createStatement();
				ResultSet res ;
				temp.removeAll();
				modelP.addColumn("ID");
				modelP.addColumn("Numele Cartii");
				modelP.addColumn("Gen");
				modelP.addColumn("Numar exemplare");
				i=0;
				if(p1 != "" && p2 != "") {
					res = stmt.executeQuery("select * from books where book_name like '%" + p1 + "%' and book_genre like '%" + p2 + "%' order by book_no ASC");
				}
				else if (p1 != "" && p2 == ""){
					res = stmt.executeQuery("select * from books where book_name like '%" + p1 + "%' order by book_no ASC");
				}
				else if (p1 == "" && p2 != "") {
					res = stmt.executeQuery("select * from books where book_genre like '%" + p2 + "%' order by book_no ASC");
				}
				else {
					res = stmt.executeQuery("select * from books order by book_no ASC");
				}
				while(res.next()) {
					modelP.insertRow(i, new Object[] {res.getInt("book_no"), res.getString("book_name"), res.getString("book_genre"), res.getInt("book_nr")});
					i++;
				}
				tempo.setBounds(0, 0, 750, 450);
				temp.add(tempo);
				temp.revalidate();
				temp.repaint();
				conn.close();
				res.close();
				stmt.close();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource() == descrescatorID) {
			DefaultTableModel modelP = new DefaultTableModel();
			JTable y = new JTable(modelP);
			JScrollPane tempo = new JScrollPane(y);
			try {
				Connection conn = ConnectingToDB();
				Statement stmt = conn.createStatement();
				ResultSet res ;
				temp.removeAll();
				modelP.addColumn("ID");
				modelP.addColumn("Numele Cartii");
				modelP.addColumn("Gen");
				modelP.addColumn("Numar exemplare");
				i=0;
				if(p1 != "" && p2 != "") {
					res = stmt.executeQuery("select * from books where book_name like '%" + p1 + "%' and book_genre like '%" + p2 + "%' order by book_no DESC");
				}
				else if (p1 != "" && p2 == ""){
					res = stmt.executeQuery("select * from books where book_name like '%" + p1 + "%' order by book_no DESC");
				}
				else if (p1 == "" && p2 != "") {
					res = stmt.executeQuery("select * from books where book_genre like '%" + p2 + "%' order by book_no DESC");
				}
				else {
					res = stmt.executeQuery("select * from books order by book_no DESC");
				}
				while(res.next()) {
					modelP.insertRow(i, new Object[] {res.getInt("book_no"), res.getString("book_name"), res.getString("book_genre"), res.getInt("book_nr")});
					i++;
				}
				tempo.setBounds(0, 0, 750, 450);
				temp.add(tempo);
				temp.revalidate();
				temp.repaint();
				conn.close();
				res.close();
				stmt.close();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource() == crescatorNRC) {
			DefaultTableModel modelP = new DefaultTableModel();
			JTable y = new JTable(modelP);
			JScrollPane tempo = new JScrollPane(y);
			try {
				Connection conn = ConnectingToDB();
				Statement stmt = conn.createStatement();
				ResultSet res ;
				temp.removeAll();
				modelP.addColumn("ID");
				modelP.addColumn("Numele Cartii");
				modelP.addColumn("Gen");
				modelP.addColumn("Numar exemplare");
				i=0;
				if(p1 != "" && p2 != "") {
					res = stmt.executeQuery("select * from books where book_name like '%" + p1 + "%' and book_genre like '%" + p2 + "%' order by book_nr ASC");
				}
				else if (p1 != "" && p2 == ""){
					res = stmt.executeQuery("select * from books where book_name like '%" + p1 + "%' order by book_nr ASC");
				}
				else if (p1 == "" && p2 != "") {
					res = stmt.executeQuery("select * from books where book_genre like '%" + p2 + "%' order by book_nr ASC");
				}
				else {
					res = stmt.executeQuery("select * from books order by book_nr ASC");
				}
				while(res.next()) {
					modelP.insertRow(i, new Object[] {res.getInt("book_no"), res.getString("book_name"), res.getString("book_genre"), res.getInt("book_nr")});
					i++;
				}
				tempo.setBounds(0, 0, 750, 450);
				temp.add(tempo);
				temp.revalidate();
				temp.repaint();
				conn.close();
				res.close();
				stmt.close();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource() == descrescatorNRC) {
			DefaultTableModel modelP = new DefaultTableModel();
			JTable y = new JTable(modelP);
			JScrollPane tempo = new JScrollPane(y);
			try {
				Connection conn = ConnectingToDB();
				Statement stmt = conn.createStatement();
				ResultSet res ;
				temp.removeAll();
				modelP.addColumn("ID");
				modelP.addColumn("Numele Cartii");
				modelP.addColumn("Gen");
				modelP.addColumn("Numar exemplare");
				i=0;
				if(p1 != "" && p2 != "") {
					res = stmt.executeQuery("select * from books where book_name like '%" + p1 + "%' and book_genre like '%" + p2 + "%' order by book_nr DESC");
				}
				else if (p1 != "" && p2 == ""){
					res = stmt.executeQuery("select * from books where book_name like '%" + p1 + "%' order by book_nr DESC");
				}
				else if (p1 == "" && p2 != "") {
					res = stmt.executeQuery("select * from books where book_genre like '%" + p2 + "%' order by book_nr DESC");
				}
				else {
					res = stmt.executeQuery("select * from books order by book_nr DESC");
				}
				while(res.next()) {
					modelP.insertRow(i, new Object[] {res.getInt("book_no"), res.getString("book_name"), res.getString("book_genre"), res.getInt("book_nr")});
					i++;
				}
				tempo.setBounds(0, 0, 750, 450);
				temp.add(tempo);
				temp.revalidate();
				temp.repaint();
				conn.close();
				res.close();
				stmt.close();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == butonDetalii) {
			x = new JFrame();
			JLabel xbackg = new JLabel();
			x.setVisible(true);
			x.setSize(200, 100);
			x.setTitle("LibrarYO");
			x.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			x.setResizable(false);
			x.setIconImage(Picon.getImage());
			x.getContentPane().setBackground(new Color(0x123456));
			x.setLocationRelativeTo(null);
			x.setLayout(null);
			xbackg.setIcon(backgroundIMG);
			xbackg.setBounds(0, 0, 200, 100);
			x.add(xbackg);
			but.setBounds(130, 15, 50, 35);
			txt.setBounds(5, 15, 100, 32);
			x.add(txt);
			x.add(but);
			but.addActionListener(this);	
			
		}
		if(e.getSource() == but) {	
			String id = txt.getText();
			try {
				txt.setText("");
				Connection conn = ConnectingToDB();
				Statement stmt = conn.createStatement();
				ResultSet res = stmt.executeQuery("select * from books where book_no = '"  + id + "' ");
				if(res.next()) {
					x.dispose();
					det = new JFrame();
					JLabel backg = new JLabel();
					JLabel titlu = new JLabel("Titlul cartii: " + res.getString("book_name"));
					JLabel gen = new JLabel("Genurile cartii: " + res.getString("book_genre"));
					JLabel autor = new JLabel("Autorul cartii: " + res.getString("autor"));
					JLabel limba = new JLabel("Limba: " +res.getString("limba"));
					JLabel datap = new JLabel("Anul publicarii: " + res.getString("data_publicarii"));
					JLabel nrpagini = new JLabel("Numarul de pagini: " + res.getString("nr_pagini"));
					JLabel editura = new JLabel("Editura: " + res.getString("editura"));
					JLabel isbn = new JLabel("ISBN" + res.getString("ISBN"));
					String descriere = res.getString("book_desc");
					String t = "";
					det.setVisible(true);
					det.setSize(800, 650);
					det.setTitle("LibrarYO");
					det.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					det.setResizable(false);
					det.setIconImage(Picon.getImage());
					det.getContentPane().setBackground(new Color(0x123456));
					det.setLocationRelativeTo(null);
					det.setLayout(null);
					backg.setIcon(backgroundIMG);
					backg.setBounds(0, 0, 800, 650);
					titlu.setBounds(20, 10, 400, 25);
					titlu.setFont(new Font("Courier", Font.BOLD, 17));
					titlu.setForeground(Color.white);
					gen.setBounds(20, 45, 400, 25);
					gen.setFont(new Font("Courier", Font.BOLD, 17));
					gen.setForeground(Color.white);
					autor.setBounds(20, 90, 400, 25);
					autor.setFont(new Font("Courier", Font.BOLD, 17));
					autor.setForeground(Color.white);
					limba.setBounds(20, 135, 400, 25);
					limba.setFont(new Font("Courier", Font.BOLD, 17));
					limba.setForeground(Color.white);
					datap.setBounds(20, 180, 400, 25);
					datap.setFont(new Font("Courier", Font.BOLD, 17));
					datap.setForeground(Color.white);
					nrpagini.setBounds(20, 225, 400, 25);
					nrpagini.setFont(new Font("Courier", Font.BOLD, 17));
					nrpagini.setForeground(Color.white);
					editura.setBounds(20, 270, 400, 25);
					editura.setFont(new Font("Courier", Font.BOLD, 17));
					editura.setForeground(Color.white);
					int u = 320;
					for( int i = 0; i < descriere.length(); i++ ) {
						t = t + descriere.charAt(i);
						if( (i + 1) % 55 == 0) {
							
							JLabel q = new JLabel(t);
							q.setBounds(20, u, 630, 20);
							q.setFont(new Font("Courier", Font.BOLD, 18));
							q.setForeground(Color.white);
							u = u + 25;
							backg.add(q);
							t = "";
						}
					}
					if( t != "") {
						JLabel q = new JLabel(t);
						q.setBounds(20, u, 630, 20);
						q.setFont(new Font("Courier", Font.BOLD, 18));
						q.setForeground(Color.white);
						u = u + 35;
						backg.add(q);
					}
					det.add(backg);	
					backg.add(titlu);
					backg.add(gen);
					backg.add(autor);
					backg.add(limba);
					backg.add(datap);
					backg.add(nrpagini);
					backg.add(editura);
					backg.add(isbn);
				}
				conn.close();
				stmt.close();
				res.close();

			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == butonImprumuta) {
			if(flag == 1) {
				mess.showMessageDialog(null, "Aceasta optiune poate fi accesata doar de catre un utilizator conectat", "Eroare", JOptionPane.PLAIN_MESSAGE);
			}
			else if (flag == 0) {
				x = new JFrame();
				JLabel xbackg = new JLabel();
				x.setVisible(true);
				x.setSize(400, 100);
				x.setTitle("LibrarYO");
				x.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				x.setResizable(false);
				x.setIconImage(Picon.getImage());
				x.getContentPane().setBackground(new Color(0x123456));
				x.setLocationRelativeTo(null);
				x.setLayout(null);
				xbackg.setIcon(backgroundIMG);
				xbackg.setBounds(0, 0, 400, 100);
				x.add(xbackg);
				xbackg.add(id1);
				xbackg.add(imprumuta);
				xbackg.add(perioada);
	
				id1.setBounds(10, 20, 100, 25);
				perioada.setBounds(130, 20, 120, 25);
				imprumuta.setBounds(270, 20, 100, 25);
				imprumuta.addActionListener(this);
				imprumuta.setBackground(Color.GRAY);
				id1.addMouseListener(this);
				perioada.addMouseListener(this);
			}	
		}
		if(e.getSource() == imprumuta) {
			try {
				x.dispose();
				Connection conn = ConnectingToDB();
				Statement stmt = conn.createStatement();
				ResultSet res = stmt.executeQuery("select * from books where book_no like '%" + id1.getText() + "%'");
				if(res.next()) {
					String id2 = res.getString("book_no");
					String nume = res.getString("book_name");
					res = stmt.executeQuery("select * from users where user_nickname like '%" + numeT + "%'");
					res.next();
					String numeR = res.getString("user_name");
					String prenumeR = res.getString("user_surname");
					int idR = res.getInt("ID");
					java.util.Date date = Calendar.getInstance().getTime();  
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
					Calendar c = Calendar.getInstance();
					String strDate = dateFormat.format(date); 
					c.setTime(dateFormat.parse(strDate));
					c.add(Calendar.DATE, Integer.parseInt(perioada.getText()));
					strDate = dateFormat.format(c.getTime());
					stmt.execute("insert into imprumut values(default, '" + numeR + "', '" + prenumeR + "', '" + numeT + "', '" + Integer.toString(idR) + "', '" + id2 + "', '" + perioada.getText() + "', '" + strDate + "', 'Procesare' '" + "')");
				}
				
			} catch (ClassNotFoundException | SQLException | ParseException e1) {
				e1.printStackTrace();
			}
			
		}
		
	}
	
	public HomeFrame(String numeU, int flagH) throws ClassNotFoundException, SQLException{
		
		//frame setup
		numeT = numeU;
		flag = flagH;
		setVisible(true);
		setSize(1280, 800);
		setTitle("LibrarYO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setIconImage(Picon.getImage());
		getContentPane().setBackground(new Color(0x123456));
		setLocationRelativeTo(null);
		setLayout(null);
		
		logoutt.addActionListener(this);
		butonFiltru.setBounds(535, 600, 230, 80);
		butonFiltru.addActionListener(this);
		butonFiltru.setBackground(Color.GRAY);
		butonImprumuta.setBounds(15, 600, 230, 80);
		butonImprumuta.addActionListener(this);
		butonImprumuta.setBackground(Color.GRAY);
		butonDetalii.setBounds(280, 600, 230, 80);
		butonDetalii.addActionListener(this);
		butonDetalii.setBackground(Color.GRAY);
		//frame setup
		
		//labels
		background.setIcon(backgroundIMG);
		background.setBounds(0, 0, 1280, 800);
		temp.setBounds(15, 120, 750, 450);
		mesajBV.setText("Bine ai venit, " + numeU + "!");
		mesajBV.setBounds(25, 690, 350, 30);
		mesajBV.setFont(new Font("Curier", Font.BOLD, 25));
		mesajBV.setForeground(Color.WHITE);
		noutati.setIcon(noutatiI);
		noutati.setBounds(790, 0, 450, 253);
		catalogL.setIcon(catalogI);
		catalogL.setBounds(150, -25, 450, 200);
		recomandari.setIcon(recomandariI);
		recomandari.setBounds(800, 320, 450, 253);
		//labels
		
		//components
		add(background);
		background.add(temp);
		temp.add(catalogCarti);
		background.add(butonImprumuta);
		background.add(butonDetalii);
		background.add(butonFiltru);
		background.add(mesajBV);
		background.add(news);
		background.add(reco);
		background.add(noutati);
		background.add(catalogL);
		background.add(recomandari);
		setJMenuBar(mb);
		mb.add(setari);
		mb.add(logout);
		utilizator.addActionListener(this);
		logout.add(logoutt);
		logout.add(schimbaU);
		setari.add(utilizator);
		//components
		
		//tabel
		catalogCarti.setBounds(0, 0, 750, 450);
		news.setBounds(800, 170, 450, 200);
		reco.setBounds(800, 500, 450, 200);
		model.addColumn("ID");
		model.addColumn("Numele Cartii");
		model.addColumn("Gen");
		model.addColumn("Numar exemplare");
		Connection conn = ConnectingToDB();
		Statement stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery("select book_no , book_name , book_genre , book_nr from books order by rand()");
		while(res.next()) {
			model.insertRow(i, new Object[] {res.getInt("book_no"), res.getString("book_name"), res.getString("book_genre"), res.getInt("book_nr")});
			i++;
		}
		model2.addColumn("ID");
		model2.addColumn("Numele Cartii");
		model2.addColumn("Gen");
		model2.addColumn("Numar exemplare");
		res = stmt.executeQuery("select book_no , book_name , book_genre , book_nr from books order by book_no desc limit 15");
		i=0;
		while(res.next()) {
			model2.insertRow(i, new Object[] {res.getInt("book_no"), res.getString("book_name"), res.getString("book_genre"), res.getInt("book_nr")});
			i++;
		}
		if(flagH == 0) {
			model3.addColumn("ID");
			model3.addColumn("Numele Cartii");
			model3.addColumn("Gen");
			model3.addColumn("Numar exemplare");
			res = stmt.executeQuery("select user_preferinte from users where user_nickname = '" + numeU + "'");
			res.next();
			String pref = res.getString("user_preferinte");
			i=0;
			res = stmt.executeQuery("select book_no , book_name , book_genre , book_nr from books where book_genre like '%" + pref + "' order by rand() limit 15");
			while(res.next()) {
				model3.insertRow(i, new Object[] {res.getInt("book_no"), res.getString("book_name"), res.getString("book_genre"), res.getInt("book_nr")});
				i++;
			}
		}
		else if(flagH == 1) {
			i=0;
			model3.addColumn("ID");
			model3.addColumn("Numele Cartii");
			model3.addColumn("Gen");
			model3.addColumn("Numar exemplare");
			res = stmt.executeQuery("select book_no , book_name , book_genre , book_nr from books order by rand() limit 15");
			while(res.next()) {
				model3.insertRow(i, new Object[] {res.getInt("book_no"), res.getString("book_name"), res.getString("book_genre"), res.getInt("book_nr")});
				i++;
			}
		}
		conn.close();
		res.close();
		stmt.close();
		//tabel
		
		
		
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == id1) {
			id1.setText("");
		}
		if(e.getSource() == perioada) {
			perioada.setText("");
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
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
