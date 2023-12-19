package DAO;
//LAST
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.AdditivoProporzionabile;
import Entity.Cocktail;
import Test.ExitCodes;

public class AdditivoProporzionabileDAO {

	public AdditivoProporzionabileDAO() {
		// TODO Auto-generated constructor stub
	}
	
	private static AdditivoProporzionabile restoreAddP(ResultSet rs) throws DAOException{
		try {
			AdditivoProporzionabile addP = new AdditivoProporzionabile();
			addP.setNome(rs.getString("nome"));
			addP.setDescrizione(rs.getString("descrizione"));
			return addP;
		} catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_RESTORE_ADDITIVO, e);
		}
	}
	
	public static AdditivoProporzionabile readAddP(String nome) throws DAOException{
		
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		
		try (
				PreparedStatement s = 
					conn.prepareStatement("SELECT * FROM AdditiviProporzionabili WHERE nome=?"))
			{
			s.setString(1, nome);
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				AdditivoProporzionabile addP;
				addP = restoreAddP(rs);
				return addP;
			}
			throw new DAOException(ExitCodes.ADDITIVIO_NOT_FOUND);
			}catch(SQLException e) {
				throw new DAOException(ExitCodes.CANT_READ_ADDITIVO, e);
			}
	}
	
	public static void aggiungiAdditivoProporzionabile(AdditivoProporzionabile addP) throws DAOException {
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		
		try (
				PreparedStatement s =
				conn.prepareStatement
				("INSERT INTO AdditiviProporzionabili (nome, descrizione) VALUES(?,?) "))
		{
			s.setString(1, addP.getNome());
			s.setString(2, addP.getDescrizione());
			s.executeUpdate();
		} catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_ADD_ADDITIVO, e);
		}
	}
	
	public static void rimuoviAdditivoProporzionabile(String nome) throws DAOException {
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		
		try (
				PreparedStatement s =
				conn.prepareStatement
				("DELETE FROM AdditiviProporzionabili WHERE nome = ?")){
				s.setString(1, nome);
				s.executeUpdate();
				
		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_REM_ADDITIVO, e);
		}
	}
}	