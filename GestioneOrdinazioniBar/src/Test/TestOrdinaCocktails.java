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

class TestOrdinaCocktails {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		PopolamentoDB.flushDB();
		PopolamentoDB.initDB();
	
	}



	@Test 
	public void TestOrdina1() {
	    try {
	    	ArrayList<String> lNomi = new ArrayList<String>();
	        lNomi.add("ABBEY");
	        lNomi.add("NOME_INESISTENTE");
	        ArrayList<Integer> lQnt = new ArrayList<Integer>();
	        lQnt.add(1);
	        lQnt.add(2);
	        GestioneOrdinazioniBar.getInstance().ordinaCocktails(lNomi, lQnt, 1);
	        fail();
	    } catch(DAOException ex) {
	        assertEquals("ORDINE CREATO", ex.getMessage(), ExitCodes.CANT_ADD_COMPCOCKTAIL);
	    }
	}


}
