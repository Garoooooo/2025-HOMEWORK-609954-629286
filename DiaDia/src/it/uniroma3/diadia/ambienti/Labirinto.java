package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IO;

public class Labirinto {
    private Stanza stanzaIniziale;
    private Stanza stanzaVincente;
    private Stanza stanzaCorrente;
    private IO io;

    public Labirinto(IO io) {
    	
    	this.io=io;
    	
        Labirinto labirintoDefault = Labirinto.newBuilder(io)
            .addStanzaIniziale("Atrio")
            .addAttrezzo("osso", 1)
            .addStanza("Biblioteca")
            .addStanza("Aula N11")
            .addStanza("Aula N10")
            .addAttrezzo("lanterna", 3)
            .addAttrezzo("spada",11)
            .addStanza("Laboratorio Campus")
            
            //adiacenze atrio
            .addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
            .addAdiacenza("Atrio", "Aula N11", Direzione.EST)
            .addAdiacenza("Atrio", "Aula N10", Direzione.SUD)
            .addAdiacenza("Atrio", "Laboratorio Campus", Direzione.OVEST)
            
            //adiacenze aula N11
            .addAdiacenza("Aula N11", "Laboratorio Campus", Direzione.EST)
            .addAdiacenza("Aula N11", "Atrio", Direzione.OVEST)
            
            //adiacenze aula N10
            .addAdiacenza("Aula N10", "Atrio", Direzione.NORD)
            .addAdiacenza("Aula N10", "est", Direzione.EST)
            .addAdiacenza("Aula N10", "Laboratorio Campus", Direzione.OVEST)
            
            //adiacenze Laboratorio Campus
            .addAdiacenza("Laboratorio Campus", "Atrio", Direzione.EST)
            .addAdiacenza("Laboratorio Campus", "Aula N11", Direzione.OVEST)
            
            //adiacenze biblioteca
            .addAdiacenza("Biblioteca", "Atrio", Direzione.SUD)
            
            //stanza vincente
            .addStanzaVincente("Biblioteca")
            
            //costruzione del labirinto
            .build();
        

        this.stanzaIniziale = labirintoDefault.getStanzaIniziale();
        this.stanzaVincente = labirintoDefault.getStanzaVincente();
        this.stanzaCorrente = this.stanzaIniziale;
    }
    
    private Labirinto(boolean b)
    {
    	
    }

    public Labirinto(String nomeFile) throws FormatoFileNonValidoException, FileNotFoundException {
        CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile,io);
        c.carica();
        this.stanzaIniziale = c.getStanzaIniziale();
        this.stanzaVincente = c.getStanzaVincente();
        this.stanzaCorrente = this.stanzaIniziale;
    }

    private Labirinto(LabirintoBuilder builder) {
        this.stanzaIniziale = builder.getStanzaIniziale();
        this.stanzaVincente = builder.getStanzaVincente();
        this.stanzaCorrente = this.stanzaIniziale;
    }

    public static LabirintoBuilder newBuilder(IO io) {
        return new LabirintoBuilder(io);
    }

    public Stanza getStanzaIniziale() {
        return stanzaIniziale;
    }

    public Stanza getStanzaVincente() {
        return stanzaVincente;
    }

    public Stanza getStanzaCorrente() {
        return stanzaCorrente;
    }

    public void setStanzaIniziale(Stanza stanza) {
        this.stanzaIniziale = stanza;
        this.stanzaCorrente = stanza;
    }

    public void setStanzaVincente(Stanza stanza) {
        this.stanzaVincente = stanza;
    }

    public void setStanzaCorrente(Stanza stanza) {
        this.stanzaCorrente = stanza;
    }

    public static class LabirintoBuilder {
        private final Labirinto labirinto;
        private Stanza ultimaStanzaAggiunta;
        private IO io;
        private Map<String, Stanza> stanze;

        private LabirintoBuilder(IO io) {
            this.labirinto = new Labirinto(true);
            this.stanze    = new HashMap<>();
            this.io=io;
        }

        public LabirintoBuilder addStanzaIniziale(String nome) {
            Stanza stanza = new Stanza(nome,io);
            labirinto.setStanzaIniziale(stanza);
            stanze.put(nome, stanza);
            ultimaStanzaAggiunta = stanza;
            return this;
        }

        public LabirintoBuilder addStanzaVincente(String nome) {
            Stanza stanza = stanze.computeIfAbsent(nome, n -> new Stanza(n,io));
            labirinto.setStanzaVincente(stanza);
            ultimaStanzaAggiunta = stanza;
            return this;
        }

        public LabirintoBuilder addStanza(String nome) {
            Stanza stanza = new Stanza(nome,io);
            stanze.put(nome, stanza);
            ultimaStanzaAggiunta = stanza;
            return this;
        }

        public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
            if (ultimaStanzaAggiunta != null) {
                Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso,io);
                ultimaStanzaAggiunta.addAttrezzo(attrezzo);
            }
            return this;
        }

        public LabirintoBuilder addAdiacenza(String nomeStanzaDa, String nomeStanzaA, Direzione direzione) {
            Stanza da = stanze.get(nomeStanzaDa);
            Stanza a  = stanze.get(nomeStanzaA);
            if (da != null && a != null) {
                da.impostaStanzaAdiacente(direzione, a);
            }
            return this;
        }

        public Labirinto build() {
            return labirinto;
        }

        public Labirinto getLabirinto() {
            return labirinto;
        }

        public Stanza getStanzaIniziale() {
            return labirinto.getStanzaIniziale();
        }

        public Stanza getStanzaVincente() {
            return labirinto.getStanzaVincente();
        }
    }
}
