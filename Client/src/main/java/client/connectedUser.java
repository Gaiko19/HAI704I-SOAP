package client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import webservice.Client;
import webservice.Hotel;

import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class connectedUser extends JDialog {

	public final JPanel contentPanel = new JPanel();
	static JTextField nameInput;
	private JTextField destinationInput;
	private JTextField startDateInput;
	private JTextField endDateInput;
	private JTextField minPriceInput;
	private JTextField maxPriceInput;
	private JTextField bedNumbersInput;
	private JTextField ratingInput;
	private JLabel endDateLabel;
	private JLabel priceLabel;
	private JLabel bedNumbersLabel;
	private JLabel ratingLabel;
	private JLabel minPriceLabel;
	private JLabel maxPriceLabel;
	private JButton searchBtn;
	static JTextField agencyInput;
	static JTextField loginUser;
	static JTextField passwordUser;
	static JTextField agencyDisplay;
	private JTextField foundHotelInput;
	private JLabel foundHotelLabel;
	private JTextField roomChoiceInput;
	private JLabel roomChoiceLabel;
	private JLabel roomImage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			connectedUser dialog = new connectedUser();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public connectedUser() throws MalformedURLException, IOException {
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			nameInput = new JTextField();
			nameInput.setHorizontalAlignment(SwingConstants.LEFT);
			nameInput.setEditable(false);
			nameInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			nameInput.setBackground(new java.awt.Color(0, 0, 0, 1));
			nameInput.setBounds(6, 3, 92, 26);
			contentPanel.add(nameInput);
			nameInput.setColumns(10);
		}
		
		JButton logOutBtn = new JButton("Log Out");
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connectedUser.this.dispose();
			}
		});
		logOutBtn.setBackground(new Color(255, 255, 255));
		logOutBtn.setForeground(UIManager.getColor("CheckBox.select"));
		logOutBtn.setBounds(344, 3, 100, 29);
		contentPanel.add(logOutBtn);
		
		destinationInput = new JTextField();
		destinationInput.setBounds(132, 44, 130, 26);
		contentPanel.add(destinationInput);
		destinationInput.setColumns(10);
		
		JLabel startDateLabel = new JLabel("Start Date");
		startDateLabel.setForeground(new Color(255, 255, 255));
		startDateLabel.setBounds(34, 74, 86, 24);
		contentPanel.add(startDateLabel);
		
		JLabel destinationLabel = new JLabel("Destination");
		destinationLabel.setForeground(new Color(255, 255, 255));
		destinationLabel.setBounds(34, 45, 86, 24);
		contentPanel.add(destinationLabel);
		
		startDateInput = new JTextField();
		startDateInput.setColumns(10);
		startDateInput.setBounds(132, 73, 130, 26);
		contentPanel.add(startDateInput);
		startDateInput.setText("2022-01-01");
		
		endDateInput = new JTextField();
		endDateInput.setColumns(10);
		endDateInput.setBounds(132, 102, 130, 26);
		contentPanel.add(endDateInput);
		endDateInput.setText("2022-01-10");
		
		minPriceInput = new JTextField();
		minPriceInput.setColumns(10);
		minPriceInput.setBounds(142, 131, 68, 26);
		contentPanel.add(minPriceInput);
		
		maxPriceInput = new JTextField();
		maxPriceInput.setColumns(10);
		maxPriceInput.setBounds(266, 131, 68, 26);
		contentPanel.add(maxPriceInput);
		
		bedNumbersInput = new JTextField();
		bedNumbersInput.setColumns(10);
		bedNumbersInput.setBounds(132, 166, 130, 26);
		contentPanel.add(bedNumbersInput);
		
		ratingInput = new JTextField();
		ratingInput.setColumns(10);
		ratingInput.setBounds(142, 204, 130, 26);
		contentPanel.add(ratingInput);
		
		endDateLabel = new JLabel("End Date");
		endDateLabel.setForeground(new Color(255, 255, 255));
		endDateLabel.setBounds(34, 103, 86, 24);
		contentPanel.add(endDateLabel);
		
		priceLabel = new JLabel("Price");
		priceLabel.setForeground(new Color(255, 255, 255));
		priceLabel.setBounds(34, 132, 58, 24);
		contentPanel.add(priceLabel);
		
		bedNumbersLabel = new JLabel("Bed Numbers");
		bedNumbersLabel.setForeground(new Color(255, 255, 255));
		bedNumbersLabel.setBounds(34, 168, 86, 24);
		contentPanel.add(bedNumbersLabel);
		
		ratingLabel = new JLabel("Minimum Rating");
		ratingLabel.setForeground(new Color(255, 255, 255));
		ratingLabel.setBounds(34, 204, 116, 24);
		contentPanel.add(ratingLabel);
		
		minPriceLabel = new JLabel("Min");
		minPriceLabel.setForeground(new Color(255, 255, 255));
		minPriceLabel.setBounds(104, 132, 86, 24);
		contentPanel.add(minPriceLabel);
		
		maxPriceLabel = new JLabel("Max");
		maxPriceLabel.setForeground(new Color(255, 255, 255));
		maxPriceLabel.setBounds(224, 132, 86, 24);
		contentPanel.add(maxPriceLabel);
		
		JButton purchaseBtn = new JButton("Purchase this room");
		purchaseBtn.setVisible(false);
		purchaseBtn.setForeground(new Color(46, 139, 87));
		purchaseBtn.setBackground(Color.WHITE);
		purchaseBtn.setBounds(34, 224, 176, 26);
		contentPanel.add(purchaseBtn);
		
		JSeparator separator = new JSeparator();
		separator.setVisible(false);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(224, 49, 19, 201);
		contentPanel.add(separator);
		
		JComboBox hotelChoice = new JComboBox();
		hotelChoice.setModel(new DefaultComboBoxModel(new String[] {}));
		hotelChoice.setVisible(false);
		hotelChoice.setBounds(34, 92, 130, 27);
		contentPanel.add(hotelChoice);
		
		JLabel roomInfosLabel = new JLabel("Room Infos");
		roomInfosLabel.setVisible(false);
		roomInfosLabel.setForeground(new Color(255, 255, 255));
		roomInfosLabel.setHorizontalAlignment(SwingConstants.CENTER);
		roomInfosLabel.setBounds(274, 78, 139, 16);
		contentPanel.add(roomInfosLabel);
		
		JSeparator infosSeparator = new JSeparator();
		infosSeparator.setVisible(false);
		infosSeparator.setBounds(294, 92, 100, 12);
		contentPanel.add(infosSeparator);
				
		searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String city = destinationInput.getText();
				String startDate = startDateInput.getText();
				String endDate = endDateInput.getText();
				int bedNumbers = Integer.valueOf(bedNumbersInput.getText());
				int minPrice = Integer.valueOf(minPriceInput.getText());
				int maxPrice = Integer.valueOf(maxPriceInput.getText());
				Double rating = Double.valueOf(ratingInput.getText());
				
				String loginUsername = loginUser.getText();
				String loginPassword = passwordUser.getText();
				String agencyId = "";
				Agency agency2 = new Agency();
				agencyId = agencyInput.getText();
				Agency agency = null;
				if(agencyId.equals("HotelAdvisor.com")) {
					agency = MainFunctions.MakeAgence(1);
				} else if (agencyId.equals("Hotel.net")) {
					agency = MainFunctions.MakeAgence(2);
				} else {
					agency = MainFunctions.MakeAgence(3);
				}
				Client client = null;
				client = MainFunctions.connectClient(loginUsername, loginPassword, agency);
				
				if(client == null) {
					connectedUser.this.dispose();
				}
				
				HashMap<Hotel, Double> hotels = MainFunctions.research(agency, city, bedNumbers, startDate, endDate, minPrice, maxPrice, rating);
				if(hotels.isEmpty()) {
					JOptionPane.showMessageDialog(null, 
	                        "Erreur, aucune correspondance", 
	                        "Research Exception", 
	                        JOptionPane.WARNING_MESSAGE);
				} else {
					
					int cpt = 0;
					for (Hotel key : hotels.keySet()) {
						hotelChoice.addItem(key.getName());
						cpt++;
					}
					
					foundHotelInput.setText(String.valueOf(cpt));
					
					destinationInput.setVisible(false);
					startDateInput.setVisible(false);
					endDateInput.setVisible(false);
					bedNumbersInput.setVisible(false);
					minPriceInput.setVisible(false);
					maxPriceInput.setVisible(false);
					ratingInput.setVisible(false);
					searchBtn.setVisible(false);
					
					destinationLabel.setVisible(false);
					startDateLabel.setVisible(false);
					endDateLabel.setVisible(false);
					bedNumbersLabel.setVisible(false);
					minPriceLabel.setVisible(false);
					maxPriceLabel.setVisible(false);
					ratingLabel.setVisible(false);
					priceLabel.setVisible(false);
					
					foundHotelInput.setVisible(true);
					foundHotelLabel.setVisible(true);
					purchaseBtn.setVisible(true);
					separator.setVisible(true);
					hotelChoice.setVisible(true);
					roomChoiceInput.setVisible(true);
					roomChoiceLabel.setVisible(true);
				
					roomChoiceLabel.setVisible(true);
					roomInfosLabel.setVisible(true);
					infosSeparator.setVisible(true);
					roomImage.setVisible(true);
				}
			}
		});
		searchBtn.setForeground(new Color(46, 139, 87));
		searchBtn.setBackground(Color.WHITE);
		searchBtn.setBounds(312, 224, 106, 26);
		contentPanel.add(searchBtn);
		
		agencyInput = new JTextField();
		agencyInput.setVisible(false);
		agencyInput.setBackground(new java.awt.Color(0, 0, 0, 1));
		agencyInput.setEnabled(false);
		agencyInput.setEditable(false);
		agencyInput.setBounds(423, 44, 19, 26);
		contentPanel.add(agencyInput);
		agencyInput.setColumns(10);
		
		loginUser = new JTextField();
		loginUser.setBackground(new java.awt.Color(0, 0, 0, 1));
		loginUser.setEnabled(false);
		loginUser.setVisible(false);
		loginUser.setEditable(false);
		loginUser.setColumns(10);
		loginUser.setBounds(425, 73, 19, 26);
		contentPanel.add(loginUser);
		
		passwordUser = new JTextField();
		passwordUser.setBackground(new java.awt.Color(0, 0, 0, 1));
		passwordUser.setVisible(false);
		passwordUser.setEnabled(false);
		passwordUser.setEditable(false);
		passwordUser.setColumns(10);
		passwordUser.setBounds(423, 102, 19, 26);
		contentPanel.add(passwordUser);
		
		agencyDisplay = new JTextField();
		agencyDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		agencyDisplay.setEditable(false);
		agencyDisplay.setColumns(10);
		agencyDisplay.setBounds(155, 3, 130, 26);
		contentPanel.add(agencyDisplay);
		agencyDisplay.setBackground(new java.awt.Color(0, 0, 0, 1));
		agencyDisplay.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		foundHotelInput = new JTextField();
		foundHotelInput.setVisible(false);
		foundHotelInput.setEditable(false);
		foundHotelInput.setColumns(10);
		foundHotelInput.setBounds(132, 44, 58, 26);
		contentPanel.add(foundHotelInput);
		
		foundHotelLabel = new JLabel("Hotels Found :");
		foundHotelLabel.setVisible(false);
		foundHotelLabel.setForeground(Color.WHITE);
		foundHotelLabel.setBounds(34, 49, 116, 24);
		contentPanel.add(foundHotelLabel);
		
		roomChoiceInput = new JTextField();
		roomChoiceInput.setVisible(false);
		roomChoiceInput.setColumns(10);
		roomChoiceInput.setBounds(366, 44, 40, 26);
		contentPanel.add(roomChoiceInput);
		roomChoiceInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		roomChoiceInput.setBackground(new java.awt.Color(0, 0, 0, 1));
		
		roomChoiceLabel = new JLabel("Room Number");
		roomChoiceLabel.setVisible(false);
		roomChoiceLabel.setForeground(new Color(255, 255, 255));
		roomChoiceLabel.setVisible(false);
		roomChoiceLabel.setBounds(266, 49, 92, 16);
		contentPanel.add(roomChoiceLabel);
		
		roomImage = new JLabel("");
		roomImage.setVisible(false);
		roomImage.setIcon(new ImageIcon("/Users/macbook/Desktop/HAI704I-SOAP/mediaGUI/HotelAdvisor.com_450x231.jpg"));
		roomImage.setBounds(255, 111, 176, 78);
		contentPanel.add(roomImage);
		
		JLabel connectedBackgroundImage = new JLabel("");
		connectedBackgroundImage.setBounds(0, 36, 450, 236);
		contentPanel.add(connectedBackgroundImage);
		BufferedImage img = ImageIO.read(new URL("http://hotelfinder.sc1samo7154.universe.wf/blurImage_563x373.jpeg"));
		connectedBackgroundImage.setIcon(new ImageIcon(img));
		
		
		
		
		
		
	}
}
