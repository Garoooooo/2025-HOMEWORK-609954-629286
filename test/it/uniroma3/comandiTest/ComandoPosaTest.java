package it.uniroma3.comandiTest;

import it.uniroma3.diadia.comandi.*;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComandoPosaTest {

    private Comando comando;
    private Partita partita;
    private Giocatore giocatore;
    private Stanza stanza;
    private IO io=new IOConsole();

    @BeforeEach
    void setUp() {
        this.comando = new ComandoPosa();
        this.partita = new Partita(io);  
        this.giocatore = partita.getGiocatore();
        this.stanza = new Stanza("aula", io);
        partita.setStanzaCorrente(stanza);
    }
    
    //borsa vuota, mi trovo nella stanza aula, e svolgo i test
    

    @Test
    void testPosaAttrezzoPresente() {
    	Attrezzo penna = new Attrezzo("penna", 1,io);
        giocatore.getBorsa().addAttrezzo(penna);
        comando.setParametro("penna");
        comando.esegui(partita);

        assertNull(giocatore.getBorsa().getAttrezzo("penna"));
        assertNotNull(stanza.getAttrezzo("penna"));
    }

    @Test
    void testPosaAttrezzoNonPresente() {
        comando.setParametro("libro");
        comando.esegui(partita);

        assertNull(stanza.getAttrezzo("libro"));
    }

    
}