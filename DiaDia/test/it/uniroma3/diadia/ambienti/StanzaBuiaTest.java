package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.IO;

import it.uniroma3.diadia.attrezzi.Attrezzo;
class StanzaBuiaTest {

	private StanzaBuia stanzaBuia;
	private IO io;
	private Attrezzo lanterna;
	private Attrezzo chiave;
	@BeforeEach
	void setUp() throws Exception {
		stanzaBuia=new StanzaBuia("buia",io,"lanterna");
		lanterna=new Attrezzo("lanterna",1,io);
		chiave=new Attrezzo("chiave",1,io);
	}

	@Test
	void testStanzaBuiaConAtrezzo() {
		stanzaBuia.addAttrezzo(lanterna);
		assertEquals(stanzaBuia.toString(),stanzaBuia.getDescrizione());
	}
	
	@Test
	void testStanzaBuiaVuota()
	{
		String buia="qui c'è buio pesto";
		assertEquals(buia,stanzaBuia.getDescrizione());
	}
	
	@Test
	void testStanzaBuiaAttrezzoDiverso()
	{
		stanzaBuia.addAttrezzo(chiave);
		String buia="qui c'è buio pesto";
		assertEquals(buia,stanzaBuia.getDescrizione());
	}

}
