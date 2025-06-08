package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.StringReader;

import org.junit.jupiter.api.Test;

class CaricatoreLabirintoTest {

    @Test
    void testMancaSezioneStanze() {
        String spec =
                "Inizio: A\n" +
                "Vincente: A\n";
        assertThrows(FormatoFileNonValidoException.class,
                () -> new CaricatoreLabirinto(new StringReader(spec)).carica());
    }

    @Test
    void testUscitaVersoStanzaNonDefinita() {
        String spec =
                "Stanze: A\n" +
                "Inizio: A\n" +
                "Vincente: A\n" +
                "Uscite: A nord B\n";
        assertThrows(FormatoFileNonValidoException.class,
                () -> new CaricatoreLabirinto(new StringReader(spec)).carica());
    }

    @Test
    void testPesoNonNumerico() {
        String spec =
                "Stanze: A\n" +
                "Inizio: A\n" +
                "Vincente: A\n" +
                "Attrezzi: Martello xyz A\n";
        assertThrows(FormatoFileNonValidoException.class,
                () -> new CaricatoreLabirinto(new StringReader(spec)).carica());
    }

    @Test
    void testDirezioneNonValida() {
        String spec =
                "Stanze: A, B\n" +
                "Inizio: A\n" +
                "Vincente: B\n" +
                "Uscite: A nord-est B\n";       
        assertThrows(FormatoFileNonValidoException.class,
                () -> new CaricatoreLabirinto(new StringReader(spec)).carica());
    }
}
