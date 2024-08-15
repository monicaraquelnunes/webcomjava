package br.pro.delfino.drogaria.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Cliente;
import br.pro.delfino.drogaria.domain.Funcionario;
import br.pro.delfino.drogaria.domain.Venda;

public class VendaDAOTest {
	
	@Ignore
	@Test
	public void salvar() {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(2L);
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(2L);
		
		Venda venda = new Venda();
		venda.setHorario(new Date());
		venda.setPrecoTotal(new BigDecimal("124.69"));
		venda.setCliente(cliente);
		venda.setFuncionario(funcionario);
		
		VendaDAO vendaDAO = new VendaDAO();
		vendaDAO.salvar(venda);
		
		System.out.println("Venda realizada com Sucesso!");
	}	
	
	@Ignore
	@Test
	public void listar() {
		VendaDAO vendaDAO = new VendaDAO();
		List<Venda> resultado = vendaDAO.listar();
	
		for (Venda venda : resultado) {
			System.out.println("Venda efetuada no valor total de: " + venda.getPrecoTotal());
		}
	}
	
	@Ignore
	@Test
	public void buscar() {
		Long codigo = 2L;
		
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(codigo);
		
		System.out.println("O código da venda é: " + venda.getCodigo());
		System.out.println("Valor total : " + venda.getPrecoTotal());
		
		//para listar a chave estrangeira, tem que estar acompanhado com um campo da mesma tabela dessa chave
		System.out.println("Cliente: " + venda.getCliente().getCodigo());
		System.out.println("Funcionário que efetuou a venda: " + venda.getFuncionario().getCodigo());
				
	}
	
	@Ignore
	@Test
	public void editar() {
		Long codigoVenda = 2L;
		//para editar, é necessário incluir as chaves estrangeiras(Cliente e Fabricante)
		Long codigoCliente = 102L; 
		Long codigoFuncionario = 2L;
		
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(codigoCliente); //pesquisando a nova chave estrangeira(Cliente)
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codigoFuncionario); //pesquisando a nova chave estrangeira(Funcionario)
		
		System.out.println("Código do Cliente: " + cliente.getCodigo());
		System.out.println("Código do Funcionário: " + funcionario.getCodigo());
		
		//pesquisando a venda a ser editada:
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(codigoVenda);
		
		System.out.println("O código da venda é: " + venda.getCodigo());
		System.out.println("Valor: " + venda.getPrecoTotal());
		
		//alterando o Cliente(chave estrangeira) junto com o valor da venda:
		venda.setPrecoTotal(new BigDecimal("40.35"));
		venda.setCliente(cliente);
		
		vendaDAO.editar(venda);
		
		System.out.println("O código da venda editada é: " + venda.getCodigo());
		System.out.println("Cliente: " + venda.getCliente().getCodigo());			
	}
	
	@Ignore
	@Test
	public void excluir() {
		Long codigoVenda = 52L;
		
		//pesquisando a venda a ser excluída:
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(codigoVenda);
		
		vendaDAO.excluir(venda);
		
		System.out.println("Venda REMOVIDA:");
		System.out.println("O código da venda é: " + venda.getCodigo());
		System.out.println("Valor: " + venda.getPrecoTotal());
	}
}
