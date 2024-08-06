package br.pro.delfino.drogaria.domain;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

//vídeo 149

@SuppressWarnings("serial")
@Entity
public class Venda extends GenericDomain {
	
	@Column
	@Temporal(TemporalType.TIMESTAMP) //Anotação informando que terá data e hora no banco
	private Date horario;
	
	@Column(nullable = false, precision = 7, scale = 2) //precision é informando o total de dígitos e o scale é para qtd de números após a vírgula
	private BigDecimal precoTotal;
	
	@ManyToOne //chave estrangeira indicando que são muitos para um
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(nullable = false) //Anotação informando que o preenchimento é obrigatório
	private Funcionario funcionario;

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	
	
	
	
}
