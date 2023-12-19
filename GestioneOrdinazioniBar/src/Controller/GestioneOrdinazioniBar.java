package Controller;
//LAST
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Boundary.MaitreB;
import DAO.*;
import Entity.*;
import Test.ExitCodes;

public class GestioneOrdinazioniBar {

	private GestioneOrdinazioniBar() {
		// TODO Auto-generated constructor stub
	}
	private static GestioneOrdinazioniBar bar;
	
	public static GestioneOrdinazioniBar getInstance(){
			if (bar == null)
				bar = new GestioneOrdinazioniBar();
			return bar;
	}

	public ArrayList<Cocktail> visuallizaCocktails() throws DAOException{
			return CocktailDAO.readListaCocktail();
	}
	
	public String checkCockctailEsistente(ArrayList<String> lNomi)  {
		try {
			CocktailDAO.checkCocktailsList(lNomi);
			return ExitCodes.COCKTAIL_FOUND;
		}catch(DAOException ex) {
			return ex.getMessage();
		}
	}


	public void ordinaCocktails(ArrayList<String> lNomi, ArrayList<Integer> lQnt, int numeroTavolo) throws DAOException{

			Ordine o = new Ordine();
			o.setNumeroTavolo(numeroTavolo);
			o.setStato(Ordine.TIPO_STATO.NON_PRONTO);
			
			OrdineDAO.aggiungiOrdine(o);//lo faccio qui, per ottenere l'id
			
			//////////////////RIMUOVERLO
			
			for(int i=0;i<lNomi.size(); i++) {
				ComposizioneCocktailDAO.aggiungiComposizioneCocktail(o.getId(), lNomi.get(i), lQnt.get(i));
			}
					
	}
	
	public void rimuoviCocktail(String nomeCocktail) throws DAOException{
				CocktailDAO.readCocktail(nomeCocktail);
				//se il cocktail non esiste fa da solo throw DAOEXception cocktail_not_found
				CocktailDAO.rimuoviCocktail(nomeCocktail);
				
			
			
	}
	
	private void inviaNotifica(String messaggio) {
		MaitreB.getInstance().pushNotification(messaggio);

	}
	
	public void suggerisciCocktail(String nome,String descrizione, String preparazione,String base, String additivi) {
		inviaNotifica("Suggerimento aggiunta: "
					+ "\n\tNome: "+nome
					+ "\n\tDescrizione: "+ 	descrizione
					+ "\n\tPreparazione: " + preparazione
					+ "\n\tBase: " + base
					+ "\n\tAdditivi: " + additivi+"\n");
	}

}
