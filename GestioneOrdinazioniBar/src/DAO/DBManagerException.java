package DAO;
import java.sql.SQLException;
//LAST
public class DBManagerException extends SQLException{
	
		
		public DBManagerException() {
			super();
		}
		public DBManagerException(String causa) {
			super(causa);
		}
		public DBManagerException(String causa, Exception e) {
			super(causa, e);
		}
}