package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;


public class ComandoAiuto extends AbstractComando {
    private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

    @Override
    public void esegui(Partita partita) {
        for (String comando : elencoComandi)
            System.out.print(comando + " ");
        System.out.println();
    }
    
    @Override
    public String getNome()
    {
    	return "aiuto";
    }
    
    @Override
    public String getParametro()
    {
    	return null;
    }
}
