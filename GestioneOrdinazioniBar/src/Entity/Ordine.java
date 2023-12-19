package Entity;
//LAST
import java.util.Set;

import Entity.Cocktail.TIPO_OPACITA;


public class Ordine{
	private int numeroTavolo;
	private TIPO_STATO stato;
	private Set<ComposizioneCocktail> composizioniCoktail;
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ordine() {
		// TODO Auto-generated constructor stub
	}
	
	public static enum TIPO_STATO{
		PRONTO,
		NON_PRONTO,
		RITIRATO;
		private static TIPO_STATO[] allValues = values();
	    public static TIPO_STATO fromInteger(int n) {return allValues[n];}
	}

	public int getNumeroTavolo() {
		return numeroTavolo;
	}

	public void setNumeroTavolo(int numeroTavolo) {
		this.numeroTavolo = numeroTavolo;
	}

	public TIPO_STATO getStato() {
		return stato;
	}

	public void setStato(TIPO_STATO stato) {
		this.stato = stato;
	}

	public Set<ComposizioneCocktail> getComposizioniCoktail() {
		return composizioniCoktail;
	}

	public void setComposizioniCoktail(Set<ComposizioneCocktail> composizioniCoktail) {
		this.composizioniCoktail = composizioniCoktail;
	}
	

}
