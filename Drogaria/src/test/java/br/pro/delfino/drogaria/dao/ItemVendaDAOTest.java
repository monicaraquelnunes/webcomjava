package br.pro.delfino.drogaria.dao;


import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import br.pro.delfino.drogaria.domain.Funcionario;
import br.pro.delfino.drogaria.domain.ItemVenda;
import br.pro.delfino.drogaria.domain.Produto;

public class ItemVendaDAOTest {
	
	@Disabled
	@SuppressWarnings("removal")
	@Test
	public void salvar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(3L);
		
		FuncionarioDAO	funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(2L);
		
		ItemVenda itemVenda = new ItemVenda();
		
		itemVenda.setQuantidade(new Short("2"));
		itemVenda.setPrecoParcial(new BigDecimal("33.80"));
		itemVenda.setProduto(produto);
		itemVenda.setFuncionario(funcionario);
		
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		itemVendaDAO.salvar(itemVenda);
		
		System.out.println("Item venda cadastrado com Sucesso!");		
	}
	
	@Disabled
	@Test
	public void listar() {
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		List<ItemVenda> resultado = itemVendaDAO.listar();
		
		for (ItemVenda itemVenda : resultado) {
			System.out.println("Quantidade de itens por venda: " + itemVenda.getQuantidade());
			System.out.println("Preço parcial: " + itemVenda.getPrecoParcial());
			System.out.println("Produto: " + itemVenda.getProduto().getDescricao());
			System.out.println("Funcionário: " + itemVenda.getFuncionario().getCodigo());
		}
	}
	
	@Disabled
	@Test
	public void buscar() {
		Long codigo = 52L;
		
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscar(codigo);
		
		System.out.println("Quantidade de itens por venda: " + itemVenda.getQuantidade());
		System.out.println("Preço parcial: " + itemVenda.getPrecoParcial());
		System.out.println("Produto: " + itemVenda.getProduto().getDescricao());
		System.out.println("Funcionário: " + itemVenda.getFuncionario().getCodigo());
	}
	
	@Disabled
	@SuppressWarnings("removal")
	@Test
	public void editar() {
		Long codigoItemVenda = 102L;
		//para editar, é necessário incluir as chaves estrangeiras(Cliente e Fabricante)
		Long codigoFuncionario = 2L;
		Long codigoProduto = 3L;
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codigoFuncionario); //pesquisando a nova chave estrangeira(Funcionario)
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigoProduto); //pesquisando a nova chave estrangeira(Produto)
		
		System.out.println("Código do Funcionário: " + funcionario.getCodigo());
		System.out.println("Códito do Produto" + produto.getCodigo());
		
		//pesquisando o item venda a ser editado:
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscar(codigoItemVenda);
		
		System.out.println("Quantidade de itens por venda: " + itemVenda.getQuantidade());
		System.out.println("Preço parcial: " + itemVenda.getPrecoParcial());
		System.out.println("Produto: " + itemVenda.getProduto().getDescricao());
		System.out.println("Funcionário: " + itemVenda.getFuncionario().getCodigo());
		
		//alterando o Funcionario e o Produto(chaves estrangeiras) junto com o item da venda:
		itemVenda.setPrecoParcial(new BigDecimal("24.60"));
		itemVenda.setQuantidade(new Short("4"));
		itemVenda.setFuncionario(funcionario);
		itemVenda.setProduto(produto);
		
		itemVendaDAO.editar(itemVenda);
		
		System.out.println("Quantidade Editada de itens por venda: " + itemVenda.getQuantidade());
		System.out.println("Preço parcial Editado: " + itemVenda.getPrecoParcial());
		System.out.println("Produto: " + itemVenda.getProduto().getDescricao());
		System.out.println("Funcionário: " + itemVenda.getFuncionario().getCodigo());			
	}
	
	@Disabled
	@Test
	public void excluir() {
		Long codigoItemVenda = 102L;
		
		//pesquisando o ItemVenda a ser excluído:
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscar(codigoItemVenda);
		
		itemVendaDAO.excluir(itemVenda);
		
		System.out.println("Item Venda REMOVIDO:");
		System.out.println("Quantidade de itens por venda: " + itemVenda.getQuantidade());
		System.out.println("Preço parcial: " + itemVenda.getPrecoParcial());
		System.out.println("Produto: " + itemVenda.getProduto().getDescricao());
		System.out.println("Funcionário: " + itemVenda.getFuncionario().getCodigo());
	}
}