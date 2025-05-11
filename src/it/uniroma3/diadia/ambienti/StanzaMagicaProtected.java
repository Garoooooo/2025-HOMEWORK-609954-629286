package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected{
	static final private int SOGLIA_MAGICA=3;
	private int sogliaMagica;
	private int contatoreAttrezzi;
	
	public StanzaMagicaProtected(String nome,IOConsole io)
	{
		super(nome,io);
		this.sogliaMagica=SOGLIA_MAGICA;
		
	}
	public StanzaMagicaProtected(String nome, IOConsole io,int sogliaMagica) {
		super(nome, io);
		this.sogliaMagica=sogliaMagica;
		this.contatoreAttrezzi=0;
	}
	
	public void setSogliaMagica(int sogliaMagica)
	{
		this.sogliaMagica=sogliaMagica;
	}
	
	public int getSogliaMagica()
	{
		return this.sogliaMagica;
	}
	
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo)
	{
		this.contatoreAttrezzi++;
		if(this.contatoreAttrezzi>=this.sogliaMagica)
		{
			trasforma(attrezzo);
		}
		if(this.numeroAttrezzi<this.attrezzi.length)
		{
			this.attrezzi[this.numeroAttrezzi]=attrezzo;
			this.numeroAttrezzi++;
			return true;
		}
		else return false;
	}
	
	public Attrezzo trasforma(Attrezzo attrezzo)
	{
		attrezzo.setNome(new StringBuilder(attrezzo.getNome()).reverse().toString());
		attrezzo.setPeso(attrezzo.getPeso()*2);
		return attrezzo;
	}

}
