package DAO;
//LAST
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import Entity.ComposizioneAdditivoP;
import Entity.ComposizioneBicchiere;
import Test.ExitCodes;

public class ComposizioneAdditivoPDAO {

	public ComposizioneAdditivoPDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static Set<ComposizioneAdditivoP> readLista(String nomeCocktail) throws DAOException{
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		try (
				PreparedStatement s =									
				conn.prepareStatement("SELECT nomeAddP, quantita FROM ComposizioniAdditiviP WHERE nomeCocktail =  ? "))
		{
				s.setString(1, nomeCocktail);
				ResultSet rs = s.executeQuery();
				Set<ComposizioneAdditivoP> composizioni = new HashSet<ComposizioneAdditivoP>();
				while(rs.next()) {
					float quantita = rs.getFloat("quantita");
					String nome = rs.getString("nomeAddP");
					ComposizioneAdditivoP c = new ComposizioneAdditivoP();
					c.setQuantita(quantita);
					c.setAddP(AdditivoProporzionabileDAO.readAddP(nome));
					composizioni.add(c);
				}
				return composizioni;
			}catch(SQLException e) {
				throw new DAOException(ExitCodes.CANT_READ_LISTCOMPADDITIVI, e);
			}
	}
	
	public static void aggiungiComposizioniAddP(String nomeAddP, String nomeCocktail ,float quantita) throws DAOException{
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		
		try (
				PreparedStatement s =
				conn.prepareStatement
				("INSERT INTO ComposizioniAdditiviP (nomeAddP,nomeCocktail, quantita) VALUES(?,?,?) "))
		{
			s.setString(1, nomeAddP);
			s.setString(2, nomeCocktail);
			s.setFloat(3, quantita);
			s.executeUpdate();
			
		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_ADD_COMPADDITIVI, e);
		}
	}
	
	
}
