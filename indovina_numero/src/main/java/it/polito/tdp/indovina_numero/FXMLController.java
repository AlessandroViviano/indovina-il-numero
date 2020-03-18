package it.polito.tdp.indovina_numero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnNuova;

    @FXML
    private TextField txtRimasti;

    @FXML
    private HBox layoutTentativo;

    @FXML
    private TextField txtTentativi;

    @FXML
    private Button btnProva;

    @FXML
    void doNuova(ActionEvent event) {
    	//gestione dell'inizio di una nuova partita - logica del gioco
    	this.segreto = (int)(Math.random()*100)+1;
    	this.tentativiFatti = 0;
    	this.inGioco = true;
    	
    	//gestione dell'interfaccia
    	layoutTentativo.setDisable(false);   //quest'istruzione abilita l'hBox relativa alla gestione dei nuovi tentativi
    	txtRisultato.clear();   //pulsice l'area di testo da qualche messaggio che può essere comparsso nel mentre
    	txtRimasti.setText(Integer.toString(TMAX));  //setto il nuovo numero di tentativi rimasti
  
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	//leggere l'input dell'utente
    	String ts = txtTentativi.getText(); 
    	int tentativo;
    	try {                                       //catturo l'eccezione che può generarsi dallo scrive3re delle lettere laddove il programma attende un numero
    	    tentativo = Integer.parseInt(ts);
    	}catch(NumberFormatException e) {
    		txtRisultato.appendText("Devi inserrire un numero!\n");
    		return ;   // ovviamente poi termino subito il programma
    	}
    	
    	this.tentativiFatti++;
    	
    	if(tentativo == this.segreto) {
    		//ho indovinato
    		
    		txtRisultato.appendText("HAI VINTO!!!!  Hai utilizzato " + this.tentativiFatti + " tentativi");
    		layoutTentativo.setDisable(true);
    		this.inGioco = false;
    		return ;
    	}
    	
    	if(tentativiFatti == TMAX) {
    		//ho esaurito i tentativi
    		txtRisultato.appendText("HAI PERSO!!! il numero segreto è " + segreto);
    		layoutTentativo.setDisable(false);
    		this.inGioco = false;
    		return ;
    	}
    	/* se non rientro nei due if precendenti significa che sono in un caso
    	 * intermedio e quindi devo informare l'utente se il tentativo è troppo
    	 * alto oppure è troppo basso, e lo faccio con degli if*/
    	
    	if(tentativo < segreto) {
    		txtRisultato.appendText("Tentativo troppo BASSO \n");
    	}
    	else {
    		txtRisultato.appendText("Tentativo troppo ALTO \n");
    	}
    	
    	txtRimasti.setText(Integer.toString(TMAX-tentativiFatti));
    }

    @FXML
    void initialize() {
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert layoutTentativo != null : "fx:id=\"layoutTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}

