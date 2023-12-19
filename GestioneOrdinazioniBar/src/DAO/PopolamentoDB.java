package DAO;
//LAST


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Test.ExitCodes;

public class PopolamentoDB {

	public PopolamentoDB() {
		
	}
	
	public static void flushDB() throws DAOException{
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}
		
		try (
				PreparedStatement s =
				conn.prepareStatement
				("DELETE FROM COMPOSIZIONIADDITIVINP;\r\n"
						+ "		DELETE FROM COMPOSIZIONIADDITIVIP;\r\n"
						+ "		DELETE FROM COMPOSIZIONIBICCHIERI;\r\n"
						+ "		DELETE FROM COMPOSIZIONICOCKTAILS;\r\n"
						+ "		DELETE FROM ADDITIVINONPROPORZIONABILI;\r\n"
						+ "		DELETE FROM ADDITIVIPROPORZIONABILI;\r\n"
						+ "		DELETE FROM BICCHIERI;\r\n"
						+ "		DELETE FROM COCKTAILS;\r\n"
						+ "		DELETE FROM BASI;\r\n"
						+ "		DELETE FROM ORDINI;"))
		{

			s.executeUpdate();
			
		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_FLUSH);
		}
	}
	
	public static void initDB() throws DAOException{
		Connection conn = null;
		try {
			conn = DBManager.getInstance().getConnection();
		} catch (DBManagerException e1) {
			throw new DAOException(ExitCodes.CANT_CONNECT);
		}

		StringBuilder insertQuery = new StringBuilder();
		//BICCHIERI
		insertQuery.append("INSERT INTO BICCHIERI (descrizioneDimensione, descrizioneForma, id) "
				+ "VALUES('piccole dimensioni',"
				+ "'Coppa Martini: si presenta con una forma conica rovesciata appoggiata su uno stelo di vetro',"
				+ "'1');");
		insertQuery.append("INSERT INTO BICCHIERI (descrizioneDimensione, descrizioneForma, id) "
				+ "VALUES('Ampia la parte superiore e lo stelo stretto e lungo',"
				+ "'Coppa Champagne: come suggerito dal nome, questo bicchiere viene utilizzato soprattutto per la degustazione di Champagne',"
				+ "'2');");
		insertQuery.append("INSERT INTO BICCHIERI (descrizioneDimensione, descrizioneForma, id) "
				+ "VALUES('Di discreta capienza',"
				+ "'Collins: e'' il bicchiere ideale per servire cocktail come la Caipirinha o il Mojito',"
				+ "'3');");
		insertQuery.append("INSERT INTO BICCHIERI (descrizioneDimensione, descrizioneForma, id) "
				+ "VALUES('Si tratta del Tumbler piu capiente che esista',"
				+ "'HighBall: e'' ideale per servire long drink o liquori doppi come il Whisky',"
				+ "'4');");
		//BASI
		insertQuery.append("INSERT INTO BASI (marca, nome, gradoAlcolico, tipo) "
				+ "VALUES('APEROL SPRITZ',"
				+ "'APEROL',"
				+ "'11','1');");
		insertQuery.append("INSERT INTO BASI (marca, nome, gradoAlcolico, tipo) "
				+ "VALUES('HENDRIKS',"
				+ "'GIN',"
				+ "'5','1');");
		insertQuery.append("INSERT INTO BASI (marca, nome, gradoAlcolico, tipo) "
				+ "VALUES('LUSTAU',"
				+ "'BRANDY',"
				+ "'40','1');");
		insertQuery.append("INSERT INTO BASI (marca, nome, gradoAlcolico, tipo) "
				+ "VALUES('BRAVO',"
				+ "'SUCCO D''ARANCIA',"
				+ "'0','2');");
		//COCKTAILS
		insertQuery.append("INSERT INTO COCKTAILS (nomeBase, tipo, qtaB, preparazione, opacita, nome, gradoAlcolico, descrizione, colore) "
				+ "VALUES('APEROL','1','66','Pestate il lime direttamente dentro i bicchieri da long drink. Aggiungete il ghiaccio e successivamente l''aperol. ',"
				+ "'2','A.P RONIK','0',"
				+ "'L''Aperol e'' un aperitivo alcolico con una gradazione di 11, dal colore rosso e dal sapore dolce amaro.', 'ROSSO');");
		
		insertQuery.append("INSERT INTO COCKTAILS (nomeBase, tipo, qtaB, preparazione, opacita, nome, gradoAlcolico, descrizione, colore) "
				+ "VALUES('GIN','0','50',' Shakerare energicamente tutti gli ingredienti con del ghiaccio cristallino e versare, filtrando, in una coppetta cocktail',"
				+ "'0','ABBEY','0',"
				+ "'L''Aperol e'' un aperitivo alcolico con una gradazione di 11, dal colore rosso e dal sapore dolce amaro.', 'ROSSO');");
		insertQuery.append("INSERT INTO COCKTAILS (nomeBase, tipo, qtaB, preparazione, opacita, nome, gradoAlcolico, descrizione, colore) "
				+ "VALUES('BRANDY','0','33.5','Si prepara nello shaker con cubetti di ghiaccio. Service nella coppetta da cocktail ghiacciata',"
				+ "'1','ALEXANDER','0',"
				+ "'TI FA PERDERE LA PENSIONE', 'BIANCO');");
		insertQuery.append("INSERT INTO COCKTAILS (nomeBase, tipo, qtaB, preparazione, opacita, nome, gradoAlcolico, descrizione, colore) "
				+ "VALUES('SUCCO D''ARANCIA','0','60.5','Succo d''ananas e d''arancia, il tocco aspro del limone e infine lo sciroppo di granatina che si adagera'' sul fondo',"
				+ "'0','SAN FRANSISCO','2',"
				+ "'Ideale per gli amanti della frutta', 'ROSSO');");
		//ADDITIVINONPROPORZIONABILI
		insertQuery.append("INSERT INTO ADDITIVINONPROPORZIONABILI (descrizione, nome) "
				+ "VALUES('ARANCIONE','BUCCIA D''ARANCIA');");
		//ADDITIVIPROPORZIONABILI
		insertQuery.append("INSERT INTO ADDITIVIPROPORZIONABILI (descrizione, nome) "
				+ "VALUES('acqua tonica e'' composta da acqua addizionata ad anidride carbonica, zucchero e aromi naturali','ACQUA TONICA');");
		insertQuery.append("INSERT INTO ADDITIVIPROPORZIONABILI (descrizione, nome) "
				+ "VALUES('AMARO E SCURA','CREMA DI CACAO');");
		insertQuery.append("INSERT INTO ADDITIVIPROPORZIONABILI (descrizione, nome) "
				+ "VALUES('La crema di latte e'' anche detta panna liquida','CREMA DI LATTE');");
		insertQuery.append("INSERT INTO ADDITIVIPROPORZIONABILI (descrizione, nome) "
				+ "VALUES('DOLCE','SUCCO D''ANANAS');");
		//COMPOSIZIONIBICCHIERE
		insertQuery.append("INSERT INTO COMPOSIZIONIBICCHIERI (nomeCocktail, idBicchiere, temperatura) "
				+ "VALUES('A.P RONIK','1','15');");
		insertQuery.append("INSERT INTO COMPOSIZIONIBICCHIERI (nomeCocktail, idBicchiere, temperatura) "
				+ "VALUES('ABBEY','3','10');");
		insertQuery.append("INSERT INTO COMPOSIZIONIBICCHIERI (nomeCocktail, idBicchiere, temperatura) "
				+ "VALUES('ALEXANDER','3','20');");
		insertQuery.append("INSERT INTO COMPOSIZIONIBICCHIERI (nomeCocktail, idBicchiere, temperatura) "
				+ "VALUES('SAN FRANSISCO','2','10');");
		//COMPOSIZIONIADDITIVINP
		insertQuery.append("INSERT INTO ComposizioniAdditiviNP (nomeAddNP, nomeCocktail, descrizioneQuantita) "
				+ "VALUES('BUCCIA D''ARANCIA', 'ABBEY', 'UNA BUCCIA');");
		//COMPOSIZIONIADDITIVIP
			insertQuery.append("INSERT INTO ComposizioniAdditiviP (nomeAddP, nomeCocktail, quantita) "
					+ "VALUES('ACQUA TONICA', 'A.P RONIK', '0.4');");
			insertQuery.append("INSERT INTO ComposizioniAdditiviP (nomeAddP, nomeCocktail, quantita) "
					+ "VALUES('CREMA DI CACAO', 'ALEXANDER', '0.5');");
			insertQuery.append("INSERT INTO ComposizioniAdditiviP (nomeAddP, nomeCocktail, quantita) "
					+ "VALUES('SUCCO D''ANANAS', 'ALEXANDER', '0.3');");
			insertQuery.append("INSERT INTO ComposizioniAdditiviP (nomeAddP, nomeCocktail, quantita) "
					+ "VALUES('SUCCO D''ANANAS', 'SAN FRANSISCO', '0.43');");
		try (
				PreparedStatement s =
				conn.prepareStatement(insertQuery.toString()) )
		{
			s.executeUpdate();
		}catch(SQLException e) {
			throw new DAOException(ExitCodes.CANT_POPULATE);
		}

	}

}
