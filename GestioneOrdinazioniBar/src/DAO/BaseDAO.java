package DAO;
//LAST
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Base;
import Entity.BaseAlcolica;
import Entity.BaseAnalcolica;
import Entity.Cocktail;
import Test.ExitCodes;


public class BaseDAO {
	
	private static java.util.Map<String, Base> persistanceMap = new java.util.HashMap<String, Base>();

	
	public BaseDAO() {
		// TODO Auto-generated constructor stub
	}
	
	private static Base restoreBase(ResultSet rs) throws DAOException {
		try {
			if(rs.getInt("tipo")==1) { // BASE ALCOLICA
				BaseAlcolica ba = new BaseAlcolica();
				ba.setNome(rs.getString("nome"));
				ba.setMarca(rs.getString("marca"));
				ba.setGradoAlcolico(rs.getFloat("gradoAlcolico"));
				return ba;
			}else if(rs.getInt("tipo")==2) {
				BaseAnalcolica ba = new BaseAnalcolica();
				ba.setNome(rs.getString("nome"));
				ba.setMarca(rs.getString("marca"));
				return ba;
			}else {
				throw new DAOException(ExitCodes.WRONG_TIPO_BASE);
			}
		} catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_RESTORE_BASE, e);
		}
	}
	
	public static Base readBase(String nome)throws DAOException {
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
					conn.prepareStatement("SELECT * FROM Basi WHERE nome = ?") )
			{
				s.setString(1, nome);
				ResultSet rs = s.executeQuery();
				while(rs.next()) {
					Base b = restoreBase(rs);
					persistanceMap.put(nome, b);
					return b;
				}
				throw new DAOException(ExitCodes.BASE_NOT_FOUND);
			}catch(SQLException e) {
				throw new DAOException(ExitCodes.CANT_READ_BASE, e);
			}
	}
	
	public static void aggiungiBase(Base b) throws DAOException {
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		
		try (
				PreparedStatement s = 
					conn.prepareStatement("INSERT INTO Basi (nome, marca, gradoAlcolico, tipo) VALUES(?,?,?,?)") )
			{
				s.setString(1, b.getNome());
				s.setString(2, b.getMarca());
				if(b instanceof BaseAlcolica) {
					
					s.setFloat(3, ((BaseAlcolica)b).getGradoAlcolico());
					s.setInt(4, 1);
				}else if(b instanceof BaseAnalcolica){
					s.setFloat(3, 0);
					s.setInt(4, 2);
				}
				s.executeUpdate();
			}catch(SQLException e) {
				throw new DAOException(ExitCodes.CANT_ADD_BASE, e);
			}
	}
	
	public static void rimuoviBase(String nome) throws DAOException{
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		
		try (
				PreparedStatement s = 
					conn.prepareStatement("DELETE FROM Basi WHERE nome = ?") )
			{
				s.setString(1, nome);
				s.executeUpdate();
				if(persistanceMap.containsKey(nome)) {
					persistanceMap.remove(nome);
				}
			}catch(SQLException e) {
				throw new DAOException(ExitCodes.CANT_REM_BASE, e);
			}
	}
	
	
	
	
}
