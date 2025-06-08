package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IO;

public class StanzaBuia extends Stanza{
	
	private String nomeAttrezzo;
	
	public StanzaBuia(String nome,IO io,String nomeAttrezzo)
	{
		super(nome,io);
		this.nomeAttrezzo=nomeAttrezzo;
	}
	
	@Override
	public String getDescrizione()
	{
		if(!super.hasAttrezzo(nomeAttrezzo))
		{
			return stampaDescrizione();
		}
		return super.getDescrizione();
	}
	
	public String stampaDescrizione()
	{
		return "qui c'è buio pesto";
	}
}
