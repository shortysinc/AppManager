package startApp;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
/**
 * 
 * @author Jorge R.
 * @version 1.0
 * @ipPruebas 10.33.162.232
 *
 */

public class FirstInterface {

	private JFrame KillProcesses;
	private JTextField ip= new JTextField();
	JTextArea execution = new JTextArea();
	JComboBox<Dirs> OptionChooser = new JComboBox<Dirs>();
	JProgressBar progressBar = new JProgressBar();
	static final int MY_MINIMUM = 0;
	static final int MY_MAXIMUM = 100;
	private String command,service, ServiceON;
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

    
    private void initializePS() {
		String command = "powershell.exe Set-ExecutionPolicy Unrestricted CurrentUser -Force";
		try {		
			Process powerShellProcess = Runtime.getRuntime().exec(command);
			powerShellProcess.getOutputStream().close();
			//System.out.println("Execution Policy Updated!");
		}
		catch (Exception e){
			
		}
	}
    
	private boolean ipValidator(final String ip){
		  matcher = pattern.matcher(ip);
		  return ((matcher.matches() && !ip.equals("")) || ip.equals("localhost"));
	}
	

	private String getCommand() {
		return this.command;
	}
	
	
	private void setCommand(String command) {
		this.command=command;
		
	}
	
	
	/**
	 * @return the serviceON
	 */
	private String getServiceON() {
		return ServiceON;
	}

	
	/**
	 * @return the progressBar
	 */
	public JProgressBar getProgressBar() {
		return progressBar;
	}

	
	/**
	 * @param progressBar the progressBar to set
	 */
	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}
	
	
	/**
	 * Create the application.
	 */
	public FirstInterface() throws Exception {
		pattern = Pattern.compile(IPADDRESS_PATTERN);
		initialize();
	}
	
	
	public void updateBar(int newValue) {
	    progressBar.setValue(newValue);
	}
	
	
	private void progressBarUpdate() {
		for (int i = MY_MINIMUM; i <= MY_MAXIMUM; i++) {
			final int percent = i;
			try {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						updateBar(percent);
					}
				});
				Thread.sleep(5);
			} 
			catch (InterruptedException e) {
				
			}
		}
	}
	
	
	public JComboBox<Dirs> getOptionChooser() {
		return OptionChooser;
	}
	
	/**
	 * @description: Esta función parsea las opciones de ejecucion que existe en el combobox...
	 */
	/*
	@TESTFUNCTIONS
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
		case Lync:
			this.setCommand("taskkill /s "+ip+" /f /im lync.exe");
			break;
		default:
			break;
		}
	}
	*/
	
	private void parser(Dirs dir, String ip) {
		switch (dir) {
		case IE:
			this.setCommand("iexplore.exe");
			break;
		case EDGE:
			this.setCommand("MicrosoftEdge.exe");
			break;
			
		case EXCEL:
			this.setCommand("excel.exe");
			break;
		case WORD:
			this.setCommand("winword.exe");
			break;
		case ONELOG:
			//this.setCommand("taskkill /s "+ip+" /f /im LoginApplication.exe");
			this.setCommand("LoginApplication.exe");
			//this.setServiceOff("sc \\\\"+ip+" stop \"ITS Onelog Client\"");
			//this.setServiceON("sc \\\\"+ip+" start  \"ITS Onelog Client\"");
			this.setService("\"ITS Onelog Client\"");
			break;
		case GP:
			//this.setCommand("taskkill /s "+ip+" /f /im pangpa.exe");
			//this.setCommand("sc \\\\"+ip+" stop pangps && timeout 5 && sc \\\\"+ip+" start pangps");
			this.setCommand("pangpa.exe");
			//this.setServiceOff("sc \\\\"+ip+" stop pangps");
			//this.setServiceON("sc \\\\"+ip+" start pangps");
			this.setService("pangps");
			break;
		case CHROME:
			this.setCommand("chrome.exe");
			break;
		case Lync:
			this.setCommand("lync.exe");
			break;
		case ND:
			this.setCommand("ndoffice.exe");
			break;
		case OUTLOOK:
			this.setCommand("outlook.exe");
			break;
		case REKOOP:
			this.setCommand("sllauncher.exe");
		default:
			break;
		}
	}



	private void ExecuteCMD(String ipField) {
		String commandRemote = "taskkill /s "+ipField+" /f /im "+this.getCommand();
		//System.out.println(commandRemote);
		try {
			progressBarUpdate();
			String commandOutput=OptionChooser.getSelectedItem().toString();
			Process CMDProcess = Runtime.getRuntime().exec(commandRemote);
			CMDProcess.getOutputStream().close();
			execution.append("Se ha cerrado correctamente\nel proceso: "+commandOutput+"\n");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Ha habido un fallo al cerrar el proceso");
			//e.getStackTrace();
		}
	}
	
	private void restartService(String ipField) {
		/*
		String ServiceON = this.getServiceON();
		String ServiceOff = this.getServiceOff();
		try {
			
			//sc \\10.33.162.232 stop pangps && timeout 5 && sc \\10.33.162.232 start pangps
			Process s_Off = Runtime.getRuntime().exec(ServiceOff);
			//System.out.println(s_Off.getOutputStream());
			s_Off.getOutputStream().close();
			
			progressBarUpdate();
			Thread.sleep(4500); //Pausa durante 5 segundos...
			
			Process s_ON = Runtime.getRuntime().exec(ServiceON);
			s_ON.getOutputStream().close();
			execution.append("Se ha reiniciado correctamente\nel proceso\n");
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Ha habido un fallo al cerrar el proceso");
			//e.getStackTrace();
		}*/
		String service=this.getService();
		try {
			String commandRemote = "powershell \"Get-Service -Name "+service+" -ComputerName "+ipField+" | Restart-Service\"";
			progressBarUpdate();
			String commandOutput=OptionChooser.getSelectedItem().toString();
			Process CMDProcess = Runtime.getRuntime().exec(commandRemote);
			CMDProcess.getOutputStream().close();
			execution.append("Se ha reiniciado correctamente\nel servicio: "+commandOutput+"\n");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Ha habido un fallo al reiniciar el proceso");
		}
	}
	
	private void startService() {
		String ServiceON = this.getServiceON();
		try {
			progressBarUpdate();
			Thread.sleep(4500); //Pausa durante 4,5 segundos...
			
			Process s_ON = Runtime.getRuntime().exec(ServiceON);
			s_ON.getOutputStream().close();
			execution.append("Se ha reiniciado correctamente\nel proceso\n");
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Ha habido un fallo al cerrar el proceso");
			//e.getStackTrace();
		}
	}
	
	@TESTFUNCTIONS
	private void restartComputer() {
		String commandRemote = this.getCommand();
		try {
			progressBarUpdate();
			Process CMDProcess = Runtime.getRuntime().exec(commandRemote);
			CMDProcess.getOutputStream().close();
			execution.append("Se ha lanzado un restart\ncorrectamente\n");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,"No se ha lanzado un restart\nremoto correctamente\n");
			//e.getStackTrace();
		}
	}
	
	private boolean isService() {
		 return getCommand().equalsIgnoreCase("LoginApplication.exe") || getCommand().equalsIgnoreCase("pangpa.exe") || getCommand().equalsIgnoreCase("");
		
	}
	
	@TESTFUNCTIONS
	private boolean checkServiceRunning(String service) throws IOException {
		String query="cmd /c \"sc query "+service+" | find /I \"RUNNING\"\"";
		//System.out.println(query);
		boolean info=true;
		Process process = Runtime.getRuntime().exec(query);
		//----------------------------------------------------------
		String line,newline = "";
		BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
		while ((line = stdout.readLine()) != null) {
			newline=newline+"\n"+line;
		}
		//System.out.println("Línea: "+newline.toString().contains("STOPPED"));
		stdout.close();
		if (newline.toString().contains("STOPPED")) {
			info=false; //Parado
		}
		else
			info=true; //Activo
		//----------------------------------------------------------
		//System.out.println(info);
		return info;
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.initializePS();
		
		KillProcesses = new JFrame();
		KillProcesses.getContentPane().setForeground(Color.LIGHT_GRAY);
		KillProcesses.getContentPane().setBackground(Color.LIGHT_GRAY);
		KillProcesses.setForeground(Color.LIGHT_GRAY);
		KillProcesses.setTitle("Task/Service Manager");
		KillProcesses.setIconImage(Toolkit.getDefaultToolkit().getImage(FirstInterface.class.getResource("/startApp/CGP.png")));
		KillProcesses.setResizable(false);
		KillProcesses.setBounds(100, 100, 494, 115);
		KillProcesses.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		KillProcesses.getContentPane().setLayout(null);
		
		JLabel IpLabel = new JLabel("IP:");
		IpLabel.setForeground(Color.BLACK);
		IpLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		IpLabel.setBounds(10, 11, 18, 12);
		KillProcesses.getContentPane().add(IpLabel);
		OptionChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateBar(MY_MINIMUM);
				execution.setText("");
			}
		});
		
		OptionChooser.setMaximumRowCount(10);
		OptionChooser.setToolTipText("\r\n");
		OptionChooser.setFont(new Font("Tahoma", Font.PLAIN, 10));
		OptionChooser.setModel(new DefaultComboBoxModel<Dirs>(Dirs.values()));
		OptionChooser.setBounds(317, 8, 161, 20);
		KillProcesses.getContentPane().add(OptionChooser);
		ip.setForeground(new Color(0, 0, 0));
		ip.setBackground(Color.WHITE);
		
		
		ip.setFont(new Font("Tahoma", Font.PLAIN, 10));
		ip.setBounds(29, 8, 229, 20);
		KillProcesses.getContentPane().add(ip);
		ip.setColumns(10);
		
		JLabel lblProcess = new JLabel("Process:");
		lblProcess.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblProcess.setBounds(270, 11, 46, 14);
		KillProcesses.getContentPane().add(lblProcess);
		
		
		execution.setFont(new Font("Monospaced", Font.PLAIN, 11));
		execution.setEditable(false);
		execution.setBounds(10, 32, 248, 43);
		KillProcesses.getContentPane().add(execution);
		
		JButton SendButton = new JButton("Send");
		SendButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		SendButton.setBounds(270, 52, 60, 23);
		KillProcesses.getContentPane().add(SendButton);
		
		
		SendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//execution.setText(command);
				//System.out.println(command);
				
				execution.setText("");
				String ipField=ip.getText().replaceAll("\\s+", "");
				Dirs option= (Dirs) OptionChooser.getSelectedItem();
				parser(option, ipField);
				//System.out.println(isService());
				if(ipValidator(ipField)) {
					if(isService()) { 
						//parser(option, ipField);
						//restartService();
						try {
							if(checkServiceRunning(getCommand())) {
								//restartService();
								restartService(ipField);
							}
							else {
								startService();
							}
						} 
						catch (Exception Failure) {
							JOptionPane.showMessageDialog(null,Failure.getMessage());
						}
					}
					else {
						//parser(option, ipField);
						ExecuteCMD(ipField);
					}
				}
				else {
					execution.setText("La IP introducida no es \ncorrecta...\n");
				
			}
			
			
		}});
		
		JButton CancelButton = new JButton("Exit");
		CancelButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		CancelButton.setBounds(418, 52, 60, 23);
		KillProcesses.getContentPane().add(CancelButton);
		progressBar.setForeground(new Color(0, 128, 0));
		progressBar.setStringPainted(true);
		progressBar.setBackground(Color.WHITE);
		
		
		progressBar.setBounds(270, 32, 208, 18);
		KillProcesses.getContentPane().add(progressBar);
		
		JButton restartButton = new JButton("Restart");
		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ipField=ip.getText().replaceAll("\\s+", "");
				setCommand("shutdown /m \\\\"+ip.getText()+" /r /f /t 00");
				if(ipValidator(ipField)) {
					restartComputer();
				}
				else {
					execution.setText("No se ha podido lanzar\nel restart remoto\n");
				}
			}
		});
		restartButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		restartButton.setBounds(340, 52, 69, 23);
		KillProcesses.getContentPane().add(restartButton);
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
	 * @return the service
	 */
	public String getService() {
		return service;
	}


	/**
	 * @param service the service to set
	 */
	public void setService(String service) {
		this.service = service;
	}
}
