package it.uniroma3.comandiTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.comandi.AbstractComando;

class AbstractComandoTest {

    private static class StubComando extends AbstractComando {
        @Override
        public void esegui(Partita p) {}
        
        @Override
        public String getNome()
        {
        	return "stub";
        }
    }

    private StubComando cmd;

    @BeforeEach
    void setUp() {
        cmd = new StubComando();
    }

    @Test
    void testDefaultNull() {
        assertNull(cmd.getParametro());
    }

    @Test
    void testSetGet() {
        cmd.setParametro("x");
        assertEquals("x", cmd.getParametro());
    }

    @Test
    void testOverwrite() {
        cmd.setParametro("a");
        cmd.setParametro("b");
        assertEquals("b", cmd.getParametro());
    }

    @Test
    void testSetNull() {
        cmd.setParametro("a");
        cmd.setParametro(null);
        assertNull(cmd.getParametro());
    }
    
    @Test
    void testGetNome()
    {
    	assertEquals("stub",cmd.getNome());
    }
}
