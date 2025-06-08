package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{
	private IO io;
	public Strega(String nome, String presentazione, IO io) {
        super(nome, presentazione,io);
    }

    @Override
    public String agisci(Partita partita) {
        return "Hai interagito con la strega";
    }
    
    @Override
    public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
        this.GetBorsa().addAttrezzo(attrezzo);
        io.mostraMessaggio( "Hahahahahahahahahahahhaahahahahahahahaha...");
        return "la strega si è trattenuta il regalo ridendoti in faccia";
}
}
