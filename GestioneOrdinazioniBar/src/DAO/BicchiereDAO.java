package DAO;
//LAST
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Bicchiere;
import Entity.ComposizioneBicchiere;
import Test.ExitCodes;

public class BicchiereDAO {

	public BicchiereDAO() {
		// TODO Auto-generated constructor stub
	}
	
	private static Bicchiere restoreBicchiere(ResultSet rs) throws DAOException{
		Bicchiere c = new Bicchiere();
		try {
			c.setDescrizioneDimensione(rs.getString("descrizioneDimensione"));
			c.setDescrizioneForma(rs.getString("descrizioneForma"));
			c.setId(rs.getInt("id"));
		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_RESTORE_BICCHIERE, e);
		}
		return c;
	}
	
	public static Bicchiere readBicchiere(int id) throws DAOException{
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		
		try (
			PreparedStatement s = conn.prepareStatement("SELECT * FROM Bicchieri WHERE id =  ? ")){
			
			s.setInt(1, id);
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				return restoreBicchiere(rs);
			}
			
			throw new DAOException(ExitCodes.BICCHIERE_NOT_FOUND);
		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_READ_BICCHIERE, e);
		}
	}

	public static void aggiungiBicchiere(Bicchiere b) throws DAOException{
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		
		try (
				PreparedStatement s =
				conn.prepareStatement
				("INSERT INTO Bicchieri (id,descrizioneForma,descrizioneDimensione) VALUES(?,?,?) "))
		{
			s.setInt(1, b.getId());
			s.setString(2, b.getDescrizioneForma());
			s.setString(3, b.getDescrizioneDimensione());
			s.executeUpdate();
		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_ADD_BICCHIERE, e);
		}
	}
	
	public static void rimuoviBicchiere(int id) throws DAOException {
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		
		try (
				PreparedStatement s =
				conn.prepareStatement
				("DELETE FROM Bicchieri WHERE id = ?"))
		{
			s.setInt(1, id);
			s.executeUpdate();

		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_REM_BICCHIERE, e);
		}
	}
}
