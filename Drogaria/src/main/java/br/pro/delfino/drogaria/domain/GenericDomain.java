package br.pro.delfino.drogaria.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial") 		//Anotação própria do java, serve para o compilador ignorar o Warnings do tipo Serial
@MappedSuperclass  					//Anotação pra dizer que essa classe não pertence a uma tabela, forma de compartilhar uma chave primária
public class GenericDomain implements Serializable {  			//Entidade DominioGenerico para ser usado em todas as outras entidades
	
	@Id  			//serve para dizer ao código que é uma chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) 	//anotação que serve para a chave primária ser gerada de forma automática
	private Long codigo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	//Código necessário para salvar a tela com a chave estrangeira, que também é necessário no cidades.xhtml em <p:selectOneMenu converter="omnifaces.SelectItemsConverter"
	@Override
	public String toString() {
		return String.format("%s[codigo=%d]", getClass().getSimpleName(), getCodigo());
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericDomain other = (GenericDomain) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
	

}
