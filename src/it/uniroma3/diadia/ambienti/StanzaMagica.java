package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.*;

public class StanzaMagica extends Stanza {

	static final private int SOGLIA_MAGICA=3;
	private int sogliaMagica;
	private int contatoreAttrezzi;
	
	public StanzaMagica(String nome,IO io)
	{
		super(nome,io);
		this.sogliaMagica=SOGLIA_MAGICA;
		
	}
	public StanzaMagica(String nome, IO io,int sogliaMagica) {
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
		if(this.contatoreAttrezzi>this.sogliaMagica)
		{
			trasforma(attrezzo);
		}
		return super.addAttrezzo(attrezzo);
	}
	
	public Attrezzo trasforma(Attrezzo attrezzo)
	{
		attrezzo.setNome(new StringBuilder(attrezzo.getNome()).reverse().toString());
		attrezzo.setPeso(attrezzo.getPeso()*2);
		return attrezzo;
	}

}
