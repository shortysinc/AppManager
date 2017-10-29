/**
 * 
 */

package startApp;

/**
 * @author Jorge R.
 *
 */

public enum Dirs {
	
	IE("Internet Explorer"),
	EDGE("Edge"),
	EXCEL("Excel"),
	WORD("Word"),
	ONELOG("Onelog"),
	GP("Global Protect"),
	CHROME("Chrome");
	      
	private String dir;
	Dirs(String process)
	{
		this.setDir(process);
	}
	
	@Override
    public String toString() {
		return this.getProcess();
    }
	
	private String getProcess() {
		switch (this) {
			case CHROME:
				return this.setDir("chrome.exe");
			case EDGE:
				return this.setDir("MicrosoftEdge.exe");
			case EXCEL:
				return this.setDir("excel.exe");				
			case GP:
				return this.setDir("pangpa.exe");	
			case IE:
				return this.setDir("iexplore.exe");
			case ONELOG:
				return this.setDir("LoginApplication.exe");
			case WORD:
				return this.setDir("Winword.exe");
			default:
				return this.setDir("UNKNOWN");
		}
	}

	/**
	 * @return the dir
	 */
	public String getDir() {
		return dir;
	}

	/**
	 * @param dir the dir to set
	 */
	public String setDir(String dir) {
		this.dir = dir;
		return dir;
	}
	
	


}

