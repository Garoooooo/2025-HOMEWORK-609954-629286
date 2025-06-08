package it.uniroma3.diadia;

import java.io.*;

import java.util.*;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.Direzione;

public class CaricatoreLabirinto {
	
	//private IOConsole io=new IOConsole();

    private static final String STANZE_MARKER          = "Stanze:";
    private static final String STANZA_INIZIALE_MARKER = "Inizio:";
    private static final String STANZA_VINCENTE_MARKER = "Vincente:";
    private static final String ATTREZZI_MARKER        = "Attrezzi:";
    private static final String USCITE_MARKER          = "Uscite:";

    private LineNumberReader reader;
    private Map<String, Stanza> nome2stanza;
    private Stanza stanzaIniziale;
    private Stanza stanzaVincente;
    
    private IO io;

    /** 
     * Costruttore originale che legge da nomeFile. 
     */
    public CaricatoreLabirinto(String nomeFile,IO io) throws FileNotFoundException {
        this.nome2stanza = new HashMap<>();
        this.reader      = new LineNumberReader(new FileReader(nomeFile));
        this.io=io;
    }

    /**
     * NUOVO costruttore per testing: legge da un qualunque Reader 
     * (ad es. StringReader), evitando di dover creare file sul disco.
     */
    public CaricatoreLabirinto(Reader in) {
        this.nome2stanza = new HashMap<>();
        this.reader      = new LineNumberReader(new BufferedReader(in));
    }

    public void carica() throws FormatoFileNonValidoException {
        try {
            this.leggiECreaStanze();
            this.leggiInizialeEvincente();
            this.leggiECollocaAttrezzi();
            this.leggiEImpostaUscite();
        } finally {
            try { reader.close(); }
            catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
        try {
            String riga = this.reader.readLine();
            check(riga != null && riga.startsWith(marker),
                  "era attesa una riga che cominciasse per " + marker);
            return riga.substring(marker.length()).trim();
        } catch (IOException e) {
            throw new FormatoFileNonValidoException(e.getMessage());
        }
    }

    private void leggiECreaStanze() throws FormatoFileNonValidoException {
        String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
        for (String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
            Stanza stanza = new Stanza(nomeStanza,io);
            this.nome2stanza.put(nomeStanza, stanza);
        }
    }

    private List<String> separaStringheAlleVirgole(String string) {
        List<String> result = new LinkedList<>();
        Scanner scanner = new Scanner(string);
        scanner.useDelimiter(",");
        while (scanner.hasNext()) {
            result.add(scanner.next().trim());
        }
        scanner.close();
        return result;
    }

    private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
        String nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
        check(isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale + " non definita");
        String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
        check(isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
        this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
        this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
    }

    private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
        String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);
        for (String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
            String nomeAttrezzo = null;
            String pesoAttrezzo = null;
            String nomeStanza = null;
            Scanner scannerLinea = new Scanner(specificaAttrezzo);
            try {
                check(scannerLinea.hasNext(),
                      msgTerminazionePrecoce("il nome di un attrezzo."));
                nomeAttrezzo = scannerLinea.next();
                check(scannerLinea.hasNext(),
                      msgTerminazionePrecoce("il peso dell'attrezzo " + nomeAttrezzo + "."));
                pesoAttrezzo = scannerLinea.next();
                check(scannerLinea.hasNext(),
                      msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo " + nomeAttrezzo + "."));
                nomeStanza = scannerLinea.next();
            } finally {
                scannerLinea.close();
            }
            posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
        }
    }

    private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza)
            throws FormatoFileNonValidoException {
        int peso;
        try {
            peso = Integer.parseInt(pesoAttrezzo);
            Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso,io);
            check(isStanzaValida(nomeStanza),
                  "Attrezzo " + nomeAttrezzo + " non collocabile: stanza " + nomeStanza + " inesistente");
            this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
        } catch (NumberFormatException e) {
            check(false, "Peso attrezzo " + nomeAttrezzo + " non valido");
        }
    }

    private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
        String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
        Scanner scannerDiLinea = new Scanner(specificheUscite);
        while (scannerDiLinea.hasNext()) {
            String stanzaPartenza = scannerDiLinea.next();
            check(scannerDiLinea.hasNext(),
                  msgTerminazionePrecoce("la direzione di una uscita della stanza " + stanzaPartenza));
            String dir = scannerDiLinea.next();
            Direzione direzione= Direzione.valueOf(dir.toUpperCase());
            check(scannerDiLinea.hasNext(),
                  msgTerminazionePrecoce("la destinazione di una uscita della stanza " + stanzaPartenza + " nella direzione " + dir));
            String stanzaDestinazione = scannerDiLinea.next();
            impostaUscita(stanzaPartenza, direzione, stanzaDestinazione);
        }
        scannerDiLinea.close();
    }

    private void impostaUscita(String stanzaDa, Direzione dir, String nomeA)
            throws FormatoFileNonValidoException {
        check(isStanzaValida(stanzaDa), "Stanza di partenza sconosciuta " + dir);
        check(isStanzaValida(nomeA),    "Stanza di destinazione sconosciuta " + dir);
        Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
        Stanza arrivoA    = this.nome2stanza.get(nomeA);
        partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
    }

    private boolean isStanzaValida(String nomeStanza) {
        return this.nome2stanza.containsKey(nomeStanza);
    }

    private String msgTerminazionePrecoce(String msg) {
        return "Terminazione precoce del file prima di leggere " + msg;
    }

    private void check(boolean condizioneCheDeveEssereVera, String messaggioErrore)
            throws FormatoFileNonValidoException {
        if (!condizioneCheDeveEssereVera) {
            throw new FormatoFileNonValidoException(
                "Formato file non valido [" + this.reader.getLineNumber() + "] " + messaggioErrore
            );
        }
    }

    public Stanza getStanzaIniziale() {
        return this.stanzaIniziale;
    }

    public Stanza getStanzaVincente() {
        return this.stanzaVincente;
    }
}
