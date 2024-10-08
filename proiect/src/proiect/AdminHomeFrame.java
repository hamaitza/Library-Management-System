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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Graphics2D;
import java.awt.Cursor;

public class AdminHomeFrame extends JFrame implements ActionListener{
	
	private ImageIcon Picon = new ImageIcon("Book_JE2_BE2.png"); 
	private JLabel background = new JLabel();
	private ImageIcon backgroundIMG = new ImageIcon("hexagons-pattern-geometric-abstract-background-simple-hexagonal-elements-medical-technology-science-design-beautiful-176591444.jpg");
	private JLabel mesajBV = new JLabel();
	private DefaultTableModel model = new DefaultTableModel();
	private JTable t = new JTable(model);
	private JScrollPane catalogCarti = new JScrollPane(t);
	private DefaultTableModel model2 = new DefaultTableModel();
	private JTable t2 = new JTable(model2);
	private JScrollPane imprumut = new JScrollPane(t2);
	private DefaultTableModel model3 = new DefaultTableModel();
	private JTable t3 = new JTable(model3);
	private JScrollPane users = new JScrollPane(t3);
	private int i = 0, flag;
	private JMenuBar mb = new JMenuBar();
	private JMenu logout = new JMenu("Logout");
	private JMenuItem logoutt = new JMenuItem("Logout");
	private JMenuItem schimbaU = new JMenuItem("Schimba utilizator");
	private JButton butonAdauga = new JButton("Adauga");
	private JButton butonStare = new JButton("Stare");
	private JButton butonDetalii = new JButton("Detalii");
	private JButton butonSterge = new JButton("Sterge");
	private JOptionPane mess = new JOptionPane();
	private JLabel temp1 = new JLabel();
	private JLabel temp2 = new JLabel();
	private JButton but = new JButton("ID");
	private JButton but1 = new JButton("Carte");
	private JButton but2 = new JButton("User");
	private JTextField txt = new JTextField();
	private JFrame x;
	private JFrame det;
	private JButton modifica = new JButton("Modifica");
	private JButton status = new JButton("Status");
	private JLabel temp3 = new JLabel();
	private JButton taxe = new JButton("Taxe");
	private JTextField bnume = new JTextField("Nume");
	private JTextField bgen = new JTextField("Gen");
	private JTextField bdesc = new JTextField("Descriere");
	private JTextField bnr = new JTextField("1");
	private JTextField bautor = new JTextField("Autor");
	private JTextField blimba = new JTextField("Limba");
	private JTextField bdp = new JTextField("Data publicarii");
	private JTextField bnrp = new JTextField("Numar pagini");
	private JTextField beditura = new JTextField("Editura");
	private JTextField bISBN = new JTextField("ISBN");
	private JButton add = new JButton("Adauga");
	private JOptionPane mesaj = new JOptionPane();
	private JTextField id4 = new JTextField("ID");
	private JTextField status2 = new JTextField("Status");
	private JButton actualizeaza = new JButton("Actualizeaza");
	private JTextField id5 = new JTextField("ID");
	private JTextField taxaN = new JTextField("Taxa");
	private JButton actualizeaza2 = new JButton("Actualizeaza");
	
