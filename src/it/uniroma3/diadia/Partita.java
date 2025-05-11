package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 */
public class Partita {
    private boolean finita;
    private Labirinto labirinto;
    private Giocatore giocatore;
	private IO io;

    
    public Partita(IO io) {
    	this.io=io;
        this.labirinto = new Labirinto(io);
        this.giocatore = new Giocatore(io);
        this.finita = false;
    }

    public Stanza getStanzaCorrente() {
        return this.labirinto.getStanzaCorrente();
    }

    public Stanza getStanzaVincente() {
        return this.labirinto.getStanzaVincente();
    }

    public void setStanzaCorrente(Stanza stanzaCorrente) {
        this.labirinto.setStanzaCorrente(stanzaCorrente);
    }
    
    /**
     * Restituisce vero se e solo se la partita e' stata vinta
     * @return vero se partita vinta
     */
    public boolean vinta() {
        return this.getStanzaCorrente() == this.getStanzaVincente();
    }

    /**
     * Restituisce vero se e solo se la partita e' finita
     * @return vero se partita finita
     */
    public boolean isFinita() {
        return finita || vinta() || (this.giocatore.getCfu() == 0);
    }

    /**
     * Imposta la partita come finita
     */
    public void setFinita() {
        this.finita = true;
    }

    public boolean giocatoreIsVivo() {
        return this.giocatore.getCfu() > 0;
    }
    
    public int getCfu() {
        return this.giocatore.getCfu();
    }

    public void setCfu(int cfu) {
        this.giocatore.setCfu(cfu);
    }
    
    public Labirinto getLabirinto() {
    	return this.labirinto;
    }
    
    public Giocatore getGiocatore() {
        return this.giocatore;
    }
}