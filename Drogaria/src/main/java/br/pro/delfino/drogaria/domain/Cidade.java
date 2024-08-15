package br.pro.delfino.drogaria.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//vídeo 145

@SuppressWarnings("serial")
@Entity
public class Cidade extends GenericDomain {
	
	@Column(length = 50, nullable = false)
	private String nome;
	
	//incluindo a chave estrangeira Estado
	@ManyToOne
	@JoinColumn(nullable = false)  //Serve para priorizar as propriedades de colunas que são chave estrangeira
	private Estado estado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	

}
