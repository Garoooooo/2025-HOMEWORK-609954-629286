package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoGuarda implements Comando {
	private String input;
	
	@Override
    public void esegui(Partita partita) {
    Stanza stanzaCorrente=partita.getStanzaCorrente();
    Giocatore giocatore=partita.getGiocatore();
    
    System.out.println(stanzaCorrente.getDescrizione());
    System.out.println("CFU rimanenti: "+giocatore.getCfu());
    System.out.println("Contenuto borsa: "+giocatore.getBorsa());
   
	}
    
    @Override
    public void setParametro(String parametro) {
    } 
}
