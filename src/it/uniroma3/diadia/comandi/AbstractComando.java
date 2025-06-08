package it.uniroma3.diadia.comandi;

public abstract class AbstractComando implements Comando{

	private String parametro;
	
	@Override 
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}
	
	  public String getParametro() {
	        return parametro;
	    }
}
