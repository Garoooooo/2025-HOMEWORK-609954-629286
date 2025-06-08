package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoPosa implements Comando {
    private String nomeAttrezzo;

    @Override
    public void esegui(Partita partita) {
        if (nomeAttrezzo == null) {
            System.out.println("Specificare il nome dell'attrezzo");
            return;
        }

        Borsa borsa = partita.getGiocatore().getBorsa();
        Stanza stanza = partita.getStanzaCorrente();
        Attrezzo attrezzo = borsa.getAttrezzo(nomeAttrezzo);

        if (attrezzo == null) {
            System.out.println("Attrezzo '" + nomeAttrezzo + "' non presente nella borsa");
            return;
        }

        if (stanza.addAttrezzo(attrezzo)) {
            borsa.removeAttrezzo(nomeAttrezzo);
            System.out.println("Hai posato: " + nomeAttrezzo);
        } else {
            System.out.println("Impossibile posare '" + nomeAttrezzo + "': stanza piena");
        }
    }

    @Override
    public void setParametro(String parametro) {
        this.nomeAttrezzo = parametro;
    }
    
    @Override
    public String getNome()
    {
    	return "posa";
    }
    
    @Override
    public String getParametro()
    {
    	return this.nomeAttrezzo;
    }
}
