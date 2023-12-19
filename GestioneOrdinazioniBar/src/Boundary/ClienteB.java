package Boundary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

import Controller.GestioneOrdinazioniBar;
import DAO.DAOException;
import Entity.*;
import Test.ExitCodes;
public class ClienteB {
	
	java.io.BufferedReader inputReader;
	public ClienteB() {
		inputReader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
	}
	
	protected String askUser(String message) throws IOException {
		System.out.print(message);
		System.out.flush();
		return inputReader.readLine();
	}
	
	public  void showBoundary() {
		
		int option = 0;
			try {
				
				do {
					System.out.println("Seleziona opzione:\n"
							+ "\t1. Visualizza cocktails\n"
							+ "\t2. Ordina cocktails\n"
							+ "\t3. Suggerisci cocktail\n"
							+ "\t4. EXIT\n");
							
					System.out.flush();
					
					try {
						option = Integer.parseInt(inputReader.readLine());
					} catch (NumberFormatException e) {
						option = 0;
					}
					
					switch (option) {
						case 1: { 
							System.err.println(visualizzaCocktails()); break; }
						case 2: { 
						    int numeroTavolo = (int)( Math.random()*20);
						    //considerando l'assenza dell'effettivo id di un tablet
						    //utilizziamo la funzione random per generarne uno
							//non potendo simulare il caso in cui da uno stesso tablet
						    //provengano più ordini

							System.err.println(ordinaCocktails(numeroTavolo)); 
							break; }
						case 3: { System.err.println(suggerisciCocktail());  break; }
						case 4: { System.out.println("The End\n"); break; }
						default: {
							System.err.println(ExitCodes.CHARACTER_NOT_RECO);
						}
					}
				} while (option != 4);
				
			}catch(IOException ex) {
				System.err.println(ExitCodes.INPUT_ERROR);
			}
		}
	
	public String visualizzaCocktails() {//-1 LISTA VUOTA, 0 OK, 1 DAOException
		ArrayList<Cocktail> lCocktails;
		try {
			lCocktails = GestioneOrdinazioniBar.getInstance().visuallizaCocktails();
			int i = 1;
			System.out.println("------------------------------------------------");
			for(Cocktail c : lCocktails) {
				//System.out.print(i + ". ");
				System.out.println(c.getNome()+"\n\t"+c.getDescrizione());
				i++;
				System.out.println("------------------------------------------------");
			}
			return ExitCodes.CLIENTB_VISUALIZZA_CORRECT;
		} catch (DAOException e) {
			return e.getMessage();
		}
		
		
	}

	public String suggerisciCocktail() {
		String nome, descrizione, preparazione, base, additivi;
		ArrayList<String> lAdditivi;
		
		System.out.println("INSERIRE NOME, DESCRIZIONE, PREPARAZIONE, BASE, ADDITIVI(separati da ,)");
		try {
			nome = askUser("NOME: ");
			if(nome.isBlank())return ExitCodes.CLIENTEB_NOME_EMPTY;
			if(nome.length()>25)return ExitCodes.CLIENTEB_NOME_LEN_OVERFLOW;
			
			
			descrizione = askUser("DESCRIZIONE: ");
			if(descrizione.length()>150)return ExitCodes.CLIENTEB_DESCRIZIONE_LEN_OVERFLOW;
			
			preparazione = askUser("PREPARAZIONE: ");
			if(preparazione.length()>150)return ExitCodes.CLIENTEB_PREPARAZIONE_LEN_OVERFLOW;
			
			base = askUser("BASE: ");
			if(base.isBlank())return ExitCodes.CLIENTEB_BASE_EMPTY;
			if(base.length()>20)return ExitCodes.CLIENTEB_BASE_LEN_OVERFLOW;
			if(!Pattern.matches("[a-zA-Z]+", base))return ExitCodes.CLIENTEB_BASE_FORMAT_ERR;
			
			additivi = askUser("ADDITIVI: ");
			lAdditivi = new ArrayList<String>(Arrays.asList(additivi.toUpperCase().split(",")));
			if(additivi.isBlank())return ExitCodes.CLIENTEB_LADDITIVI_EMPTY;
			for(String a: lAdditivi) 
				if(!Pattern.matches("[a-zA-Z]+", a)) 
					return ExitCodes.CLIENTEB_ADDITIVO_FORMAT_ERR;
			
			if( new HashSet<String>(lAdditivi).size() != lAdditivi.size() ) {
				return ExitCodes.CLIENTEB_ADDITIVO_REPLICATED;
			}
			
			GestioneOrdinazioniBar.getInstance().suggerisciCocktail(nome, descrizione, preparazione,base, additivi);
			
			return ExitCodes.CLIENTB_SUGGERISCI_CORRECT;
		}catch(IOException ex) {
			return ExitCodes.INPUT_ERROR;
		}
	}
	private boolean isDigit(String s) {
		try {
			int i = Integer.parseInt(s);
			return true;
		}catch(NumberFormatException ex) {
			return false;
		}
	}

	public String ordinaCocktails(int numeroTavolo) {
		String exitCodes = visualizzaCocktails();
		ArrayList<String> checkList = new ArrayList<String>();
		if(exitCodes != ExitCodes.CLIENTB_VISUALIZZA_CORRECT) {
			return exitCodes;
		}
		try {
			String cocktails=askUser("INSERIRE NOMI COCKTAIL(separati da ,)");
			ArrayList<String> lNomi = new ArrayList<String>(Arrays.asList(cocktails.toUpperCase().split(",")));
			if(cocktails.isBlank() || cocktails.replaceAll("[^a-zA-Z0-9]", "").length()==0) {
				return  ExitCodes.CLIENTEB_ORDINE_EMPTY;
			}
			for(String nome: lNomi) { 
				if(nome.isBlank()) {
					return  ExitCodes.CLIENTEB_NOME_EMPTY;
				}else if(nome.length()>25) {
					return ExitCodes.CLIENTEB_NOME_LEN_OVERFLOW;
				}
				else if(checkList.contains(nome.toUpperCase())){
					return ExitCodes.CLIENTEB_COCKTAIL_REPLICATED;
				}else {
					checkList.add(nome.toUpperCase());
				}
			}
			
			String checkCode = GestioneOrdinazioniBar.getInstance().checkCockctailEsistente(lNomi);
			if(!checkCode.equals(ExitCodes.COCKTAIL_FOUND)) {
				return checkCode;
			}
			
			ArrayList<Integer> lQnt = new ArrayList<Integer>();
			for(String a: lNomi) {
				String qnt = askUser("INSERIRE QUANTITA "+a+": ");
				if(!isDigit(qnt)){
					return ExitCodes.CLIENTEB_QTA_FORMAT_ERR;
				}
				int qtaI = Integer.parseInt(qnt);
				if(qtaI < 1) {
					return ExitCodes.CLIENTEB_QTA_VALUE_ERR;
				}
				
				lQnt.add(qtaI);
			}
			
			GestioneOrdinazioniBar.getInstance().ordinaCocktails(lNomi, lQnt, numeroTavolo);
			
			return ExitCodes.CLIENTEB_ORDINE_CORRECT;
		}catch(IOException ex) {
			return ExitCodes.INPUT_ERROR;
		}catch(DAOException ex) { 
			return ex.getMessage();
		}
	
	}
}
