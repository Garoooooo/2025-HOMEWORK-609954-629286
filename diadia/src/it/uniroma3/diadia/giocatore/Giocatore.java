package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.IOConsole;

public class Giocatore {
    static final private int CFU_INIZIALI = 20;
    private int cfu;
    private Borsa borsa;
    private IOConsole io;
    
    
    public Giocatore(IOConsole io) {
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