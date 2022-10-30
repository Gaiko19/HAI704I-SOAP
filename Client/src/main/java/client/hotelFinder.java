package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class hotelFinder extends JFrame {

	private JPanel contentPane;
	private JTextField connectedDisplay;
	public String connectedClientName = ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hotelFinder frame = new hotelFinder();
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
	public hotelFinder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel connectedLabel = new JLabel("Client connecté");
		contentPane.add(connectedLabel);
		
		connectedDisplay = new JTextField();
		connectedDisplay.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		connectedDisplay.setBackground(new java.awt.Color(0, 0, 0, 1));
		connectedDisplay.setEditable(false);
		contentPane.add(connectedDisplay);
		connectedDisplay.setColumns(10);
	}

}
