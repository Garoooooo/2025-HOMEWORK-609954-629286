package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.Stanza;
class StanzaBloccataTest {

	private StanzaBloccata stanzaBloccata;
	private IO io;
	private Attrezzo chiave;
	private Attrezzo lanterna;
	private Stanza stanza;
	
	@BeforeEach
	void setUp() throws Exception {
	
		stanzaBloccata=new StanzaBloccata("bloccata",io,"chiave","sud");
		chiave=new Attrezzo("chiave",1,io);
		lanterna=new Attrezzo("lanterna",1,io);
		stanza=new Stanza("stanza",io);
	}

	@Test
	void testStanzaConAttrezzo() {
		stanzaBloccata.addAttrezzo(chiave);
		stanzaBloccata.impostaStanzaAdiacente("sud",stanza);
		assertEquals(stanzaBloccata.toString(),stanzaBloccata.getDescrizione());
		assertEquals(stanzaBloccata.getStanzaAdiacente("sud"),stanza);
	}
	
	@Test
	void testStanzaVuota()
	{
		stanzaBloccata.impostaStanzaAdiacente("sud", stanza);
		stanza.impostaStanzaAdiacente("sud", stanza);
		assertNotEquals(stanzaBloccata.getDescrizione(),stanza.getDescrizione());
		assertEquals(stanzaBloccata.getStanzaAdiacente("sud"),stanzaBloccata);
	}
	
	@Test
	void testStanzaConAltroAttrezzo()
	{
		stanzaBloccata.impostaStanzaAdiacente("sud", stanza);
		stanzaBloccata.addAttrezzo(lanterna);
		stanza.impostaStanzaAdiacente("sud", stanza);
		assertNotEquals(stanzaBloccata.getDescrizione(),stanza.getDescrizione());
		assertEquals(stanzaBloccata.getStanzaAdiacente("sud"),stanzaBloccata);
	}

}
