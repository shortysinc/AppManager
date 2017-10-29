/**
 * 
 */

package startApp;

/**
 * @author Jorge R.
 *
 */

public enum Dirs {
	
	Dir1("Internet Explorer"),
	Dir2("Edge"),
	Dir3("Excel"),
	Dir4("Word"),
	Dir5("Onelog"),
	Dir6("Global Protect"),
	Dir7("Chrome");
	;
	
    private final String Proceso;       
	
	 private Dirs(String s) {
	        this.Proceso = s;
	    }

	
	 @Override
    public String toString() {
		return this.Proceso;
    }


}

