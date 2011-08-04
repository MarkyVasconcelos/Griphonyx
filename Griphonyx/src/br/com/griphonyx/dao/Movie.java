package br.com.griphonyx.dao;

public class Movie {

	private String nomeFilme;
	private String sinopse;
	private int nota;
	private boolean assistido;

	public String getNomeFilme() {
		return nomeFilme;
	}

	public void setNomeFilme(String nomeFilme) {
		this.nomeFilme = nomeFilme;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public int getNota() {
		return nota;
	}
	
	public void setAssistido(boolean assistido) {
		this.assistido = assistido;
	}

	public boolean isAssistido() {
		return assistido;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}
	
	@Override
	public String toString() {
		return "Filme: "+nomeFilme+" Nota: "+nota;
	}
	
}
