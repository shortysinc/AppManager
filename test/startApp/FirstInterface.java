package startApp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class FirstInterface {

	private JFrame frame;
	private JTextField ip;

	/**
	 * Create the application.
	 */
	public FirstInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 439, 106);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel IpLabel = new JLabel("IP/Machine Name:");
		IpLabel.setBounds(10, 11, 108, 14);
		frame.getContentPane().add(IpLabel);
		
		JComboBox<Dirs> OptionChooser = new JComboBox<Dirs>();
		OptionChooser.setModel(new DefaultComboBoxModel<Dirs>(Dirs.values()));
		OptionChooser.setBounds(281, 8, 137, 20);
		frame.getContentPane().add(OptionChooser);
		
		ip = new JTextField();
		ip.setBounds(101, 8, 116, 20);
		frame.getContentPane().add(ip);
		ip.setColumns(10);
		
		JLabel lblProcess = new JLabel("Process:");
		lblProcess.setBounds(234, 11, 46, 14);
		frame.getContentPane().add(lblProcess);
		
		JTextArea execution = new JTextArea();
		execution.setEditable(false);
		execution.setBounds(10, 32, 207, 34);
		frame.getContentPane().add(execution);
		
		JButton SendButton = new JButton("Send");
		SendButton.setBounds(234, 39, 89, 23);
		frame.getContentPane().add(SendButton);
		
		JButton CancelButton = new JButton("Cancel");
		CancelButton.setBounds(333, 39, 89, 23);
		frame.getContentPane().add(CancelButton);
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstInterface window = new FirstInterface();
					window.frame.setVisible(true);
		
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
