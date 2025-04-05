package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO (da un'idea di Michael Kolling and David J. Barnes)
 * 
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = { "vai ", "aiuto ", "fine " ,"prendi <nomeAttrezzo> " , "posa <nomeAttrezzo> "};
	private Partita partita;
	private Giocatore giocatore;
    private IOConsole io;
	
	public DiaDia(IOConsole io) {
		this.io=io;
	    this.partita = new Partita(io);  // Labirinto creato qui dentro
	    this.giocatore = new Giocatore(io);
	}
    
	
	public Giocatore getGiocatore() {
    	return this.giocatore;
    }

	public void gioca() {
		String istruzione; 

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
	    Comando comandoDaEseguire = new Comando(istruzione, io);

	    if (comandoDaEseguire.getNome().equals("fine")) {
	        this.fine();
	        return true;
	    } else if (comandoDaEseguire.getNome().equals("vai"))
	        this.vai(comandoDaEseguire.getParametro());
	    else if (comandoDaEseguire.getNome().equals("aiuto"))
	        this.aiuto();
	    else if(comandoDaEseguire.getNome().equals("prendi"))
	        this.prendi(comandoDaEseguire.getParametro());  
	    else if(comandoDaEseguire.getNome().equals("posa"))
	        this.posa(comandoDaEseguire.getParametro());  
	    else    
	        io.mostraMessaggio("messaggio sconosciuto");
	    
	    if (this.partita.vinta()) {
	    	io.mostraMessaggio("Hai vinto!");
	        return true;
	    } else
	        return false;
	}
	// implementazioni dei comandi dell'utente:
	private void prendi(String nomeAttrezzo) {
	    // 1. Verifica che il nome dell'attrezzo sia valido
	    if(nomeAttrezzo == null) {
	    	io.mostraMessaggio("Devi specificare quale attrezzo prendere");
	        return;
	    }
	    
	    Attrezzo attrezzo = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
	    
	    if(attrezzo == null) {
	    	io.mostraMessaggio("Attrezzo " + nomeAttrezzo + " non presente nella stanza");
	        return;
	    }
	    
	    if(this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo)) {
	        this.partita.getStanzaCorrente().removeAttrezzo(attrezzo);
	        io.mostraMessaggio("Hai preso: " + nomeAttrezzo);
	    } else {
	    	io.mostraMessaggio("Non puoi prendere " + nomeAttrezzo + ": borsa piena o troppo pesante");
	    }
	}
	
	
	private void posa(String nomeAttrezzo) {
	    if(nomeAttrezzo == null) {
	    	io.mostraMessaggio("Specificare il nome dell'attrezzo");
	        return;
	    }
	    
	    Borsa borsaCorrente = this.partita.getGiocatore().getBorsa();
	    Stanza stanzaCorrente = this.partita.getStanzaCorrente();
	    
	    Attrezzo attrezzo = borsaCorrente.getAttrezzo(nomeAttrezzo);
	    if(attrezzo == null) {
	    	io.mostraMessaggio("Attrezzo '" + nomeAttrezzo + "' non presente nella borsa");
	        return;
	    }
	  
	      if(stanzaCorrente.addAttrezzo(attrezzo)){     // questo if, compie il metodo prima e poi verifica se è andato a buon fine
	    	  borsaCorrente.removeAttrezzo(nomeAttrezzo);
	    	  io.mostraMessaggio("Hai posato: " + nomeAttrezzo);
	      }
	      else {
	    	  io.mostraMessaggio("Impossibile posare '" + nomeAttrezzo + "': stanza piena");
	      }
	}
    
	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for (int i = 0; i < elencoComandi.length; i++)
			io.mostraMessaggio(elencoComandi[i] + " ");
		io.mostraMessaggio("");
	}
	
	

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if (direzione == null)
			io.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			io.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.giocatore.getCfu();
			this.giocatore.setCfu(--cfu);
		}
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
	
	/**
	 * Comando "Fine".
	 */
	private void fine() {
		io.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
}