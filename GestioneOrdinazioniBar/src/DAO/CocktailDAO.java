package DAO;
//LAST
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Entity.BaseAlcolica;
import Entity.Cocktail;
import Entity.Cocktail.TIPO_COCKTAIL;
import Entity.Cocktail.TIPO_GRADO;
import Entity.ComposizioneAdditivoP;
import Entity.ComposizioneBicchiere;
import Test.ExitCodes;



public class CocktailDAO{
	
	
	private static Map<String, Cocktail> persistanceMap = new java.util.HashMap<String, Cocktail>();
	
	
	private static Cocktail restoreCocktail(ResultSet rs) throws DAOException {//RESPONSE SQL->OGGETTO JAVA
		try {
			Cocktail c = new Cocktail();
			c.setNome(rs.getString("nome"));
			c.setDescrizione(rs.getString("descrizione"));
			c.setPreparazione(rs.getString("preparazione"));
			c.setColore(rs.getString("colore"));
			c.setQtaB(rs.getFloat("qtaB"));

			c.setTipo(Cocktail.TIPO_COCKTAIL.fromInteger(rs.getInt("tipo")));
			c.setOpacita(Cocktail.TIPO_OPACITA.fromInteger(rs.getInt("opacita")));
			c.setGradoAlcolico(Cocktail.TIPO_GRADO.fromInteger(rs.getInt("gradoAlcolico")));

			
			c.setBase( BaseDAO.readBase(rs.getString("nomeBase")) );
			
			c.setComposizioneBicchieri(ComposizioneBicchiereDAO.readLista(c.getNome()));
			c.setComposizioneAddP(ComposizioneAdditivoPDAO.readLista(c.getNome()));
			c.setComposizioneAddNP(ComposizioneAdditivoNPDAO.readLista(c.getNome()));
			
			
			return c;
		} catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_RESTORE_COCKTAIL, e);
		}
	}
	
	public static void aggiungiCocktail(Cocktail c) throws DAOException {
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		
		try (
				PreparedStatement s =
				conn.prepareStatement
				("INSERT INTO Cocktails (nome,colore,descrizione,preparazione,qtaB,tipo,opacita,gradoAlcolico, nomeBase) VALUES(?,?,?,?,?,?,?,?,?) "))
		{
			s.setString(1, c.getNome());
			s.setString(2, c.getColore());
			s.setString(3, c.getDescrizione());
			s.setString(4, c.getPreparazione());
			s.setFloat(5, c.getQtaB());
			
			float quantita = 0;
			for(ComposizioneAdditivoP addP: c.getComposizioneAddP()) {
				quantita = quantita+addP.getQuantita();
			}
			quantita=quantita+c.getQtaB();
			if(quantita > 0.25) {
				s.setInt(6, TIPO_COCKTAIL.LONG_DRINK.ordinal());
			} else {
				s.setInt(6, TIPO_COCKTAIL.SHORT_DRINK.ordinal());
			}
			
			s.setInt(7, c.getOpacita().ordinal());
			if(c.getBase() instanceof BaseAlcolica ) {
				BaseAlcolica b = (BaseAlcolica)c.getBase();
				float alcolTotale = (b.getGradoAlcolico()*c.getQtaB())/100;
				if( alcolTotale > 0.21*quantita ) {
					s.setInt(8, TIPO_GRADO.SUPER_ALCOLICO.ordinal());
				} else if(alcolTotale <= 0.21*quantita && alcolTotale >0 ) {
					s.setInt(8, TIPO_GRADO.ALCOLICO.ordinal());
				}    
			} else {
				s.setInt(8, TIPO_GRADO.ANALCOLICO.ordinal());
			}
			s.setInt(7, c.getOpacita().ordinal());
			s.setString(9, c.getBase().getNome());
			s.executeUpdate();
			
			
		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_ADD_COCKTAIL, e);
		}
	}
	
	public static void rimuoviCocktail(String nome) throws DAOException {
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		try(
				PreparedStatement s =
				conn.prepareStatement
				("DELETE FROM Cocktails WHERE nome = ?"))
		{
			s.setString(1, nome);
			s.executeUpdate();

			if(persistanceMap.containsKey(nome)) {
				persistanceMap.remove(nome);
			}
			
		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_REM_COCKTAIL, e);
		}
	}
	
	public static Cocktail readCocktail(String nome) throws DAOException{
		
		if(persistanceMap.containsKey(nome)) {
			return persistanceMap.get(nome);
		}
		
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		
		try (
			PreparedStatement s = 
				conn.prepareStatement("SELECT * FROM Cocktails WHERE nome=?"))
		{
			s.setString(1,nome);
			ResultSet rs = s.executeQuery();

			while(rs.next()) {
				Cocktail c;
				c = restoreCocktail(rs);
				persistanceMap.put(nome, c);
				return c;
			}
			throw new DAOException(ExitCodes.COCKTAIL_NOT_FOUND);
		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_READ_COCKTAIL, e);
		}
		
	}
	
	public static void checkCocktailsList(ArrayList<String> lNomi) throws DAOException{
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		String query = "SELECT COUNT(NOME) AS C FROM COCKTAILS WHERE NOME IN (select * from table(x VARCHAR = ?))";
		
		try (
			PreparedStatement s = 
				conn.prepareStatement(query))
		{
			s.setArray(1, conn.createArrayOf("VARCHAR", lNomi.toArray()));
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				int count = rs.getInt("C");
				if(count != lNomi.size()) {
					throw new DAOException(ExitCodes.COCKTAIL_NOT_FOUND);
				}
			}
			
		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_READ_LISTACOCKTAILS, e);
		}
	}
	
	public static ArrayList<Cocktail> readListaCocktail() throws DAOException{
		
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		try (
			PreparedStatement s = 
				conn.prepareStatement("SELECT * FROM Cocktails"))
		{
			ResultSet rs = s.executeQuery();
			ArrayList<Cocktail> listaCocktail = new ArrayList<Cocktail>();
			if(!rs.next()) {
				throw new DAOException(ExitCodes.LISTCOCKTAILS_NOT_FOUND);
			}else {
				do {
					
					Cocktail c;
					final String nome = rs.getString("nome");
					
					if (persistanceMap.containsKey(nome)) {
						c = persistanceMap.get(nome);
					} else {
						c = restoreCocktail(rs);
						persistanceMap.put(nome, c);
					}
					
					listaCocktail.add(c);
				}while(rs.next());
			}
			
			return listaCocktail;
			
		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_READ_LISTACOCKTAILS, e);
		}
	}
	
	
	
}

