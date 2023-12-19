package DAO;
//LAST
import java.sql.SQLException;

public class DAOException extends Exception {
	
	 	public DAOException() {
	  	}

	    public DAOException(String message) {
	        super(message);
	    }

	    public DAOException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public DAOException(Throwable cause) {
	        super(cause);
	    }
	    

}
