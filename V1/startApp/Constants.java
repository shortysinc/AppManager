package startApp;

public class Constants {
	
	public static final String IPADDRESS_PATTERN =
    		"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
    		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
    		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
    		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$"; 
	
	public static final String initializePS = "powershell.exe Set-ExecutionPolicy Unrestricted CurrentUser -Force";
	public static final String localhost="localhost";
	public static final String emptyString="";
	public static final int MY_MINIMUM = 0;
	public static final int MY_MAXIMUM = 100;
	public static final String process_closed="Se ha cerrado correctamente\nel proceso: ";
	public static final String fail_close="Ha habido un fallo al cerrar el proceso";
	public static final String restart_process="Se ha reiniciado correctamente\nel servicio: ";
	public static final String fail_restart="Ha habido un fallo al reiniciar el proceso";
	public static final String start_process="Se ha reiniciado correctamente\nel proceso\n";
	public static final String restart_computer="Se ha lanzado un restart\ncorrectamente\n";
	public static final String restart_fail="No se ha lanzado un restart\nremoto correctamente\n";
	public static final String onelog="LoginApplication.exe";
	public static final String global_protect="pangpa.exe";
	public static final String ip_incorrect="La IP introducida no es \ncorrecta...\n";
}
