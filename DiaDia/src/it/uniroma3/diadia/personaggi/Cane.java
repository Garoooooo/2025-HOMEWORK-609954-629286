package it.uniroma3.diadia.personaggi;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	
	private IO io;
	
    public Cane(String nome, String presentazione,IO io) {
        super(nome, presentazione,io);
        this.io=io;
    }
    
    //private IOConsole io=new IOConsole();

    @Override
    public String agisci(Partita partita) {
        return "Hai interagito con il cane";
    }
    
    @Override
    public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
        String ciboPreferito = "osso";
        if (attrezzo.getNome().equalsIgnoreCase(ciboPreferito)) {
            Attrezzo bastone = new Attrezzo("bastone", 1,io);
            partita.getStanzaCorrente().addAttrezzo(bastone);
            return this.getNome() + " accetta il " + attrezzo.getNome() + " e lascia cadere il bastone.";
        } else {
            partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
            return this.getNome() + " rifiuta il " + attrezzo.getNome() + " e ti morde: perdi 1 CFU!";
        }
    }
    
    
    
}
