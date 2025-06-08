package it.uniroma3.comandiTest;

import static org.junit.jupiter.api.Assertions.*;


import it.uniroma3.diadia.comandi.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FabbricaComandiFisarmonicaTest {
	private FabbricaDiComandiFisarmonica fabbrica;

	@BeforeEach
	void setUp() {
		fabbrica = new FabbricaDiComandiFisarmonica();
	}

	@Test
	void testRiconosciComandoVai() {
		Comando c = fabbrica.costruisciComando("vai nord");
		assertEquals("vai", c.getNome());
		assertEquals("nord", c.getParametro());
	}
	@Test
	void testRiconosciComandoPrendi() {
		Comando c = fabbrica.costruisciComando("prendi chiave");
		assertEquals("prendi", c.getNome());
		assertEquals("chiave", c.getParametro());
	}

	@Test
	void testRiconosciComandoPosa() {
		Comando c = fabbrica.costruisciComando("posa mappa");
		assertEquals("posa", c.getNome());
		assertEquals("mappa", c.getParametro());
	}

	@Test
	void testComandoFine() {
		Comando c = fabbrica.costruisciComando("fine");
		assertEquals("fine", c.getNome());
		assertNull(c.getParametro());
	}

	@Test
	void testComandoNonValido() {
		Comando c = fabbrica.costruisciComando("vola su");
		assertEquals("nonvalido", c.getNome());
		assertEquals("su", c.getParametro());
	}
	
	@Test
	void testComandoGuarda()
	{
		Comando c=fabbrica.costruisciComando("guarda");
		assertEquals("guarda",c.getNome());
		assertNull(c.getParametro());
	}
	
	@Test
	void testComandoAiuto()
	{
		Comando c=fabbrica.costruisciComando("aiuto");
		assertEquals("aiuto",c.getNome());
		assertNull(c.getParametro());
	}

}
