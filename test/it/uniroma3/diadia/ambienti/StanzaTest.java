package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
    
    private Stanza stanzaVuota;
    private Stanza stanzaConAttrezzo;
    private Attrezzo martello;
    private IOConsole io=new IOConsole();
    
    @BeforeEach
    public void setUp() {
        stanzaVuota = new Stanza("N13", io);
        stanzaConAttrezzo = new Stanza("N14", io);
        martello = new Attrezzo("martello", 2, io);
        stanzaConAttrezzo.addAttrezzo(martello);
    }
    
    @Test  //hasattrezzo
    public void testStanzaVuotaAllInizio() {
        assertFalse(stanzaVuota.hasAttrezzo("martello"));
    }
    
    @Test   //hasattrezzo
    public void testHasNotAttrezzo() {
    assertFalse(stanzaConAttrezzo.hasAttrezzo("spada"));    	
    }
    
    @Test    //addattrezzo e hasattrezzo
    public void testAddAttrezzo() {
        stanzaVuota.addAttrezzo(martello);
        assertTrue(stanzaVuota.hasAttrezzo("martello"));
    }
    
    @Test    //addattrezzo e hasattrezzo
    public void testAddAttrezzoStanzaPiena() {
        
    	//riempio la stanza vuota di oggetti fino al suo limite 
        for (int i = 0; i < stanzaVuota.getNumeroMassimoAttrezzi(); i++) {
            assertTrue(stanzaVuota.addAttrezzo(new Attrezzo("attrezzo" + i, 1, io)));
        }
        
        assertFalse(stanzaVuota.addAttrezzo(martello)); 
        assertFalse(stanzaVuota.hasAttrezzo("martello")); 
    }
    
    @Test  //getattrezzo
    public void testGetAttrezzoPresente() {
        Attrezzo attrezzoTrovato = stanzaConAttrezzo.getAttrezzo("martello");
        assertEquals(martello, attrezzoTrovato);
    }
    
    @Test    //getattrezzo
    public void testGetAttrezzoInesistente() {
        Attrezzo attrezzoTrovato = stanzaConAttrezzo.getAttrezzo("spada");
        assertNull(attrezzoTrovato);
    }

    
    @Test   //impostastanzaadiacente
    public void testImpostaStanzaAdiacente() {
        Stanza N15 = new Stanza("N15", io);
        stanzaVuota.impostaStanzaAdiacente("nord", N15);
        assertEquals(N15, stanzaVuota.getStanzaAdiacente("nord"));
    }
}