package proiect;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainFFrame extends JFrame implements MouseListener{
		
		private ImageIcon Picon = new ImageIcon("Book_JE2_BE2.png");
		private JLabel user = new JLabel();
		private JLabel admin = new JLabel();
		private ImageIcon icon1 = new ImageIcon(new ImageIcon("14145432-valdemaras-d-7vpfyhb-j8y-unsplash_cover_1920x1217_2.jpg").getImage().getScaledInstance(640, 800, Image.SCALE_SMOOTH));
		private ImageIcon icon3 = new ImageIcon(new ImageIcon("14145432-valdemaras-d-7vpfyhb-j8y-unsplash_cover_1920x1217.jpg").getImage().getScaledInstance(640, 800, Image.SCALE_SMOOTH));
		private ImageIcon icon2 = new ImageIcon(new ImageIcon("Picture-of-the-Klosterbibliothek-in-Metten-Germany_2.jpg").getImage().getScaledInstance(640, 800, Image.SCALE_SMOOTH));
		private ImageIcon icon4 = new ImageIcon(new ImageIcon("Picture-of-the-Klosterbibliothek-in-Metten-Germany.jpg").getImage().getScaledInstance(640, 800, Image.SCALE_SMOOTH));
		private JButton userB = new JButton("User");
		private JButton adminB= new JButton("Admin");

		
		public MainFFrame() {
			
			//frame
			setLayout(null);
			setVisible(true);
			setSize(1280, 800);
			setTitle("LibrarYO");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(false);
			setLocationRelativeTo(null);	
			setIconImage(Picon.getImage());
			//frame
			
			//labels
			user.setBounds(0, 0, 640, 800);
			admin.setBounds(640, 0, 640, 800);
			user.setIcon(icon1);
			admin.setIcon(icon2);
			user.setLayout(null);
			admin.setLayout(null);
			//labels

			add(user);
			add(admin);
			user.add(userB);
			userB.setBounds(265, 375, 100, 50);
			admin.add(adminB);
			userB.setFocusable(false);
			adminB.setFocusable(false);
			adminB.setBounds(265, 375, 100, 50);
			userB.addMouseListener(this);
			adminB.addMouseListener(this);
			userB.setFont(new Font("Courier", Font.BOLD, 15));
			adminB.setFont(new Font("Courier", Font.BOLD, 15));
			userB.setBackground(Color.gray);
			adminB.setBackground(Color.gray);
			userB.setCursor(new Cursor(Cursor.HAND_CURSOR));
			adminB.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == userB) {
				dispose();
				new LogInFrame();
			}
			if(e.getSource() == adminB) {
				dispose();
				new AdminLogInFrame();
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
			if(e.getSource() == userB) {
				user.setIcon(icon3);
			}
			if(e.getSource() == adminB) {
				admin.setIcon(icon4);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource() == userB) {
				user.setIcon(icon1);
			}
			if(e.getSource() == adminB) {
				admin.setIcon(icon2);
			}
		}
}
