package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.ambienti.Direzione;
import java.util.EnumMap;
import java.util.Map;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/
public class Stanza {
	
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI  = 10;
	
	private String nome;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private Map<Direzione, Stanza> stanzeAdiacenti;
	private IO io;
	private AbstractPersonaggio personaggio;
	
    public Stanza(String nome,IO io) {
    	this.nome = nome;
    	this.io = io;
        this.numeroAttrezzi = 0;
        this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
        this.stanzeAdiacenti = new EnumMap<>(Direzione.class);
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
        if (this.stanzeAdiacenti.size() < NUMERO_MASSIMO_DIREZIONI
            || this.stanzeAdiacenti.containsKey(direzione)) {
            this.stanzeAdiacenti.put(direzione, stanza);
        }
    }
    
    public AbstractPersonaggio getPersonaggio() {
    	return this.personaggio;
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(Direzione direzione) {
        return this.stanzeAdiacenti.get(direzione);
	}
	
    public String getNome() {
        return this.nome;
    }

    public String getDescrizione() {
        return this.toString();
    }

    public Attrezzo[] getAttrezzi() {
        return this.attrezzi;
    }

    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
        	this.attrezzi[this.numeroAttrezzi++] = attrezzo;
        	return true;
        }
        return false;
    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite:");
    	for (Direzione d : this.stanzeAdiacenti.keySet())
    		risultato.append(" " + d);
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (int i = 0; i < numeroAttrezzi; i++)
    	    risultato.append(attrezzi[i] + " ");
    	return risultato.toString();
    }

	public boolean hasAttrezzo(String nomeAttrezzo) {
		for (int i = 0; i < this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				return true;
		return false;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (int i = 0; i < this.numeroAttrezzi; i++)
			if (attrezzi[i].getNome().equals(nomeAttrezzo))
				return attrezzi[i];
		return null;	
	}

	public boolean removeAttrezzo(Attrezzo attrezzo) {
	    for (int i = 0; i < this.numeroAttrezzi; i++) {
	        if (attrezzo.equals(attrezzi[i])) {
	            for (int j = i; j < this.numeroAttrezzi - 1; j++)
	                attrezzi[j] = attrezzi[j+1];
	            attrezzi[--numeroAttrezzi] = null;
	            return true;
	        }
	    }
	    return false;
	}
	
	public Direzione[] getDirezioni() {
		return this.stanzeAdiacenti.keySet().toArray(new Direzione[0]);
    }

	public int getNumeroMassimoAttrezzi() {
		return NUMERO_MASSIMO_ATTREZZI;
	}
	
	public int getNumeroAttrezzi() {
		return this.numeroAttrezzi;
	}
}
