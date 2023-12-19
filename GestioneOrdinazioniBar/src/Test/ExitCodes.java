package Test;
//LAST

public class ExitCodes {
	
	
	
	public static final  String INPUT_ERROR = "Errore I\\O";
    public static final  String CHARACTER_NOT_RECO = "Carattere non rionosciuto!";
    
    //DB
    public static final  String  CANT_CONNECT = "CANT CONNECT DB";
    public static final  String  CANT_CLOSE = "CANT CLOSE CONNECTION";
    public static final  String  CANT_LOAD_H2 = "CANT CLOSE CONNECTION";
    public static final String CANT_FLUSH = "CANT FLUSH";
    public static final String CANT_POPULATE = "CANT POPULATE";
 

    //CLIENTEB
    public static final String CLIENTEB_NOME_EMPTY = "ERR: Campo vuoto!";
    public static final String CLIENTEB_ORDINE_EMPTY = "ERR: Ordine vuoto!";
    public static final String CLIENTEB_NOME_LEN_OVERFLOW = "ERR: Nome troppo lungo!";
    public static final String CLIENTEB_DESCRIZIONE_LEN_OVERFLOW = "ERR: Descrizione troppo lunga!";
    public static final String CLIENTEB_PREPARAZIONE_LEN_OVERFLOW = "ERR: Preparazione troppo lunga!";
    public static final String CLIENTEB_BASE_LEN_OVERFLOW = "ERR: Base troppo lunga!";
    public static final String CLIENTEB_BASE_EMPTY = "ERR: Base vuota!";
    public static final String CLIENTEB_BASE_FORMAT_ERR = "ERR: Base formato errato!";
    public static final String CLIENTEB_LADDITIVI_EMPTY = "ERR: Lista additivi vuota!";
    public static final String CLIENTEB_ADDITIVO_FORMAT_ERR = "ERR: Additivo formato errato!";
    public static final String CLIENTEB_ADDITIVO_REPLICATED = "ERR: Additivo replicato!";
    public static final String CLIENTEB_COCKTAIL_REPLICATED = "ERR: Cocktail replicato!";
    public static final String CLIENTEB_QTA_VALUE_ERR = "ERR: Valore quantita errato!";
    public static final String CLIENTEB_QTA_FORMAT_ERR = "ERR: Formato quantita errato!";
    public static final String CLIENTEB_ORDINE_CORRECT = "Ordine creato!";
    //public static final String CLIENTEB_ORDINE_ERROR = "ERR: Ordine non creato!";
    public static final String CLIENTB_VISUALIZZA_CORRECT = "Visualizzazione corretta!";
    //public static final String CLIENTB_VISUALIZZA_ERROR = "ERR: Visualizzazione errata!";
    public static final String CLIENTB_SUGGERISCI_CORRECT = "Suggerimento inviato!";
    //public static final String CLIENTB_SUGGERISCI_ERROR = "ERR: Suggerimento non inviato!";
    
    
    //MAITREB
    public static final String MAITREB_NOME_EMPTY = "ERR: Campo vuoto!";
    public static final String MAITREB_NOME_LEN_OVERFLOW = "ERR: Nome troppo lungo!";
    public static final String MAITREB_RIMUOVI_CORRECT = "Cocktail rimosso!";
    public static final String MAITREB_RIMUOVI_ERROR = "ERR: Ordine non creato!";
    
    //COMP COCKTAILS
    public  static final  String CANT_ADD_COMPCOCKTAIL = "CANT ADD COMPOSIZIONECOCKTAIL";
    public static final String    LISTCOMPCOCKTAILS_NOT_FOUND = "";
    public static final  String CANT_READ_LISTCOMPCOCKTAILS = "CANT READ LISTACOCKTAILS";
    
    //COMP BICCHIERI
    public static  final String CANT_ADD_COMPBICCHIERE = "CANT ADD COMPOSIZIONEBICCHIERE";
    public static final String CANT_REM_COMPBICCHIERE = "CANT REMOVE COMPOSIZIONEBICCHIERE";
    public static final String CANT_READ_LISTCOMPBICCHIERI = "CANT READ LISTACOMPOSIZIONEBICCHIERE";
    public static final String LISTCOMPBICCHIERI_NOT_FOUND = "";
    
    //COMP ADDITIVI
    public static final String CANT_ADD_COMPADDITIVI = "CANT ADD COMPOSIZIONEADDITIVO";
    public static final String CANT_READ_LISTCOMPADDITIVI = "CANT READ LISTACOMPOSIZIONEADDITIVO";
    public static final String LISTCOMPADDITIVI_NOT_FOUND = "LISTA ADD VUOTA";
    
    //BICCHIERI
    public static final String CANT_READ_BICCHIERE = "CANT READ BICCHIERE";
    public static final String CANT_RESTORE_BICCHIERE = "CANT RESTORE BICCHIERE";
    public static final String CANT_ADD_BICCHIERE = "CANT ADD BICCHIERE";
    public static final String CANT_REM_BICCHIERE = "CANT REMOVE BICCHIERE";
    public static final String BICCHIERE_NOT_FOUND = "BICCHIERE NOT FOUND IN BD";
    
    
    //BASI
    public static final String CANT_RESTORE_BASE = "CANT RESTORE BASE";
    public static final String CANT_READ_BASE = "CANT READ BASE";
    public static final String CANT_REM_BASE = "CANT REMOVE BASE";
    public static final String CANT_ADD_BASE = "CANT ADD BASE";
    public static final String BASE_NOT_FOUND = "BASE NOT FOUND IN BD";
    public static final String WRONG_TIPO_BASE = "WRONG TIPO BASE";
    
    //ADDITIVI
    public static final String CANT_REM_ADDITIVO = "CANT REMOVE ADDITIVO";
    public static final String CANT_READ_ADDITIVO = "CANT REDAD ADDITIVO";
    public static final String CANT_RESTORE_ADDITIVO = "CANT RESTORE ADDITIVO";
    public static final String CANT_ADD_ADDITIVO = "CANT ADD ADDITIVO";
    public static final String ADDITIVIO_NOT_FOUND = "ADDITIVO NOT FOUND IN BD";
    
    //COCKTAIL
    public static final String COCKTAIL_NOT_FOUND = "ERR: Cocktail non trovato";
    public static final String COCKTAIL_FOUND = "Cocktail trovato";
    public static final String LISTCOCKTAILS_NOT_FOUND = "Lista cocktails vuota!";
    public static final String CANT_REM_COCKTAIL = "CANT REMOVE COCKTAIL";
    public static final String CANT_ADD_COCKTAIL = "CANT ADD COCKTAIL";
    public static final String CANT_RESTORE_COCKTAIL = "CANT RESTORE COCKTAIL";
    public static final String CANT_READ_LISTACOCKTAILS = "ERR: Impossibile leggere lista cocktails!";
    public static final String CANT_READ_COCKTAIL = "CANT READ COCKTAIL";
    
    //ORDINI
    public static final String ORDINE_NOT_FOUND = "ORDINE NOT FOUND IN BD";
    public  static final  String CANT_RESTORE_ORDINE = "CANT RESTORE ORDINE";
    public  static  final String CANT_ADD_ORDINE = "CANT ADD ORDINE";
    public  static final  String CANT_REM_ORDINE = "CANT REMOVE ORDINE";
    public  static final  String CANT_READ_ORDINI = "CANT READ ORDINE";
	
	
	public ExitCodes() {
		// TODO Auto-generated constructor stub
	}

}
