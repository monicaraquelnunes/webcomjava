package br.pro.delfino.drogaria.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
 
//vídeo 143 e 144
@SuppressWarnings("serial")
@Entity 		//anotação que serve para informar que a classe Estado é uma entidade do Hibernate
public class Estado extends GenericDomain {     //está herdando a chave primária @Id
	
	@Column(length = 2, nullable = false)   //Anotação que serve para personalizar o atributo e o length é para colocar o tamanho do campo
	private String sigla;
	
	@Column(length = 50, nullable = false)  // o nullable é para informar que não quer que a coluna fique nula
	private String nome;

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
