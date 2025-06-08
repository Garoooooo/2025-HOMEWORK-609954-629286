package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;


class LabirintoBuilderTest {

    private Labirinto labirinto;
    Scanner scanner=new Scanner(System.in);
    IO io=new IOConsole(scanner);

    @BeforeEach
    void setUp() {
        
        labirinto = Labirinto.newBuilder(io)
                .addStanzaIniziale("salotto")
                .addStanza("cucina")
                .addAttrezzo("pentola", 1)
                .addStanzaVincente("camera")
                .addAdiacenza("salotto", "cucina", Direzione.NORD)
                .addAdiacenza("cucina",  "camera", Direzione.EST)
                .getLabirinto();      // oppure .build()
    }

    @Test
    void testStanzaIniziale() {
        assertEquals("salotto", labirinto.getStanzaIniziale().getNome());
    }

    @Test
    void testStanzaVincente() {
        assertEquals("camera", labirinto.getStanzaVincente().getNome());
    }

    @Test
    void testAdiacenza() {
        Stanza salotto = labirinto.getStanzaIniziale();
        Stanza cucina  = salotto.getStanzaAdiacente(Direzione.NORD);
        assertNotNull(cucina);
        assertEquals("cucina", cucina.getNome());
    }

    @Test
    void testAttrezzoInCucina() {
        Stanza cucina = labirinto.getStanzaIniziale()
                                 .getStanzaAdiacente(Direzione.NORD);
        assertTrue(cucina.hasAttrezzo("pentola"));
    }
}
