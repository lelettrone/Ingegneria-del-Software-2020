package DAO;
//LAST
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.HashSet;
import Entity.ComposizioneCocktail;
import Test.ExitCodes;

public class ComposizioneCocktailDAO {


	
	public static Set<ComposizioneCocktail> readLista(int idOrdine) throws DAOException{
		
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		
		try (
				PreparedStatement s = conn.prepareStatement("SELECT nomeCocktail, numero FROM ComposizioniCocktails WHERE idOrdine =  ? "))
		{
			s.setInt(1, idOrdine);
			ResultSet rs = s.executeQuery();
			Set<ComposizioneCocktail> composizioni = new HashSet<ComposizioneCocktail>();
			
			while(rs.next()) {
				String nomeCocktail = rs.getString("nomeCocktail");
				int numero = rs.getInt("numero");
				ComposizioneCocktail c = new ComposizioneCocktail();
				c.setNumero(numero);
				c.setCocktail(CocktailDAO.readCocktail(nomeCocktail));
				composizioni.add(c);
			}
			/*if(composizioni.size()==0) {
				throw new DAOException(ExitCodes.LISTCOMPCOCKTAILS_NOT_FOUND);
			}*/
			return composizioni;
		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_READ_LISTCOMPCOCKTAILS, e);
		}
	}
	
	public static void  aggiungiComposizioneCocktail(int idOrdine, String nomeCocktail, int numero) throws DAOException {
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		
		try (
				PreparedStatement s =
				conn.prepareStatement
				("INSERT INTO ComposizioniCocktails (idOrdine, nomeCocktail, numero) VALUES(?,?,?) "))
		{
			s.setInt(1, idOrdine);
			s.setString(2, nomeCocktail);
			s.setInt(3, numero);
			s.executeUpdate();
			
		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_ADD_COMPCOCKTAIL, e);
		}
	}
	

	
}
