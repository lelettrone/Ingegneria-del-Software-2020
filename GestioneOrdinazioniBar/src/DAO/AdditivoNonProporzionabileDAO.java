package DAO;
//LAST
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.AdditivoNonProporzionabile;
import Entity.AdditivoProporzionabile;
import Test.ExitCodes;


public class AdditivoNonProporzionabileDAO {

	public AdditivoNonProporzionabileDAO() {
		// TODO Auto-generated constructor stub
	}
	
	private static AdditivoNonProporzionabile restoreAddNP(ResultSet rs) throws DAOException{
		try {
			AdditivoNonProporzionabile addNP = new AdditivoNonProporzionabile();
			addNP.setNome(rs.getString("nome"));
			addNP.setDescrizione(rs.getString("descrizione"));
			return addNP;
		} catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_RESTORE_ADDITIVO, e);
		}
	}
	public static AdditivoNonProporzionabile readAddNP(String nome) throws DAOException{
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		
		try (
				PreparedStatement s = 
					conn.prepareStatement("SELECT * FROM AdditiviNonProporzionabili WHERE nome=?"))
			{
			s.setString(1, nome);
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				AdditivoNonProporzionabile addNP;
				addNP = restoreAddNP(rs);
				return addNP;
			}
			throw new DAOException(ExitCodes.ADDITIVIO_NOT_FOUND);
			}catch(SQLException e) {
				throw new DAOException(ExitCodes.CANT_READ_ADDITIVO, e);
			}
	}
	
	public static void aggiungiAdditivoNonProporzionabile(AdditivoNonProporzionabile addP) throws DAOException {
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		
		try (
				PreparedStatement s =
				conn.prepareStatement
				("INSERT INTO AdditiviNonProporzionabili (nome, descrizione) VALUES(?,?) "))
		{
			s.setString(1, addP.getNome());
			s.setString(2, addP.getDescrizione());
			s.executeUpdate();
		} catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_ADD_ADDITIVO, e);
		}
	}
	
	public static void rimuoviAdditivoNonProporzionabile(String nome) throws DAOException {
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		
		try (
				PreparedStatement s =
				conn.prepareStatement
				("DELETE FROM AdditiviNonProporzionabili WHERE nome = ?")){
				s.setString(1, nome);
				s.executeUpdate();
				
		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_REM_ADDITIVO, e);
		}
	}
}
