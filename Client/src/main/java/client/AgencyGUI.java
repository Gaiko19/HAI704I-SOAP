package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import webservice.Client;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgencyGUI extends JFrame {

	private JPanel contentPane;
	private JTextField usernameInput;
	private JPasswordField passwordInput;
	private JTextField credentialAlert;
	private JTextField agencyInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgencyGUI frame = new AgencyGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AgencyGUI() {
		setTitle("Hotel Finder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setForeground(Color.white);
		usernameLabel.setBounds(133, 162, 91, 16);
		contentPane.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.white);
		passwordLabel.setBounds(133, 209, 91, 16);
		contentPane.add(passwordLabel);
		
		JLabel loginLabel = new JLabel("Login Page");
		loginLabel.setForeground(Color.white);
		loginLabel.setBackground(new Color(255, 255, 255));
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setFont(new Font("KufiStandardGK", Font.BOLD, 20));
		loginLabel.setBounds(190, 58, 181, 29);
		contentPane.add(loginLabel);
		
		usernameInput = new JTextField();
		usernameInput.setBounds(215, 156, 167, 29);
		contentPane.add(usernameInput);
		usernameInput.setColumns(10);
		
		passwordInput = new JPasswordField();
		passwordInput.setBackground(new Color(255, 255, 255));
		passwordInput.setBounds(215, 201, 167, 29);
		contentPane.add(passwordInput);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String loginUsername = usernameInput.getText();
				String loginPassword = String.valueOf(passwordInput.getPassword());
				Agency agency = null;
				agency = MainFunctions.MakeAgence(1);
				Client client = null;
				client = MainFunctions.connectClient(loginUsername, loginPassword, agency);
				
				if(client == null) {
					credentialAlert.setText("Wrong credentials, unable to connect");
				} else {
			        new hotelFinder().setVisible(true);
				}
			}
		});
		loginBtn.setBackground(new Color(243, 254, 255));
		loginBtn.setBounds(229, 271, 117, 29);
		contentPane.add(loginBtn);
		
		credentialAlert = new JTextField();
		credentialAlert.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		credentialAlert.setHorizontalAlignment(SwingConstants.CENTER);
		credentialAlert.setBackground(new java.awt.Color(0, 0, 0, 1));
		credentialAlert.setForeground(Color.red);
		credentialAlert.setEditable(false);
		credentialAlert.setBounds(133, 242, 318, 26);
		contentPane.add(credentialAlert);
		credentialAlert.setColumns(10);
		
		JLabel agencyLabel = new JLabel("Agency Name");
		agencyLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		agencyLabel.setForeground(Color.WHITE);
		agencyLabel.setBounds(133, 117, 75, 16);
		contentPane.add(agencyLabel);
		
		agencyInput = new JTextField();
		agencyInput.setColumns(10);
		agencyInput.setBounds(215, 111, 167, 29);
		contentPane.add(agencyInput);
		
		JLabel backgroundImage = new JLabel("");
		backgroundImage.setIcon(new ImageIcon("/Users/macbook/Desktop/HAI704I-SOAP/mediaGUI/blurImage_563x373.jpeg"));
		backgroundImage.setBounds(6, 6, 563, 373);
		contentPane.add(backgroundImage);
	}
}
