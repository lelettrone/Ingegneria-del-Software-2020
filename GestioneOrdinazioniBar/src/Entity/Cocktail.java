package Entity;
//LAST
import java.util.Set;

//KEREKEYYYOOO
public class Cocktail  {
	private String nome;
	private String descrizione;
	private String preparazione;
	private String colore;
	private float qtaB;
	private TIPO_OPACITA opacita;

	private TIPO_COCKTAIL tipo;
	private TIPO_GRADO gradoAlcolico;
	private Base base;
	
	private Set<ComposizioneAdditivoNP> composizioneAddNP;
	private Set<ComposizioneAdditivoP> composizioneAddP;
	

	private Set<ComposizioneBicchiere> composizioneBicchieri;
	
	public Cocktail() {
		
	}
	
	public static enum TIPO_OPACITA{
		CHIARO,
		CREMOSO,
		OPACO;
		private static TIPO_OPACITA[] allValues = values();
	    public static TIPO_OPACITA fromInteger(int n) {return allValues[n];}

	}
	public static enum TIPO_COCKTAIL{
		SHORT_DRINK,
		LONG_DRINK;
		private static TIPO_COCKTAIL[] allValues = values();
	    public static TIPO_COCKTAIL fromInteger(int n) {return allValues[n];}
	}
	public static enum TIPO_GRADO{
		ALCOLICO,
		SUPER_ALCOLICO,
		ANALCOLICO;
		private static TIPO_GRADO[] allValues = values();
	    public static TIPO_GRADO fromInteger(int n) {return allValues[n];}
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPreparazione() {
		return preparazione;
	}
	public void setPreparazione(String preparazione) {
		this.preparazione = preparazione;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getColore() {
		return colore;
	}
	public void setColore(String colore) {
		this.colore = colore;
	}
	public float getQtaB() {
		return qtaB;
	}
	public void setQtaB(float qtaB) {
		this.qtaB = qtaB;
	}
	public TIPO_OPACITA getOpacita() {
		return opacita;
	}
	public void setOpacita(TIPO_OPACITA opacita) {
		this.opacita = opacita;
	}
	public TIPO_COCKTAIL getTipo() {
		return tipo;
	}
	public void setTipo(TIPO_COCKTAIL tipo) {
		this.tipo = tipo;
	}
	public TIPO_GRADO getGradoAlcolico() {
		return gradoAlcolico;
	}
	public void setGradoAlcolico(TIPO_GRADO gradoAlcolico) {
		this.gradoAlcolico = gradoAlcolico;
	}
	public Base getBase() {
		return base;
	}
	public void setBase(Base base) {
		this.base = base;
	}
	
	public Set<ComposizioneAdditivoNP> getComposizioneAddNP() {
		return composizioneAddNP;
	}
	public void setComposizioneAddNP(Set<ComposizioneAdditivoNP> composizioneAddNP) {
		this.composizioneAddNP = composizioneAddNP;
	}
	public Set<ComposizioneAdditivoP> getComposizioneAddP() {
		return composizioneAddP;
	}
	public void setComposizioneAddP(Set<ComposizioneAdditivoP> composizioneAddP) {
		this.composizioneAddP = composizioneAddP;
	}
	public Set<ComposizioneBicchiere> getComposizioneBicchieri() {
		return composizioneBicchieri;
	}
	public void setComposizioneBicchieri(Set<ComposizioneBicchiere> composizioneBicchieri) {
		this.composizioneBicchieri = composizioneBicchieri;
	}
	
	

}


