package it.uniroma3.diadia.personaggi;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public abstract class AbstractPersonaggio {
	private String nome;
	private String presentazione;
	private boolean haSalutato;
	//private IOConsole io=new IOConsole();
	private IO io;
    //private Borsa borsa=new Borsa(Integer.MAX_VALUE);
	private Borsa borsa;
	
	public AbstractPersonaggio(String nome, String presentazione, IO io) {
		this.nome = nome;
		this.presentazione = presentazione;
		this.haSalutato = false;
		this.io=io;
		this.borsa=new Borsa(Integer.MAX_VALUE,io);
	}
	
	
	
	public String getNome() {
		return this.nome;
	}
	
	
	public boolean haSalutato() {
		return this.haSalutato;
	}
	
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return "";
	}
	
	public String saluta() {
		StringBuilder risposta = new StringBuilder("Ciao, io sono ");
		risposta.append(this.getNome()+".");
		if (!haSalutato)
			risposta.append(this.presentazione);
		else
			risposta.append("Ci siamo gia' presentati!");
		this.haSalutato = true;
		return risposta.toString();
	}
	
	public Borsa GetBorsa() {
		return this.borsa;
	}
	
	abstract public String agisci(Partita partita);
	@Override
	public String toString() {
		return this.getNome();
	}


}
