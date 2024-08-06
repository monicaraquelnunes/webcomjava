package br.pro.delfino.drogaria.domain;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@SuppressWarnings("serial") 		//Anotação própria do java, serve para o compilador ignorar o Warnings do tipo Serial
@MappedSuperclass  					//Anotação pra dizer que essa classe não pertence a uma tabela, forma de compartilhar uma chave primária
public class GenericDomain implements Serializable {  			//Entidade DominioGenerico para ser usado em todas as outras entidades
	
	@Id  			//serve para dizer ao código que é uma chave primária
	@GeneratedValue(strategy = GenerationType.AUTO) 	//anotação que serve para a chave primária ser gerada de forma automática
	private Long codigo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	

}
