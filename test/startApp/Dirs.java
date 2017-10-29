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
	Dir2("Edge");
	
    private final String Proceso;       
	
	 private Dirs(String s) {
	        this.Proceso = s;
	    }

	
	 @Override
    public String toString() {
		return this.Proceso;
    }


}

