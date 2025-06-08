package it.uniroma3.comandiTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comandi.*;


class ComandoVaiTest {
    private IO io;
    private Partita partita;
    private ComandoVai comando;
    private Scanner scanner=new Scanner(System.in);

    @BeforeEach
    void setUp() {
        io = new IOConsole(scanner);

        
        Labirinto labirinto = Labirinto.newBuilder(io)
                .addStanzaIniziale("salotto")
                .addStanza("cucina")
                .addStanzaVincente("camera")
                .addAdiacenza("salotto", "cucina", Direzione.NORD)
                .addAdiacenza("cucina",  "camera", Direzione.EST)
                .getLabirinto();          // (oppure .build())

        partita = new Partita(io);
        partita.setLabirinto(labirinto);

        comando = new ComandoVai();
    }

    @Test
    void testVaiNellaDirezioneCorretta() {
        comando.setParametro("nord");
        comando.esegui(partita);
        assertEquals("cucina", partita.getStanzaCorrente().getNome());
    }

    @Test
    void testVaiInDirezioneInesistente() {
        comando.setParametro("ovest");
        comando.esegui(partita);
        assertEquals("salotto", partita.getStanzaCorrente().getNome());
    }

    @Test
    void testVittoria() {
        comando.setParametro("nord");
        comando.esegui(partita);  // arrivo in cucina
        comando.setParametro("est");
        comando.esegui(partita);  // arrivo in camera (stanza vincente)
        assertTrue(partita.vinta());
    }
}
