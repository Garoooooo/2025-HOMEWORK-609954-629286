package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Configuratore;
import it.uniroma3.diadia.IO;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = Configuratore.getPesoMaxBorsa();
	private Map<String,Attrezzo> attrezzi=new HashMap<>();
	private int numeroAttrezzi;
	private int pesoMax;
	private IO io;

	public Borsa(IO io) {
		this(DEFAULT_PESO_MAX_BORSA, io);
	}

	public Borsa(int pesoMax, IO io) {
		this.pesoMax = pesoMax;
		this.io=io;
		this.numeroAttrezzi = 0;
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if(attrezzi.size()==10)
		{
			return false;
		}
		attrezzi.put(attrezzo.getNome(), attrezzo);
		this.numeroAttrezzi++;
		return true;
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if(attrezzi.containsKey(nomeAttrezzo))
		{
			return attrezzi.get(nomeAttrezzo);
		}
		return null;
	}

	public int getPeso() {
		int[] peso = {0};
		attrezzi.forEach((nome,attrezzo)->{
			peso[0]+=attrezzo.getPeso();
		});
		return peso[0];
	}

	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a=null;
		if(attrezzi.containsKey(nomeAttrezzo))
		{
			a=attrezzi.get(nomeAttrezzo);
			attrezzi.remove(nomeAttrezzo);
			return a;
		}
		io.mostraMessaggio("oggetto non presente in borsa e non rimosso");
		return null;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			s.append(attrezzi.values().toString());
		} else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso()
	{
		List<Attrezzo> listaAttrezzi=new ArrayList<>(attrezzi.values());
		listaAttrezzi.sort((a1,a2)->{
			int confrontoPeso=Integer.compare(a1.getPeso(),a2.getPeso());
			if(confrontoPeso!=0)
			{
				return confrontoPeso;
			}
			return a1.getNome().compareTo(a2.getNome());
		});
		return listaAttrezzi;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome()
	{
		SortedSet<Attrezzo> setAttrezzi=new TreeSet<>(
				(a1,a2)->a1.getNome().compareTo(a2.getNome())
		);
		setAttrezzi.addAll(attrezzi.values());
		return setAttrezzi;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso()
	{
		Map<Integer,Set<Attrezzo>> mappaAttrezzi=new HashMap<>();
		attrezzi.forEach((nome,attrezzo)->{
			if(!mappaAttrezzi.containsKey(attrezzo.getPeso()))
			{
				mappaAttrezzi.put(attrezzo.getPeso(),new HashSet<Attrezzo>());
			}
			mappaAttrezzi.get(attrezzo.getPeso()).add(attrezzo);
		});
		return mappaAttrezzi;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso()
	{
		SortedSet<Attrezzo> setAttrezzi=new TreeSet<>((a1,a2)->{
			int confrontoPeso=Integer.compare(a1.getPeso(),a2.getPeso());
			if(confrontoPeso!=0)
			{
				return confrontoPeso;
			}
			return a1.getNome().compareTo(a2.getNome());
		});
		setAttrezzi.addAll(attrezzi.values());
		return setAttrezzi;
	}
}
