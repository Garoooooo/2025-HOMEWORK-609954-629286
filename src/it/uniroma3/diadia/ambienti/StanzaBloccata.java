/*  StanzaBloccata.java  */
package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IO;

public class StanzaBloccata extends Stanza {

	private String    nomeAttrezzo;
	private Direzione direzioneBloccata;

	public StanzaBloccata(String nome, IO io, String nomeAttrezzo, Direzione direzioneBloccata) {
		super(nome,io);
		this.nomeAttrezzo      = nomeAttrezzo;
		this.direzioneBloccata = direzioneBloccata;
	}


	public StanzaBloccata(String nome, IO io, String nomeAttrezzo, String direzioneBloccata) {
		this(nome, io, nomeAttrezzo, Direzione.fromString(direzioneBloccata));
	}

	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if (direzione.equals(this.direzioneBloccata) && !super.hasAttrezzo(nomeAttrezzo))
			return this;
		return super.getStanzaAdiacente(direzione);
	}

	public Stanza getStanzaAdiacente(String direzione) {
		return getStanzaAdiacente(Direzione.fromString(direzione));
	}

	@Override
	public String getDescrizione() {
		if (!super.hasAttrezzo(nomeAttrezzo))
			return super.getDescrizione() + "\nDirezione bloccata: " + this.direzioneBloccata +
			       " (serve attrezzo: " + this.nomeAttrezzo + ")";
		return super.getDescrizione();
	}

	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(super.getNome());
		risultato.append("\nUscite: ");
		for (Direzione direzione : super.getDirezioni())
			if (direzione != null) {
				if (direzione.equals(this.direzioneBloccata)) {
					if (super.hasAttrezzo(this.nomeAttrezzo))
						risultato.append(" ").append(direzione);
				} else
					risultato.append(" ").append(direzione);
			}
		risultato.append("\nAttrezzi nella stanza: ");
		for (int i = 0; i < super.getNumeroAttrezzi(); i++)
			risultato.append(super.getAttrezzi()[i].toString()).append(" ");
		return risultato.toString();
	}
}
