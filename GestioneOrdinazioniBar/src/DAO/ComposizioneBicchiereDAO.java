package DAO;
//LAST
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.HashSet;

import Entity.ComposizioneBicchiere;
import Test.ExitCodes;

public class ComposizioneBicchiereDAO {


	
	public static Set<ComposizioneBicchiere> readLista(String nomeCocktail) throws DAOException{
		
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		
		try (
				PreparedStatement s = conn.prepareStatement("SELECT temperatura, idBicchiere FROM ComposizioniBicchieri WHERE nomeCocktail =  ? "))
		{
			s.setString(1, nomeCocktail);
			ResultSet rs = s.executeQuery();
			Set<ComposizioneBicchiere> composizioni = new HashSet<ComposizioneBicchiere>();
			
			while(rs.next()) {
				float temp = rs.getFloat("temperatura");
				int id = rs.getInt("idBicchiere");
				ComposizioneBicchiere c = new ComposizioneBicchiere();
				c.setTemperaturaBicchiere(temp);
				c.setBicchiere(BicchiereDAO.readBicchiere(id));
				composizioni.add(c);
			}
			/*if(composizioni.size()==0) {
				throw new DAOException(ExitCodes.LISTCOMPBICCHIERI_NOT_FOUND);
			}*/
			return composizioni;

		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_READ_LISTCOMPBICCHIERI, e);
		}
	}
	
	public static void  aggiungiComposizioneBicchiere(String nomeCocktail, int idBicchiere, float temperatura) throws DAOException {
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		
		try (
				PreparedStatement s =
				conn.prepareStatement
				("INSERT INTO ComposizioniBicchieri (nomeCocktail,idBicchiere, temperatura) VALUES(?,?,?) "))
		{
			s.setString(1, nomeCocktail);
			s.setInt(2, idBicchiere);
			s.setFloat(3, temperatura);
			s.executeUpdate();
			
		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_ADD_COMPBICCHIERE, e);
		}
	}
	
	public static void rimuoviComposizioneBicchiere(String nomeCocktail, int idBicchiere) throws DAOException {
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		
		try (
				PreparedStatement s =
				conn.prepareStatement
				("DELETE FROM ComposizioniBicchieri WHERE nomeCocktail = ? AND idBicchiere = ?"))
		{
			s.setString(1, nomeCocktail);
			s.setInt(2,idBicchiere);
			
			s.executeUpdate();
		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_REM_COMPBICCHIERE, e);
		}
	}
	
}
