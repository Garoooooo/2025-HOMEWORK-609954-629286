package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.IO;

public class Giocatore {
    static final private int CFU_INIZIALI = 20;
    private int cfu;
    private Borsa borsa;
    private IO io;
    
    
    public Giocatore(IO io) {
    	this.io=io;
        this.cfu = CFU_INIZIALI;
        this.borsa = new Borsa(io);
    }

    public void setCfu(int cfu) {
        this.cfu = cfu;        
    }

    public int getCfu() {
        return this.cfu;
    }
    
    public Borsa getBorsa() {
        return this.borsa;
    }
}