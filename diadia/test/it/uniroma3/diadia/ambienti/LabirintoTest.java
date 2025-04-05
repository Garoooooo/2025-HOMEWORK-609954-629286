package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class LabirintoTest {
	
	private Labirinto labirinto;
	private IOConsole io=new IOConsole();
	private Stanza stanzaVincente;
	private Stanza stanzaCorrente;
	
	@BeforeEach
	void setUp() throws Exception {
		labirinto=new Labirinto(io);
		stanzaVincente=new Stanza("N13",io);
		stanzaCorrente=labirinto.getStanzaCorrente();
		}

	@Test //stanzaIniziale
	void testStanzaIniziale() {
		assertTrue(labirinto.getStanzaIniziale().getNome().equals("Atrio"));

	}
	
	@Test //stanzaFinale
	void testStanzaFinale()
	{
		assertTrue(labirinto.getStanzaVincente().getNome().equals("Biblioteca"));
	}
	
	@Test //setStanzaVincente() + stanzaFinale
	void testStanzaFinaleCambiata()
	{
		labirinto.setStanzaVincente(stanzaVincente);
		assertTrue(labirinto.getStanzaVincente().equals(this.stanzaVincente));
	}
	
	@Test //stanzaCorrente all'inizio
	void testStanzaCorrente()
	{
		assertTrue(labirinto.getStanzaCorrente().getNome().equals("Atrio"));
	}
	
	@Test //setStanzaCorrente + stanzaCorrente
	void testStanzaCorrenteCambiata()
	{
		labirinto.setStanzaCorrente(stanzaVincente);
		assertFalse(labirinto.getStanzaCorrente().equals(this.stanzaCorrente));
	}
}
