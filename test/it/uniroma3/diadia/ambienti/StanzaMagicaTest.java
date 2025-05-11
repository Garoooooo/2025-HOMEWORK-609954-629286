package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.StanzaMagica;
class StanzaMagicaTest {

	private StanzaMagica stanzaMagicaDefault; //usa la soglia magica di default
	private StanzaMagica stanzaMagica; //usa una soglia magica personalizzata;
	private IO io;
	private Attrezzo spada;
	
	@BeforeEach
	void setUp() throws Exception {
		stanzaMagicaDefault=new StanzaMagica("magicaDefault",io);
		stanzaMagica=new StanzaMagica("magica",io,2);
		spada=new Attrezzo("spada",2,io);
	}

	@Test
	void testTrasformaAttrezzo() {
		stanzaMagica.trasforma(spada);
		assertEquals(spada.getNome(),"adaps");
		assertEquals(spada.getPeso(),4);
	}
	
	@Test
	void testAddAttrezzo()
	{
		stanzaMagica.addAttrezzo(spada);
		stanzaMagica.removeAttrezzo(spada);
		stanzaMagica.addAttrezzo(spada);
		stanzaMagica.removeAttrezzo(spada);
		stanzaMagica.addAttrezzo(spada);
		assertEquals(spada.getNome(),"adaps");
		assertEquals(spada.getPeso(),4);
	}
	
	@Test
	void testSogliaMagicaDefault()
	{
		assertEquals(stanzaMagicaDefault.getSogliaMagica(),3);
	}
	
	@Test
	void testSogliaMagicaPersonalizzata()
	{
		assertEquals(stanzaMagica.getSogliaMagica(),2);
	}
	
	@Test
	void testSetSogliaMagica()
	{
		stanzaMagica.setSogliaMagica(3);
		assertEquals(stanzaMagica.getSogliaMagica(),3);
	}

}
