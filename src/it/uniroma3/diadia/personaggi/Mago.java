package it.uniroma3.diadia.personaggi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {
	private IO io;
	
	public Mago(String nome, String presentazione, IO io) {
        super(nome, presentazione,io);
        this.io=io;
    }

    @Override
    public String agisci(Partita partita) {
        return "Hai interagito con il mago";
    }
    
    @Override
    public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
        this.GetBorsa().addAttrezzo(attrezzo);
        attrezzo.setPeso(attrezzo.getPeso() / 2);

        Stanza stanza = partita.getStanzaCorrente();
        if (stanza.addAttrezzo(attrezzo)) {
            this.GetBorsa().removeAttrezzo(attrezzo.getNome());
        }
        return this.getNome() + " ha accettato il regalo, lo ha reso più leggero e lo ha lasciato cadere nella stanza.";    }

    
}
