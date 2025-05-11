package it.uniroma3.diadia.attrezzi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Una semplice classe che modella un attrezzo. Gli attrezzi possono trovarsi
 * all'interno delle stanze del labirinto. Ogni attrezzo ha un nome ed un peso.
 *
 * @author docente di POO
 * @see Stanza
 * @version base
 */
public class Attrezzo {

	private String nome;
	private int peso;
	private IO io;

	/**
	 * Crea un attrezzo
	 * 
	 * @param nome il nome che identifica l'attrezzo
	 * @param peso il peso dell'attrezzo
	 */
	public Attrezzo(String nome, int peso, IO io) {
		this.io=io;
		this.peso = peso;
		this.nome = nome;
	}

	/**
	 * Restituisce il nome identificatore dell'attrezzo
	 * 
	 * @return il nome identificatore dell'attrezzo
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce il peso dell'attrezzo
	 * 
	 * @return il peso dell'attrezzo
	 */
	public int getPeso() {
		return this.peso;
	}
	
	/*
	    attrezzo.setNome(new StringBuilder(attrezzo.getNome()).reverse().toString());
		attrezzo.setPeso(attrezzo.getPeso()*2);
		*/
	
	public void setNome(String a) {
		this.nome=a;
	}
	
	public void setPeso(int a) {
		this.peso=a;
	}
	

	/**
	 * Restituisce una rappresentazione stringa di questo attrezzo
	 * 
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		return this.getNome() + " (" + this.getPeso() + "kg)";
	}

}