package startApp;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class FirstInterface {

	private JFrame frame;
	private JTextField ip;
	JProgressBar progressBar = new JProgressBar();

	/**
	 * Create the application.
	 */
	public FirstInterface() {
		initialize();
	}
	
	private void updateBar(int newValue) {
	    progressBar.setValue(newValue);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(FirstInterface.class.getResource("/startApp/CGP.png")));
		frame.setResizable(false);
		frame.setBounds(100, 100, 439, 132);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel IpLabel = new JLabel("IP/Machine Name:");
		IpLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		IpLabel.setBounds(10, 11, 98, 14);
		frame.getContentPane().add(IpLabel);
		
		JComboBox<Dirs> OptionChooser = new JComboBox<Dirs>();
		OptionChooser.setFont(new Font("Tahoma", Font.PLAIN, 10));
		OptionChooser.setModel(new DefaultComboBoxModel<Dirs>(Dirs.values()));
		OptionChooser.setBounds(281, 8, 137, 20);
		frame.getContentPane().add(OptionChooser);
		
		ip = new JTextField();
		ip.setFont(new Font("Tahoma", Font.PLAIN, 10));
		ip.setBounds(108, 8, 109, 20);
		frame.getContentPane().add(ip);
		ip.setColumns(10);
		
		JLabel lblProcess = new JLabel("Process:");
		lblProcess.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblProcess.setBounds(234, 11, 46, 14);
		frame.getContentPane().add(lblProcess);
		
		JTextArea execution = new JTextArea();
		execution.setFont(new Font("Monospaced", Font.PLAIN, 11));
		execution.setEditable(false);
		execution.setBounds(10, 32, 207, 60);
		frame.getContentPane().add(execution);
		
		JButton SendButton = new JButton("Send");
		SendButton.setBounds(234, 69, 89, 23);
		frame.getContentPane().add(SendButton);
		SendButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 for (int i = progressBar.getMinimum(); i <= progressBar.getMaximum(); i++) {
				      updateBar(i);
				      try {
						Thread.sleep(100);
					} catch (InterruptedException exceptionThreadPB) {
						exceptionThreadPB.printStackTrace();
					}
				 }
			}
		});
		
		JButton CancelButton = new JButton("Cancel");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		CancelButton.setBounds(329, 69, 89, 23);
		frame.getContentPane().add(CancelButton);
		
		
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setStringPainted(true);
		progressBar.setForeground(new Color(0, 128, 0));
		progressBar.setBounds(234, 39, 184, 19);
		frame.getContentPane().add(progressBar);
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
