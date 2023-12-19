package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import Controller.GestioneOrdinazioniBar;
import DAO.DAOException;
import DAO.PopolamentoDB;

class TestCheckCocktails {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		PopolamentoDB.flushDB();
		PopolamentoDB.initDB();
	
	}




	@Test
	public void TestCheckCocktailEsistente1() {
	    	ArrayList<String> lNomi = new ArrayList<String>();
	        lNomi.add("ABBEY");
	        lNomi.add("NOME_INESISTENTE");
	        String message = GestioneOrdinazioniBar.getInstance().checkCockctailEsistente(lNomi);
	        assertEquals("COCKTAIL TROVATO", message, ExitCodes.COCKTAIL_NOT_FOUND);
	}

	@Test
	public void TestCheckCocktailEsistente2() {
	    	ArrayList<String> lNomi = new ArrayList<String>();
	        lNomi.add("SAN FRANSISCO");
	        lNomi.add("ABBEY");
	        String message = GestioneOrdinazioniBar.getInstance().checkCockctailEsistente(lNomi);
	        assertEquals("COCKTAIL NON TROVATO", message, ExitCodes.COCKTAIL_FOUND);
	}











}