	private Connection ConnectingToDB () throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect", "root", "pacotheparrot22@@@");
		return con;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == logoutt) {
			dispose();
			new AdminLogInFrame();
		}
		if(e.getSource() == taxe) {
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
			xbackg.add(id5);
			xbackg.add(actualizeaza2);
			xbackg.add(taxaN);
			
			id5.setBounds(10, 20, 100, 25);
			taxaN.setBounds(130, 20, 110, 25);
			actualizeaza2.setBounds(270, 20, 120, 25);
			actualizeaza2.addActionListener(this);
			actualizeaza2.setBackground(Color.GRAY);
		}
		if(e.getSource() == actualizeaza2) {
			
			try {
				DefaultTableModel model5 = new DefaultTableModel();
				JTable t5 = new JTable(model5);
				JScrollPane yy = new JScrollPane(t5);
				model5.addColumn("ID");
				model5.addColumn("Numele");
				model5.addColumn("Prenume");
				model5.addColumn("E-mail");
				model5.addColumn("Nr. de telefon");
				model5.addColumn("Nickname");
				model5.addColumn("Preferinte");
				model5.addColumn("Parola");
				model5.addColumn("Taxe");
				Connection conn = ConnectingToDB();
				Statement stmt = conn.createStatement();
				ResultSet res = stmt.executeQuery("select * from users where user_ID = '" + Integer.parseInt(id5.getText()) + "'");
				res.next();
				stmt.execute("update users set taxe = '" + (Integer.parseInt(res.getString("taxe")) + Integer.parseInt(taxaN.getText())) + "' where user_ID = '" + Integer.parseInt(id5.getText()) + "'");
				
				i=0;
				res = stmt.executeQuery("select * from users");
				while(res.next()) {
					model5.insertRow(i, new Object[] {res.getInt("user_ID"), res.getString("user_name"), res.getString("user_surname"), res.getString("user_email"), res.getString("user_mbnr"), res.getString("user_nickname"), res.getString("user_preferinte"), res.getString("user_pass"), res.getString("taxe")});
					i++;
				}
				yy.setBounds(0, 0, 700, 410);
				temp1.removeAll();
				temp1.add(yy);
				temp1.revalidate();
				temp1.repaint();
				
				conn.close();
				stmt.close();
				res.close();
				
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == butonAdauga) {
			x = new JFrame();
			JLabel xbackg = new JLabel();
			x.setVisible(true);
			x.setSize(500, 800);
			x.setTitle("LibrarYO");
			x.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			x.setResizable(false);
			x.setIconImage(Picon.getImage());
			x.getContentPane().setBackground(new Color(0x123456));
			x.setLocationRelativeTo(null);
			x.setLayout(null);
			xbackg.setIcon(backgroundIMG);
			xbackg.setBounds(0, 0, 500, 800);
			x.add(xbackg);
			
			xbackg.add(bnume);
			xbackg.add(bgen);
			xbackg.add(bdesc);
			xbackg.add(bnr);
			xbackg.add(bautor);
			xbackg.add(blimba);
			xbackg.add(bdp);
			xbackg.add(bnrp);
			xbackg.add(beditura);
			xbackg.add(bISBN);
			xbackg.add(add);
			
			bnume.setBounds(150, 25, 200, 50);
			bgen.setBounds(150, 90, 200, 50);
			bdesc.setBounds(150, 155, 200, 50);
			bnr.setBounds(150, 220, 200, 50);
			bautor.setBounds(150, 285, 200, 50);
			blimba.setBounds(150, 350, 200, 50);
			bdp.setBounds(150, 415, 200, 50);
			bnrp.setBounds(150, 480, 200, 50);
			beditura.setBounds(150, 545, 200, 50);
			bISBN.setBounds(150, 610, 200, 50);
			add.setBounds(150, 675, 200, 50);
			add.setBackground(Color.GRAY);
			add.addActionListener(this);
		}
		if(e.getSource() == add) {
			try {
				DefaultTableModel model4 = new DefaultTableModel();
				JTable t4 = new JTable(model4);
				JScrollPane y = new JScrollPane(t4);
				model4.addColumn("ID");
				model4.addColumn("Numele Cartii");
				model4.addColumn("Gen");
				model4.addColumn("Descriere");
				model4.addColumn("Numar exemplare");
				model4.addColumn("Autor");
				model4.addColumn("Limba");
				model4.addColumn("Data Publicarii");
				model4.addColumn("Nr. pagini");
				model4.addColumn("Editura");
				model4.addColumn("ISBN");
				Connection conn = ConnectingToDB();
				Statement stmt = conn.createStatement();
				ResultSet res;
				res = stmt.executeQuery("select * from books where book_name like '%" + bnume.getText() + "%'");
				if(res.next()) {
					mesaj.showMessageDialog(null, "Cartea exista deja in baza de date", "Eroare", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					y.setBounds(0, 0, 1200, 220);
					temp3.removeAll();
					stmt.execute("insert into books values(default, '" + bnume.getText()+  "', '" + bdesc.getText() + "' , '" + bgen.getText() + "', '" + Integer.parseInt(bnr.getText()) + "', '" + bautor.getText() + "', '" + blimba.getText() + "', '" + bdp.getText() + "', '" + bnrp.getText() + "', '" + beditura.getText() + "', '" + bISBN.getText() + "')");
					res = stmt.executeQuery("select * from books");
					i=0;
					while(res.next()) {
						model4.insertRow(i, new Object[] {res.getInt("book_no"), res.getString("book_name"), res.getString("book_genre"), res.getString("book_desc"), res.getInt("book_nr"), res.getString("autor"), res.getString("limba"), res.getString("data_publicarii"), res.getString("nr_pagini"), res.getString("editura"), res.getString("ISBN")});
						i++;
						System.out.println(i);
					}
					temp3.add(y);
					temp3.revalidate();
					temp3.repaint();
				}
				conn.close();
				stmt.close();
				res.close();
				
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		if(e.getSource() == status) {
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
				model4.addColumn("User_ID");
				model4.addColumn("ID_Carte");
				model4.addColumn("Perioada");
				model4.addColumn("Data_Returnare");
				model4.addColumn("Status");
				Connection conn = ConnectingToDB();
				Statement stmt = conn.createStatement();
				stmt.execute("update imprumut set Status = '" + status2.getText() + "' where ID = '" + Integer.parseInt(id4.getText()) + "'");
				ResultSet res = stmt.executeQuery("select * from imprumut order by ID DESC");
				i=0;
				while(res.next()) {
					model4.insertRow(i, new Object[] {res.getInt("ID"), res.getString("Nume"), res.getString("Prenume"), res.getString("User_ID"), res.getString("ID_Carte"), res.getString("Perioada"), res.getString("Data_Returnare"), res.getString("Status")});
					i++;
				}
				y.setBounds(0, 0, 700, 410);
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
		
	}
	
	public AdminHomeFrame(String numeU, int flagH) throws ClassNotFoundException, SQLException{
		
		//frame setup
		flag = flagH;
		setVisible(true);
		setSize(1500, 800);
		setTitle("LibrarYO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setIconImage(Picon.getImage());
		getContentPane().setBackground(new Color(0x123456));
		setLocationRelativeTo(null);
		setLayout(null);
		
		logoutt.addActionListener(this);
		butonAdauga.setBounds(710, 20, 69, 45);
		butonAdauga.addActionListener(this);
		butonAdauga.setBackground(Color.GRAY);
		butonSterge.setBounds(710, 85, 69, 45);
		butonSterge.addActionListener(this);
		butonSterge.setBackground(Color.GRAY);
		butonDetalii.setBounds(710, 160, 69, 45);
		butonDetalii.addActionListener(this);
		butonDetalii.setBackground(Color.GRAY);
		modifica.setBounds(710, 235, 69, 45);
		modifica.addActionListener(this);
		modifica.setBackground(Color.GRAY);
		taxe.setBounds(710, 385, 69, 45);
		taxe.addActionListener(this);
		taxe.setBackground(Color.GRAY);
		taxe.setFont(new Font("Curier", Font.BOLD, 11));
		status.setBounds(710, 310, 69, 45);
		status.addActionListener(this);
		status.setBackground(Color.GRAY);
		status.setFont(new Font("Curier", Font.BOLD, 11));
		modifica.setFont(new Font("Curier", Font.BOLD, 8));
		butonDetalii.setFont(new Font("Curier", Font.BOLD, 11));
		butonAdauga.setFont(new Font("Curier", Font.BOLD, 9));
		butonSterge.setFont(new Font("Curier", Font.BOLD, 9));
		//frame setup
		
		//labels
		temp1.setBounds(10, 20, 700, 410);
		temp2.setBounds(780, 20, 700, 410);
		temp3.setBounds(130, 450, 1200, 220);
		background.setIcon(backgroundIMG);
		background.setBounds(0, 0, 1500, 800);
		mesajBV.setText("Bine ai venit, " + numeU + "!");
		mesajBV.setBounds(25, 690, 350, 30);
		mesajBV.setFont(new Font("Curier", Font.BOLD, 25));
		mesajBV.setForeground(Color.BLACK);
	
		//labels
		
		//components
		add(background);
		background.add(temp1);
		background.add(temp2);
		background.add(temp3);
		background.add(status);
		background.add(taxe);
		background.add(butonAdauga);
		background.add(butonDetalii);
		background.add(butonSterge);
		background.add(mesajBV);
		background.add(modifica);
		temp1.add(users);
		temp2.add(imprumut);
		temp3.add(catalogCarti);
		setJMenuBar(mb);
		mb.add(logout);
		logout.add(logoutt);
		logout.add(schimbaU);

		//components
		
		//tabel
		catalogCarti.setBounds(0, 0, 1200, 220);
		imprumut.setBounds(0, 0, 700, 410);
		users.setBounds(0, 0, 700, 410);
		model.addColumn("ID");
		model.addColumn("Numele Cartii");
		model.addColumn("Gen");
		model.addColumn("Descriere");
		model.addColumn("Numar exemplare");
		model.addColumn("Autor");
		model.addColumn("Limba");
		model.addColumn("Data Publicarii");
		model.addColumn("Nr. pagini");
		model.addColumn("Editura");
		model.addColumn("ISBN");
		Connection conn = ConnectingToDB();
		Statement stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery("select * from books");
		while(res.next()) {
			model.insertRow(i, new Object[] {res.getInt("book_no"), res.getString("book_name"), res.getString("book_genre"), res.getString("book_desc"), res.getInt("book_nr"), res.getString("autor"), res.getString("limba"), res.getString("data_publicarii"), res.getString("nr_pagini"), res.getString("editura"), res.getString("ISBN")});
			i++;
		}
		model2.addColumn("ID");
		model2.addColumn("Numele");
		model2.addColumn("Prenume");
		model2.addColumn("User_ID");
		model2.addColumn("ID_Carte");
		model2.addColumn("Perioada");
		model2.addColumn("Data_Returnare");
		model2.addColumn("Status");
		res = stmt.executeQuery("select * from imprumut order by ID DESC");
		i=0;
		while(res.next()) {
			model2.insertRow(i, new Object[] {res.getInt("ID"), res.getString("Nume"), res.getString("Prenume"), res.getString("User_ID"), res.getString("ID_Carte"), res.getString("Perioada"), res.getString("Data_Returnare"), res.getString("Status")});
			i++;
		}
		model3.addColumn("ID");
		model3.addColumn("Numele");
		model3.addColumn("Prenume");
		model3.addColumn("E-mail");
		model3.addColumn("Nr. de telefon");
		model3.addColumn("Nickname");
		model3.addColumn("Preferinte");
		model3.addColumn("Parola");
		model3.addColumn("Taxe");
		res = stmt.executeQuery("select * from users");
		i=0;
		while(res.next()) {
			model3.insertRow(i, new Object[] {res.getInt("ID"), res.getString("user_name"), res.getString("user_surname"), res.getString("user_email"), res.getString("user_mbnr"), res.getString("user_nickname"), res.getString("user_pass"), res.getString("user_preferinte"), res.getString("taxe")});
			i++;
		}
		conn.close();
		res.close();
		stmt.close();
		//tabel
		
		
		
		
	}

	
}

