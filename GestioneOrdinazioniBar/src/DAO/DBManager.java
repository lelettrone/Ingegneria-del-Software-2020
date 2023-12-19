package DAO;
//LAST
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Test.ExitCodes;

/*
 * Gestisce la logica di connessione al DBMS.
 * 
 * Implementa il pattern sigleton:
 * 	- Il costruttore e' privato
 *  - C'e' un metodo static public getInstance() che restistuisce un riferimento all'unica istanza
 *      dell'oggetto.
 *      
 * 
 */
class DBManager {
	
	private DBManager() throws DBManagerException {
		
	}
	
	private static DBManager instance = null;
	
	public static DBManager getInstance() throws DBManagerException {
		if (instance == null) {
			try {
				Class.forName("org.h2.Driver");
			} catch (ClassNotFoundException e) {
				throw new DBManagerException(ExitCodes.CANT_LOAD_H2, e);
			}
			instance = new DBManager(); 
		}
		return instance;
	}
	
	public Connection getConnection() throws DBManagerException {
		try {
			if (connection == null || connection.isClosed()) {
				//final String pathDB_H2 = "./test";
				this.connection = DriverManager.getConnection("jdbc:h2:" + dbPath, "admin", "");
			}
		}catch(SQLException e) {
			throw new DBManagerException(ExitCodes.CANT_CONNECT, e);
		}
		
		return connection;	
	}
	
	public void closeConnection() throws DBManagerException {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}catch(SQLException e) {
			throw new DBManagerException(ExitCodes.CANT_CLOSE, e);
		}
		
	}
	
	protected Connection connection;
	protected final String dbPath = "./gestioneBar";
}
