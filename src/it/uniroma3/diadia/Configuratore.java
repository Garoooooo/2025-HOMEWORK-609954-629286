package it.uniroma3.diadia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuratore {
    private static final String NOME_FILE = "diadia.properties";
    private static final Properties props = new Properties();

    static {
        try (InputStream input = Configuratore.class.getClassLoader().getResourceAsStream(NOME_FILE)) {
            if (input != null) {
                props.load(input);
            } else {
                System.err.println("File di configurazione non trovato: " + NOME_FILE);
            }
        } catch (IOException e) {
            System.err.println("Errore nella lettura del file di configurazione");
            e.printStackTrace();
        }
    }

    public static int getCFUIniziali() {
        return Integer.parseInt(props.getProperty("CFU", "20"));
    }

    public static int getPesoMaxBorsa() {
        return Integer.parseInt(props.getProperty("PESO_MAX_BORSA", "10"));
    }
}
