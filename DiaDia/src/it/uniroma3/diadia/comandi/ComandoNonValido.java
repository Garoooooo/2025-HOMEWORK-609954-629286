package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	private String parametro;
	
    @Override
    public void esegui(Partita partita) {
        System.out.println("Comando non valido.");
   }
    
    @Override
    public void setParametro(String parametro) {
    	this.parametro=parametro;
    } 
    
    @Override
    public String getNome()
    {
    	return "nonvalido";
    }
    
    @Override
    public String getParametro()
    {
    	return this.parametro;
    }
}