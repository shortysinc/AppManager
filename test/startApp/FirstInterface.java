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
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class FirstInterface {

	private JFrame KillProcesses;
	private JTextField ip= new JTextField();
	JTextArea execution = new JTextArea();
	JCheckBox chckbxServiceOn = new JCheckBox("Service On");
	

	JCheckBox chckbxServiceOff = new JCheckBox("Service Off");

	JComboBox<Dirs> OptionChooser = new JComboBox<Dirs>();
	
	
	private String command, ServiceON, ServiceOff="";
	
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

	private boolean ipValidator(final String ip){
		  matcher = pattern.matcher(ip);
		  return (matcher.matches() && !ip.equals(""));
	}
	

	private String getCommand() {
		return this.command;
	}
	
	private void setCommand(String command) {
		this.command=command;
		
	}
	
	/**
	 * @return the chckbxServiceOn
	 */
	public JCheckBox getChckbxServiceOn() {
		return chckbxServiceOn;
	}

	/**
	 * @param chckbxServiceOn the chckbxServiceOn to set
	 */
	public void setChckbxServiceOn(JCheckBox chckbxServiceOn) {
		this.chckbxServiceOn = chckbxServiceOn;
	}

	/**
	 * @return the chckbxServiceOff
	 */
	public JCheckBox getChckbxServiceOff() {
		return chckbxServiceOff;
	}

	/**
	 * @param chckbxServiceOff the chckbxServiceOff to set
	 */
	public void setChckbxServiceOff(JCheckBox chckbxServiceOff) {
		this.chckbxServiceOff = chckbxServiceOff;
	}
	
	/**
	 * Create the application.
	 */
	public FirstInterface() throws Exception {
		pattern = Pattern.compile(IPADDRESS_PATTERN);
		initialize();
	}
	
	
	
	/**
	 * @description: Esta función parsea las opciones de ejecucion que existe en el combobox...
	 */
	@test
	private void parser(Dirs dir, String ip) {
		switch (dir) {
		case IE:
			this.setCommand("taskkill /s "+ip+" /f /im iexplore.exe");
			break;
		case EDGE:
			this.setCommand("taskkill /s "+ip+" /f /im MicrosoftEdge.exe");
			break;
			
		case EXCEL:
			this.setCommand("taskkill /s "+ip+" /f /im excel.exe");
			break;
		case WORD:
			this.setCommand("taskkill /s "+ip+" /f /im winword.exe");
			break;
		case ONELOG:
			//this.setCommand("taskkill /s "+ip+" /f /im LoginApplication.exe");
			this.setCommand("");
			this.setServiceOff("sc \\\\"+ip+" stop \"ITS Onelog Client\"");
			this.setServiceON("sc \\\\"+ip+" start  \"ITS Onelog Client\"");
			break;
		case GP:
			//this.setCommand("taskkill /s "+ip+" /f /im pangpa.exe");
			//this.setCommand("sc \\\\"+ip+" stop pangps && timeout 5 && sc \\\\"+ip+" start pangps");
			this.setCommand("");
			this.setServiceOff("sc \\\\"+ip+" stop pangps");
			this.setServiceON("sc \\\\"+ip+" start pangps");
			break;
		case CHROME:
			this.setCommand("taskkill /s "+ip+" /f /im chrome.exe");
			break;
		default:
			break;
		}
	}
	
	private void ExecuteCMD() {
		String commandRemote = this.getCommand();
		//System.out.println(commandRemote);
		//String command = "powershell Rename-Item -path " + path.toString() + " -NewName "+ path.toString()+".old" + " -force ";
		try {
			Process CMDProcess = Runtime.getRuntime().exec(commandRemote);
			CMDProcess.getOutputStream().close();
			execution.append("\nSe ha cerrado correctamente\nel proceso");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Ha habido un fallo al cerrar el proceso");
			//e.getStackTrace();
		}
	}
	
	private void ExecuteService() {
		String ServiceON = this.getServiceON();
		String ServiceOff = this.getServiceOff();
		//System.out.println(ServiceOff);
		//System.out.println(ServiceON);
		try {
			
			Process s_Off = Runtime.getRuntime().exec(ServiceOff);
			System.out.println(s_Off.getOutputStream());
			s_Off.getOutputStream().close();
			
			
			Thread.sleep(5000); //Pausa durante 5 segundos...
			
			Process s_ON = Runtime.getRuntime().exec(ServiceON);
			s_ON.getOutputStream().close();
			execution.append("Se ha reiniciado correctamente\nel proceso");
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Ha habido un fallo al cerrar el proceso");
			//e.getStackTrace();
		}
	}
	
	@test
	private boolean isService() {
		 return getCommand().equals("");
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		KillProcesses = new JFrame();
		KillProcesses.getContentPane().setForeground(Color.LIGHT_GRAY);
		KillProcesses.getContentPane().setBackground(Color.LIGHT_GRAY);
		KillProcesses.setForeground(Color.ORANGE);
		KillProcesses.setTitle("Kill Processes");
		KillProcesses.setIconImage(Toolkit.getDefaultToolkit().getImage(FirstInterface.class.getResource("/startApp/CGP.png")));
		KillProcesses.setResizable(false);
		KillProcesses.setBounds(100, 100, 472, 133);
		KillProcesses.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		KillProcesses.getContentPane().setLayout(null);
		
		JLabel IpLabel = new JLabel("IP/Machine Name:");
		IpLabel.setForeground(Color.BLACK);
		IpLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		IpLabel.setBounds(10, 11, 98, 14);
		KillProcesses.getContentPane().add(IpLabel);
		
		
		OptionChooser.setToolTipText("\r\n");
		OptionChooser.setFont(new Font("Tahoma", Font.PLAIN, 10));
		OptionChooser.setModel(new DefaultComboBoxModel<Dirs>(Dirs.values()));
		OptionChooser.setBounds(317, 8, 137, 20);
		KillProcesses.getContentPane().add(OptionChooser);
		
		
		ip.setFont(new Font("Tahoma", Font.PLAIN, 10));
		ip.setBounds(108, 8, 150, 20);
		KillProcesses.getContentPane().add(ip);
		ip.setColumns(10);
		
		JLabel lblProcess = new JLabel("Process:");
		lblProcess.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblProcess.setBounds(270, 11, 46, 14);
		KillProcesses.getContentPane().add(lblProcess);
		
		
		execution.setFont(new Font("Monospaced", Font.PLAIN, 11));
		execution.setEditable(false);
		execution.setBounds(10, 32, 248, 66);
		KillProcesses.getContentPane().add(execution);
		
		JButton SendButton = new JButton("Send");
		SendButton.setBounds(270, 75, 89, 23);
		KillProcesses.getContentPane().add(SendButton);
		
		
		SendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parser((Dirs) OptionChooser.getSelectedItem(), ip.getText());
				execution.setText(command);
				//System.out.println(command);
				String ipField=ip.getText().replaceAll("\\s+", "");
				Dirs option= (Dirs) OptionChooser.getSelectedItem();
				if(ipValidator(ipField)) {
					if(isService()) { //falla aquí1
						parser(option, ipField);
						ExecuteService();
					}
					else {
						parser(option, ipField);
						ExecuteCMD();
					}
				}
				else {
					execution.setText("La IP introducida no es \ncorrecta...");
				
			}
			
			
		}});
		
		JButton CancelButton = new JButton("Exit");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		CancelButton.setBounds(365, 75, 89, 23);
		KillProcesses.getContentPane().add(CancelButton);
		
		
		chckbxServiceOn.setBackground(Color.LIGHT_GRAY);
		chckbxServiceOn.setBounds(270, 45, 89, 18);
		KillProcesses.getContentPane().add(chckbxServiceOn);
		
		
		chckbxServiceOff.setBackground(Color.LIGHT_GRAY);
		chckbxServiceOff.setBounds(365, 45, 89, 18);
		KillProcesses.getContentPane().add(chckbxServiceOff);
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
	 * @return the serviceON
	 */
	public String getServiceON() {
		return ServiceON;
	}

	/**
	 * @param serviceON the serviceON to set
	 */
	public void setServiceON(String serviceON) {
		ServiceON = serviceON;
	}

	/**
	 * @return the serviceOff
	 */
	public String getServiceOff() {
		return ServiceOff;
	}

	/**
	 * @param serviceOff the serviceOff to set
	 */
	public void setServiceOff(String serviceOff) {
		ServiceOff = serviceOff;
	}
}
