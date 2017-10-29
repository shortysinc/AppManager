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
import javax.swing.SwingUtilities;

import java.awt.Toolkit;
import java.awt.Window.Type;

public class FirstInterface {

	private JFrame KillProcesses;
	private JTextField ip;
	JProgressBar progressBar = new JProgressBar();
	JComboBox<Dirs> OptionChooser = new JComboBox<Dirs>();
	private String command="";

	public String getCommand() {
		return this.command;
	}
	
	public void setCommand(String command) {
		this.command=command;
		
	}
	
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
	 * @description: Esta función parsea las opciones de ejecucion que existe en el combobox...
	 */
	@test
	private void parser(Dirs dir, String ip) {
		switch (dir) {
		case IE:
			this.setCommand("cmd /s "+ip+" /f /im"+ dir.toString());
			break;
		case EDGE:
			
			break;
			
		case EXCEL:
			
			break;
		case WORD:
			
			break;
		case ONELOG:
			
			break;
		case GP:
			
			break;
		case CHROME:
			
			break;
		default:
			break;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		KillProcesses = new JFrame();
		KillProcesses.setForeground(Color.ORANGE);
		KillProcesses.setTitle("Kill Processes");
		KillProcesses.setIconImage(Toolkit.getDefaultToolkit().getImage(FirstInterface.class.getResource("/startApp/CGP.png")));
		KillProcesses.setResizable(false);
		KillProcesses.setBounds(100, 100, 439, 132);
		KillProcesses.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		KillProcesses.getContentPane().setLayout(null);
		
		JLabel IpLabel = new JLabel("IP/Machine Name:");
		IpLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		IpLabel.setBounds(10, 11, 98, 14);
		KillProcesses.getContentPane().add(IpLabel);
		
		
		OptionChooser.setToolTipText("\r\n");
		OptionChooser.setFont(new Font("Tahoma", Font.PLAIN, 10));
		OptionChooser.setModel(new DefaultComboBoxModel<Dirs>(Dirs.values()));
		OptionChooser.setBounds(281, 8, 137, 20);
		KillProcesses.getContentPane().add(OptionChooser);
		
		ip = new JTextField();
		ip.setFont(new Font("Tahoma", Font.PLAIN, 10));
		ip.setBounds(108, 8, 109, 20);
		KillProcesses.getContentPane().add(ip);
		ip.setColumns(10);
		
		JLabel lblProcess = new JLabel("Process:");
		lblProcess.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblProcess.setBounds(234, 11, 46, 14);
		KillProcesses.getContentPane().add(lblProcess);
		
		JTextArea execution = new JTextArea();
		execution.setFont(new Font("Monospaced", Font.PLAIN, 11));
		execution.setEditable(false);
		execution.setBounds(10, 32, 207, 60);
		KillProcesses.getContentPane().add(execution);
		
		JButton SendButton = new JButton("Send");
		SendButton.setBounds(234, 69, 89, 23);
		KillProcesses.getContentPane().add(SendButton);
		/*
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
		});*/
		
		SendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = progressBar.getMinimum(); i <= progressBar.getMaximum(); i++) {
				      final int percent = i;
				      try {
				    	  SwingUtilities.invokeLater(new Runnable() {
				          public void run() {
				        	  updateBar(percent);
				          }
				        });
				        java.lang.Thread.sleep(100);
				      } catch (InterruptedException exc) {
				        ;
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
		KillProcesses.getContentPane().add(CancelButton);
		
		
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setStringPainted(true);
		progressBar.setForeground(new Color(0, 128, 0));
		progressBar.setBounds(234, 39, 184, 19);
		KillProcesses.getContentPane().add(progressBar);
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstInterface window = new FirstInterface();
					window.KillProcesses.setVisible(true);
		
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
