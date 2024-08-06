package br.pro.delfino.drogaria.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;


@SuppressWarnings("serial")
@Entity
public class Fabricante extends GenericDomain {
	
	@Column(length = 50, nullable = false)
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
