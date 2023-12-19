package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controller.GestioneOrdinazioniBar;
import DAO.DAOException;
import DAO.PopolamentoDB;

class TestVisualizzaCocktails {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		PopolamentoDB.flushDB();
	}


	@Test
	public void TestVisualizzaCocktails1() {
	    try {
	        GestioneOrdinazioniBar.getInstance().visuallizaCocktails();
	        fail();
	    } catch(DAOException ex) {
	        assertEquals("LISTA NON VUOTA", ex.getMessage(), ExitCodes.LISTCOCKTAILS_NOT_FOUND);
	    }
	}
	
}
