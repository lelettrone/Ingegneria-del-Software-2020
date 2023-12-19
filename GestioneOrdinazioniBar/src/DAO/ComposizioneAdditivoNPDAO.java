package DAO;
//LAST
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import Entity.ComposizioneAdditivoNP;
import Test.ExitCodes;

public class ComposizioneAdditivoNPDAO {

	public static Set<ComposizioneAdditivoNP> readLista(String nomeCocktail) throws DAOException{
		
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new RuntimeException(ExitCodes.CANT_CONNECT);
		}
		
		try (
				PreparedStatement s =
				conn.prepareStatement("SELECT nomeAddNP, descrizioneQuantita FROM ComposizioniAdditiviNP WHERE nomeCocktail =  ? "))
		{
				s.setString(1, nomeCocktail);
				ResultSet rs = s.executeQuery();
				Set<ComposizioneAdditivoNP> composizioni = new HashSet<ComposizioneAdditivoNP>();
				while(rs.next()) {
					String descrizioneQuantita = rs.getString("descrizioneQuantita");
					String nome = rs.getString("nomeAddNP");
					ComposizioneAdditivoNP c = new ComposizioneAdditivoNP();
					c.setDescrizioneQuantita(descrizioneQuantita);
					c.setAddNP(AdditivoNonProporzionabileDAO.readAddNP(nome));
					composizioni.add(c);

				}
				return composizioni;
			}catch(SQLException e) {
				throw new DAOException(ExitCodes.CANT_READ_LISTCOMPADDITIVI, e);
			}
	}
	
	public static void aggiungiComposizioniAddNP(String nomeAddNP, String nomeCocktail ,String descrizioneQuantita) throws DAOException{
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new RuntimeException(ExitCodes.CANT_CONNECT);
		}
		
		try (
				PreparedStatement s =
				conn.prepareStatement
				("INSERT INTO ComposizioniAdditiviNP (nomeAddNP,nomeCocktail, descrizioneQuantita) VALUES(?,?,?) "))
		{
			s.setString(1, nomeAddNP);
			s.setString(2, nomeCocktail);
			s.setString(3, descrizioneQuantita);
			s.executeUpdate();
			
		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_ADD_COMPADDITIVI, e);
		}
	}
}
