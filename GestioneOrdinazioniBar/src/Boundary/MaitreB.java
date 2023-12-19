package Boundary;
//LAST
import java.io.IOException;
import java.util.ArrayList;

import Controller.GestioneOrdinazioniBar;
import DAO.DAOException;
import Test.ExitCodes;

public class MaitreB {
	
	
	private ArrayList<String> lNotifiche;
	private java.io.BufferedReader inputReader;
	private static MaitreB instance = null;
	
	private MaitreB() {
		inputReader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		lNotifiche = new ArrayList<String>();
	}

	public static MaitreB getInstance() {
		if (instance == null) {
			instance = new MaitreB();
		}
		return instance;
	}
	
	public void pushNotification(String not) {
		lNotifiche.add(not);
	}
	
	
	protected String askUser(String message) throws IOException {
		System.out.print(message);
		System.out.flush();
		return inputReader.readLine();
	}

	private void showNotifications() {
		System.out.println("Notifiche ricevute");
		for(String n: lNotifiche) {
			System.out.println(n);
		}
		
		lNotifiche.clear();
	}
	
	public void showBoundary() {
		int option = 0;
		if(lNotifiche.size()>=1) {
			showNotifications();
		}
		
		try {
			do {
				System.out.println("Seleziona opzione:\n"
						+ "\t1. Rimuovi cocktail\n"
						+ "\t2. Aggiungi cocktail\n"
						+ "\t3. EXIT\n");

				System.out.flush();

				try {
					option = Integer.parseInt(inputReader.readLine());
				} catch (NumberFormatException e) {
					option = 0;
				}

				switch (option) {
				case 1: { System.err.println(rimuoviCocktail()); break; }
				case 2: { 
					System.out.println("Coming soon...");
					break; }
				case 3: { System.out.println("The End\n"); break; }
				default: {
					System.err.println(ExitCodes.CHARACTER_NOT_RECO);
				}
				}
			} while (option != 3);

		}catch(IOException ex) {
			System.err.println(ExitCodes.INPUT_ERROR);
		}
	}

	public String rimuoviCocktail() {
		try {
			String nome = askUser("INSERIRE NOME COCKTAIL DA RIMUOVERE: ");
			if(nome.isBlank())return ExitCodes.MAITREB_NOME_EMPTY;
			if(nome.length()>25)return ExitCodes.MAITREB_NOME_LEN_OVERFLOW;

			GestioneOrdinazioniBar.getInstance().rimuoviCocktail(nome);

			return ExitCodes.MAITREB_RIMUOVI_CORRECT;
		}catch(IOException ex) {
			return ExitCodes.INPUT_ERROR;
		}catch(DAOException ex) {
				return ex.getMessage();
		}
	}
}
