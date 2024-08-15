package br.pro.delfino.drogaria.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Fabricante;
import br.pro.delfino.drogaria.domain.Produto;


public class ProdutoDAOTest { //162 (DESAFIO): listar, buscar, editar, excluir
	
	@Ignore
	@Test
	public void salvar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(new Long("3"));
		
		Produto produto = new Produto();
		produto.setDescricao("Cataflan 50 mg com 20 comprimidos"); //a Descrição é uma String
		produto.setFabricante(fabricante); //o Fabricante é a CHAVE ESTRANGEIRA
		produto.setPreco(new BigDecimal("13.70")); //o Preço é um campo BigDecimal, ou seja, tem que criar o new BigDecimal
		produto.setQuantidade(new Short("7")); //a Quantidade é um campo do tipo Short, ou seja, tem que criar o new Short
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
		
		System.out.println("Produto Salvo com Sucesso!");
	}
	
	@Ignore
	@Test
	public void listar() { //162 - desafio "listar" concluído
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> resultado = produtoDAO.listar(); 
		
		for (Produto produto : resultado) {
			System.out.println("A descrição do produto é: " + produto.getDescricao());
			
			//para listar a chave estrangeira, tem que estar acompanhado com um campo da mesma tabela dessa chave
			System.out.println("O fabricante é: " + produto.getFabricante().getDescricao());
			
			System.out.println("Preço do produto: " + produto.getPreco());
			System.out.println("Quantidade:  " + produto.getQuantidade());
			System.out.println();
		}
	}
	
	@Ignore
	@Test
	public void buscar() { //162 - desafio "buscar" concluído
		Long codigo = 102L;
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);
		
		System.out.println("O código do produto é: " + produto.getCodigo());
		System.out.println("A descrição do produto é: " + produto.getDescricao());
		
		//para listar a chave estrangeira, tem que estar acompanhado com um campo da mesma tabela dessa chave
		System.out.println("O fabricante é: " + produto.getFabricante().getDescricao());
		
		System.out.println("Preço do produto: " + produto.getPreco());
		System.out.println("Quantidade:  " + produto.getQuantidade());	
	}
	
	@Ignore
	@Test
	public void editar() { //162 - desafio "editar" o produto concluído
		Long codigoProduto = 1L;
		Long codigoFabricante = 2L;  //para editar um produto, tem que incluir a chave estrangeira
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigoFabricante); //pesquisando a nova chave estrangeira(fabricante)
		
		System.out.println("Código do Fabricante: " + fabricante.getCodigo());
		System.out.println("Descrição do Fabricante: " + fabricante.getDescricao());
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigoProduto); //pesquisando o produto a ser editado
		
		System.out.println("O código do produto é: " + produto.getCodigo());
		System.out.println("A descrição do produto é: " + produto.getDescricao());
		
		//para listar a chave estrangeira, tem que estar acompanhado com um campo da mesma tabela dessa chave
		System.out.println("O fabricante é: " + produto.getFabricante().getDescricao());
		
		System.out.println("Preço do produto: " + produto.getPreco());
		System.out.println("Quantidade:  " + produto.getQuantidade());	
		
		
		//alterando o Fabricante(chave estrangeira) junto com o produto
		produto.setDescricao("Novalgina");
		produto.setFabricante(fabricante);
		
		produtoDAO.editar(produto);
		
		System.out.println("O código do produto editado é: " + produto.getCodigo());
		System.out.println("Descrição do produto: " + produto.getDescricao());
		
		//para listar a chave estrangeira, tem que estar acompanhado com um campo da mesma tabela dessa chave
		System.out.println("Fabricante: " + produto.getFabricante().getDescricao());
		
		System.out.println("Preço do produto: " + produto.getPreco());
		System.out.println("Quantidade:  " + produto.getQuantidade());	
		
	}
	
	@Ignore
	@Test
	public void excluir() { //162 - desafio "excluir" o produto concluído
		Long codigo = 1L;
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);
		
		produtoDAO.excluir(produto); 
		
		System.out.println("Produto REMOVIDO:");
		System.out.println("O código do produto removido é: " + produto.getCodigo());
		System.out.println("Descrição do produto: " + produto.getDescricao());
		
		//para listar a chave estrangeira, tem que estar acompanhado com um campo da mesma tabela dessa chave
		System.out.println("Fabricante: " + produto.getFabricante().getDescricao());
		
		System.out.println("Preço do produto: " + produto.getPreco());
		System.out.println("Quantidade:  " + produto.getQuantidade());	
		
	}

}
