package br.com.zup.dojo;

import java.util.Date;

public class Cidade {

	private int cep;
	private String nome;
	private Integer nro_habitantes;
	private boolean capital;
	private String estado;
	private Float renda_per_capita;
	private String data_fundacao;

	public Cidade() {

	}

	public Cidade(int cep, String nome, Integer nro_habitantes, boolean capital, String estado, float renda_per_capita,
			String data_fundacao) {
		this.cep = cep;
		this.nome = nome;
		this.nro_habitantes = nro_habitantes;
		this.capital = capital;
		this.estado = estado;
		this.renda_per_capita = renda_per_capita;
		this.data_fundacao = data_fundacao;
	}

	public int getId_cep() {
		return cep;
	}

	public void setId_cep(int id_cep) {
		this.cep = id_cep;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getNumero_habitantes() {
		return nro_habitantes;
	}

	public void setNumero_habitantes(Integer numero_habitantes) {
		this.nro_habitantes = numero_habitantes;
	}

	public boolean isCapital() {
		return capital;
	}

	public void setCapital(boolean capital) {
		this.capital = capital;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Float getRenda_per_capita() {
		return renda_per_capita;
	}

	public void setRenda_per_capita(Float renda_per_capita) {
		this.renda_per_capita = renda_per_capita;
	}

	public String getData_fundacao() {
		return data_fundacao;
	}

	public void setData_fundacao(String data_fundacao) {
		this.data_fundacao = data_fundacao;
	}

	public String toString() {
		return "Cidade [cep=" + cep + ", nome=" + nome + ", nro_habitantes=" + nro_habitantes + ",capital= " + capital
				+ ", estado " + estado + ", renda_per_capita=" + renda_per_capita + ", data_fundação=" + data_fundacao
				+ " ]";
	}

}
