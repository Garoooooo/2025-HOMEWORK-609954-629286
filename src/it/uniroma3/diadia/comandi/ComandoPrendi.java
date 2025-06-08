package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {
    private String nomeAttrezzo;

    @Override
    public void esegui(Partita partita) {
        if (nomeAttrezzo == null) {
            System.out.println("Devi specificare quale attrezzo prendere");
            return;
        }

        Attrezzo attrezzo = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);

        if (attrezzo == null) {
            System.out.println("Attrezzo " + nomeAttrezzo + " non presente nella stanza");
            return;
        }

        if (partita.getGiocatore().getBorsa().addAttrezzo(attrezzo)) {
            partita.getStanzaCorrente().removeAttrezzo(attrezzo);
            System.out.println("Hai preso: " + nomeAttrezzo);
        } else {
            System.out.println("Non puoi prendere " + nomeAttrezzo + ": borsa piena o troppo pesante");
        }
    }

    @Override
    public void setParametro(String parametro) {
        this.nomeAttrezzo = parametro;
    }
    
    @Override
    public String getNome()
    {
    	return "prendi";
    }
    
    @Override
    public String getParametro()
    {
    	return this.nomeAttrezzo;
    }
}
