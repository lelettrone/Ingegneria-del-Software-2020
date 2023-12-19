package DAO;
//LAST
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.Cocktail;
import Entity.Ordine;
import Test.ExitCodes;

public class OrdineDAO {


	public OrdineDAO() {
		
	}
	
	private static Ordine restoreOrdine(ResultSet rs) throws DAOException{
		try {
			Ordine o = new Ordine();
			o.setStato(Ordine.TIPO_STATO.fromInteger(rs.getInt("stato")));
			o.setNumeroTavolo(rs.getInt("numeroTavolo"));
			o.setId(rs.getInt("id"));
			o.setComposizioniCoktail(ComposizioneCocktailDAO.readLista(o.getId()));
			return o;
		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_RESTORE_ORDINE, e);
		}
	}
	
	public static void aggiungiOrdine(Ordine o) throws DAOException {
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		
		try (
				PreparedStatement s =
				conn.prepareStatement
				("INSERT INTO Ordini (numeroTavolo,stato) VALUES(?,?) "))
		{

			s.setInt(1, o.getNumeroTavolo());
			s.setInt(2, o.getStato().ordinal());
			s.executeUpdate();
			
			ResultSet generatedKeys = s.getGeneratedKeys();
			generatedKeys.next();
			String persistantID = generatedKeys.getString(1);
			
			o.setId(Integer.parseInt(persistantID));//SETTO ID
			
		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_ADD_ORDINE, e);
		}
	}
	
	public static void rimuoviOrdine(int id) throws DAOException {
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		try(
				PreparedStatement s =
				conn.prepareStatement
				("DELETE FROM Ordini WHERE id = ?"))
		{
			s.setInt(1, id);
			s.executeUpdate();

		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_REM_ORDINE, e);
		}
	}
	
	public static Ordine readOrdine(int id) throws DAOException{
		
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		
		try (
			PreparedStatement s = 
				conn.prepareStatement("SELECT * FROM Ordini WHERE id=?"))
		{
			s.setInt(1,id);
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				Ordine o = restoreOrdine(rs);
				return o;
			}
			throw new DAOException(ExitCodes.ORDINE_NOT_FOUND);
		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_READ_ORDINI, e);
		}
		
	}
	
}
