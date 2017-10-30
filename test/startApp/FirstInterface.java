package startApp;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FirstInterface {

	private JFrame KillProcesses;
	private JTextField ip;
	

	JComboBox<Dirs> OptionChooser = new JComboBox<Dirs>();
	
	
	private String command="";
	
	private Pattern pattern;
    private Matcher matcher;
    
    private static final String IPADDRESS_PATTERN =
    		"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
    		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
    		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
    		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    
	public FirstInterface(JComboBox<Dirs> optionChooser) {
		pattern = Pattern.compile(IPADDRESS_PATTERN);
		OptionChooser = optionChooser;
	}

	@SuppressWarnings("unused")
	private boolean ipValidator(final String ip){
		  matcher = pattern.matcher(ip);
		  return (matcher.matches() && !ip.equals(""));
	}
	

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
	
	
	
	/**
	 * @description: Esta función parsea las opciones de ejecucion que existe en el combobox...
	 */
	@test
	private void parser(Dirs dir, String ip) {
		switch (dir) {
		case IE:
			this.setCommand("cmd /s "+ip+" /f /im iexplore.exe");
			break;
		case EDGE:
			this.setCommand("cmd /s "+ip+" /f /im MicrosoftEdge.exe");
			break;
			
		case EXCEL:
			this.setCommand("cmd /s "+ip+" /f /im excel.exe");
			break;
		case WORD:
			this.setCommand("cmd /s "+ip+" /f /im winword.exe");
			break;
		case ONELOG:
			this.setCommand("cmd /s "+ip+" /f /im LoginApplication.exe");
			break;
		case GP:
			this.setCommand("cmd /s "+ip+" /f /im pangpa.exe");
			break;
		case CHROME:
			this.setCommand("cmd /s "+ip+" /f /im chrome.exe");
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
		KillProcesses.setBounds(100, 100, 439, 104);
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
		execution.setBounds(10, 32, 207, 32);
		KillProcesses.getContentPane().add(execution);
		
		JButton SendButton = new JButton("Send");
		SendButton.setBounds(234, 40, 89, 23);
		KillProcesses.getContentPane().add(SendButton);
		
		
		SendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//parser((Dirs) OptionChooser.getSelectedItem(), ip.getText());
				//execution.setText(command);
				//System.out.println(command);
				//String ipField=ip.getText();
				//Dirs option= (Dirs) OptionChooser.getSelectedItem();
				/*if(ipValidator(ipField)) {
					parser(option, ipField);
				}
				else {
					execution.setText("La IP introducida no es correcta...");
				}*/
			}
			
			
		});
		
		JButton CancelButton = new JButton("Cancel");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		CancelButton.setBounds(329, 40, 89, 23);
		KillProcesses.getContentPane().add(CancelButton);
	}
	

	public JComboBox<Dirs> getOptionChooser() {
		return OptionChooser;
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

	/**
	 * @return the matcher
	 */
	public Matcher getMatcher() {
		return matcher;
	}

	/**
	 * @param matcher the matcher to set
	 */
	public void setMatcher(Matcher matcher) {
		this.matcher = matcher;
	}

	/**
	 * @return the pattern
	 */
	public Pattern getPattern() {
		return pattern;
	}

	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}
	
	/**
	 * @return the ip
	 */
	public JTextField getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(JTextField ip) {
		this.ip = ip;
	}
}
