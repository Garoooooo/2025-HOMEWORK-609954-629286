package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;

class PartitaTest {
    
    private Partita partita;
    private Stanza stanzaVincente;
    private Stanza stanzaNormale;
    private IOConsole io=new IOConsole();

    @BeforeEach
    void setUp() {
        partita = new Partita(io);
        stanzaVincente = new Stanza("Vincente", io);
        stanzaNormale = new Stanza("Normale", io);
        
        partita.getLabirinto().setStanzaVincente(stanzaVincente);
    }

    @Test
    void testVintaQuandoStanzaCorrenteEVincente() {
        partita.setStanzaCorrente(stanzaVincente);
        assertTrue(partita.vinta());
    }

    @Test
    void testVintaQuandoStanzaCorrenteNonEVincente() {
        partita.setStanzaCorrente(stanzaNormale);
        assertFalse(partita.vinta());
    }
    
    @Test
    void testIsFinitaQuandoVinta() {
        partita.setStanzaCorrente(stanzaVincente);
        assertTrue(partita.isFinita());
    }

    @Test
    void testIsFinitaQuandoCfuZero() {
        partita.setCfu(0);
        assertTrue(partita.isFinita());
    }

    @Test
    void testIsFinitaQuandoSetFinita() {
        partita.setFinita();
        assertTrue(partita.isFinita());
    }

    @Test
    void testIsFinitaPartitaInCorso() {
        partita.setStanzaCorrente(stanzaNormale);
        partita.setCfu(5);
        assertFalse(partita.isFinita());
    }

    @Test
    void testGetSetCfu() {
        partita.setCfu(10);
        assertEquals(10, partita.getCfu());
    }
}