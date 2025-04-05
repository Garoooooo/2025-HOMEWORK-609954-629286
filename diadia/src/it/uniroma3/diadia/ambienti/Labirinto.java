package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
	private Stanza stanzainiziale;
    private IOConsole io;
	
	  /**
     * Crea tutte le stanze e le porte di collegamento
     */
	
	public Labirinto(IOConsole io) {
		this.io=io;
		creaStanze();
	}
	
    private void creaStanze() {

		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3, io);
		Attrezzo osso = new Attrezzo("osso",1, io);

		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio", io);
		Stanza aulaN11 = new Stanza("Aula N11", io);
		Stanza aulaN10 = new Stanza("Aula N10", io);
		Stanza laboratorio = new Stanza("Laboratorio Campus", io);
		Stanza biblioteca = new Stanza("Biblioteca", io);
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio
        stanzaCorrente = atrio;            //stanza iniziale
        stanzainiziale=stanzaCorrente;
		stanzaVincente = biblioteca;       //stanza vincente
	}
    
    public void setStanzaVincente(Stanza stanzavincente) {
    	 stanzaVincente=stanzavincente;
    }
    
    public Stanza getStanzaIniziale() {
    	return this.stanzainiziale;
    }
    
    public Stanza getStanzaCorrente() {
    	return stanzaCorrente;
    }
    
    public Stanza getStanzaVincente() {
    	return stanzaVincente;
    }
    
    public void setStanzaCorrente(Stanza stanzaCorrente) {
        this.stanzaCorrente=stanzaCorrente;
    }
    
}
