package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.Direzione;

public class ComandoVai extends AbstractComando {
    private Direzione direzione;
    private String parametro;

    @Override
    public void esegui(Partita partita) {
        Stanza stanzaCorrente = partita.getStanzaCorrente();
        if (direzione == null) {
            System.out.println("Dove vuoi andare? Devi specificare una direzione");
            return;
        }
        
        Stanza prossima = stanzaCorrente.getStanzaAdiacente(direzione);
        
        if(prossima==null)
        {
        	System.out.println("Direzione inesistente");
        	return;
        }
        
        partita.setStanzaCorrente(prossima);
        System.out.println(prossima.getNome());
        System.out.println(prossima.getDescrizione());
        partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
    }

    @Override
    public void setParametro(String parametro) {
        this.parametro = parametro;
        if (parametro != null)
            this.direzione = Direzione.fromString(parametro);
        else
            this.direzione = null;
    }
    
    @Override
    public String getNome()
    {
    	return "vai";
    }
    
    @Override
    public String getParametro()
    {
    	return this.parametro;
    }
    
}