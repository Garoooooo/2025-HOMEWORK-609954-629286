package it.uniroma3.comandiTest;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.comandi.*;
import it.uniroma3.diadia.giocatore.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComandoPrendiTest {
	private IO io=new IOConsole();
    private Borsa borsa;
    private Attrezzo attrezzo;
	private Stanza stanza;
	private Partita partita;

	@BeforeEach
	void setUp() throws Exception {
		stanza=new Stanza("biblioteca", io);
		borsa=new Borsa(io);
		partita=new Partita(io);
		partita.setStanzaCorrente(stanza);
		attrezzo=new Attrezzo("Coltello",borsa.getPesoMax()-1,io);
	}
	

	@Test
	void testPrendiOggettoConBorsaPiena() {
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		borsa.addAttrezzo(new Attrezzo("Altro", borsa.getPesoMax() - attrezzo.getPeso(), io));
		ComandoPrendi comando = new ComandoPrendi();
		comando.setParametro("Coltello");
		comando.esegui(partita);
		
		assertNotNull(partita.getGiocatore().getBorsa().getAttrezzo("Coltello")); 
		assertNull(partita.getGiocatore().getBorsa().getAttrezzo("ciaociaociao"));
	}
	

	@Test
	void testPrendiOggettoAmmissibile() {
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		ComandoPrendi comando = new ComandoPrendi();
		comando.setParametro("Coltello");
		comando.esegui(partita);
		
		assertNotNull(partita.getGiocatore().getBorsa().getAttrezzo("Coltello")); 
	}
	
	
	@Test
	void NonPrendiOggettoNonPresenteNellaStanza() {
	    ComandoPrendi comando = new ComandoPrendi();
	    comando.setParametro("Cacciavite"); 
	    comando.esegui(partita);
	    
	    assertNull(partita.getGiocatore().getBorsa().getAttrezzo("Cacciavite"));
	}
	
	
	@Test
	void testAttrezzoPresenteInStanzaVieneRimossoDallaStanzaDopoIlPrendi() {
		partita.getStanzaCorrente().addAttrezzo(attrezzo); 
		ComandoPrendi comando = new ComandoPrendi();
		comando.setParametro("Coltello");
		comando.esegui(partita);
		
		assertNull(partita.getStanzaCorrente().getAttrezzo("Coltello"));
		assertNotNull(partita.getGiocatore().getBorsa().getAttrezzo("Coltello"));
	}

}
