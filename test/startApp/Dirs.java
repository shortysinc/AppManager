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
	
    private final String Proceso;       
	
	 private Dirs(String s) {
	        this.Proceso = s;
	    }

	
	 @Override
    public String toString() {
		return this.Proceso;
    }


}

