package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IO;

public class StanzaBloccata extends Stanza{
	
	private String nomeAttrezzo;
	private String direzioneBloccata;
	
	public StanzaBloccata(String nome,IO io,String nomeAttrezzo,String direzioneBloccata)
	{
		super(nome,io);
		this.nomeAttrezzo=nomeAttrezzo;
		this.direzioneBloccata=direzioneBloccata;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione)
	{
		if(direzione.equals(this.direzioneBloccata) && !super.hasAttrezzo(nomeAttrezzo))
		{
			return this;
		}
		else
		{
			return super.getStanzaAdiacente(direzione);
		}
	}
	
	@Override
	public String getDescrizione()
	{
		if(!super.hasAttrezzo(nomeAttrezzo))
		{
			return toString();
		}
		return super.getDescrizione();
	}
	
	@Override
	public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(super.getNome());
    	risultato.append("\nUscite: ");
    	for (String direzione : super.getDirezioni())
    		if (direzione!=null)
    		{
    			if(direzione.equals(this.direzioneBloccata))
    			{
    				if(super.hasAttrezzo(this.nomeAttrezzo))
    					risultato.append(" " + direzione);
    			}
    			else
    				risultato.append(" "+direzione);
    					
    		}
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (int i = 0; i < super.getNumeroAttrezzi(); i++) {  
    	    risultato.append(super.getAttrezzi()[i].toString() + " ");
    	}
    	return risultato.toString();
    }

}
