package Boundary;
//LAST
import Entity.*;

import java.io.IOException;

import DAO.*;

public class Main {
	
	
	
	
	protected static java.io.BufferedReader inputReader;
	protected static String askUser(String message) throws IOException {
		System.out.print(message);
		System.out.flush();
		return inputReader.readLine();
	}
	
	
	
	
	
	public static void main(String[] args) {
		ClienteB cB = new ClienteB();
		MaitreB mB =  MaitreB.getInstance();
		
		inputReader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		
		 	 /*  //C'E' LA POSSIBILITA' DI POPOLARE IL DATABASE TRAMITE TALI ISTRUZIONI
		  		//(NON FA PARTE DEL PRODOTTO E' STATZO IMPLEMENTARE PER FACILITARE IL POPOLAMENTO)
		
		 
			String cmd = null;
			boolean exit = false;
			do {
				System.out.println("Si puo popolare il Database inserendo y, altrimenti n: ");
				System.out.flush();
				try {
					cmd = inputReader.readLine().toLowerCase();
					if(cmd.equals("y")) {
						try {
							PopolamentoDB.flushDB();
							PopolamentoDB.initDB();
							exit = true;
						}catch(DAOException ex) {
							System.err.println("Popolamento fallito!");
							exit = false;
						}
						
					}else if(cmd.equals("n")){
						exit = true;
					}else {
						System.err.println("Opzione non riconosciuta");
						exit = false;
					}
				} catch (IOException e1) {
					System.err.println("Si e' verificato un errore di I/O:");
					exit = false;
				}
					
			}while(!exit); */
			
		
		
		
		
		
		
		int option = 0;
		
		try {
			do {
				System.out.println("Seleziona utente:\n"
						+ "\t1. Cliente\n"
						+ "\t2. Maitre\n"
						+ "\t3. Barman\n"
						+ "\t4. Cameriere\n"
						+ "\t5. EXIT\n");
						
				System.out.flush();
				
				try {
					option = Integer.parseInt(inputReader.readLine());
				} catch (NumberFormatException e) {
					option = 0;
				}
				
				switch (option) {
					case 1: {cB.showBoundary(); break; }
					case 2: { mB.showBoundary(); break; }
					case 3: { System.out.println("Coming soon...\n"); break; }
					case 4: { System.out.println("Coming soon...\n"); break; }
					case 5: { System.out.println("The End\n"); break; }
					default: {
						System.out.println("Carattere non riconosciuto!\n");
					}
				}
			} while (option != 5);
		}catch(IOException ex) {
			System.err.println("Si e' verificato un errore di I/O:");
		}

	}

}
