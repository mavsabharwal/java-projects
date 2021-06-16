package App;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Choice;
import javax.swing.SwingConstants;
import java.net.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class Client {

	
	
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	static JLabel lblAwatingConnection;
	private JFrame frame;
	private JTextField textField;
	private JScrollPane scrollPane;
	static JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try
		{
			
		s=new Socket("localhost",6001);
		din=new DataInputStream(s.getInputStream());
		dout=new DataOutputStream(s.getOutputStream());
		String msgip="";
		while(true)
		{
		msgip=din.readUTF();
		textArea.append(msgip+"\n");
		}
		}
		catch(Exception ex)
		{}
		
		
	}

	/**
	 * Create the application.
	 */
	public Client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBackground(Color.BLACK);
		frame.setBounds(100, 100, 342, 494);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setForeground(Color.WHITE);
		textField.setBackground(Color.BLACK);
		textField.setBounds(10, 428, 223, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				String msgop="";
				msgop=textField.getText();
				dout.writeUTF(msgop);
				textField.setText("");
			textArea.append (msgop +"\n");
				}
				catch(Exception ex)
				{}
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setFont(new Font("Segoe Print", Font.BOLD, 13));
		btnNewButton.setBounds(238, 428, 75, 19);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Client");
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 0, 67, 23);
		frame.getContentPane().add(lblNewLabel);
		
		lblAwatingConnection = new JLabel("Awating Connection");
		lblAwatingConnection.setForeground(Color.YELLOW);
		lblAwatingConnection.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		lblAwatingConnection.setBackground(Color.BLACK);
		lblAwatingConnection.setBounds(87, 0, 146, 24);
		frame.getContentPane().add(lblAwatingConnection);
		
		JLabel lblX = new JLabel("Close");
		lblX.setHorizontalAlignment(SwingConstants.RIGHT);
		lblX.setForeground(Color.RED);
		lblX.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		lblX.setBackground(Color.BLACK);
		lblX.setBounds(258, 0, 56, 24);
		frame.getContentPane().add(lblX);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 33, 303, 385);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setForeground(Color.CYAN);
		textArea.setBackground(Color.BLACK);
		scrollPane.setViewportView(textArea);
	}
}
