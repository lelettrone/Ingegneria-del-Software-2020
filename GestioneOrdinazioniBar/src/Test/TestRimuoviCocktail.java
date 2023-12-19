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

class TestRimuoviCocktail {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		PopolamentoDB.flushDB();
		PopolamentoDB.initDB();
	
	}



	@Test
	public void TestRimuoviCocktail1() {
	    try {
	        GestioneOrdinazioniBar.getInstance().rimuoviCocktail("NOME_INESISTENTE");
	        fail();
	    } catch(DAOException ex) {
	        assertEquals("COCKTAIL PRESENTE", ex.getMessage(), ExitCodes.COCKTAIL_NOT_FOUND);
	    }
	}









}
