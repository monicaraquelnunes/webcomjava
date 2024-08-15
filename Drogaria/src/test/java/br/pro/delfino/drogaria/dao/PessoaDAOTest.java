package br.pro.delfino.drogaria.dao;


import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Cidade;
import br.pro.delfino.drogaria.domain.Pessoa;

public class PessoaDAOTest { //aula 162 (DESAFIO- criar a classe pessoaDAO): salvar, listar, buscar, editar, excluir
	
	@Ignore
	@Test
	public void salvar() {
		
		CidadeDAO cidadeDAO = new CidadeDAO(); //incluindo a chave estrangeira Cidade
		Cidade cidade = cidadeDAO.buscar(new Long("3"));
		
		Pessoa pessoa = new Pessoa(); //buscando a entidade Pessoa
		
		pessoa.setNome("Mônica Raquel Nunes Carvalho");
		pessoa.setCpf("72602627100");
		pessoa.setRg("2129651");
		pessoa.setRua("Centro");
		pessoa.setNumero(new Short("1102")); //o número é um campo do tipo Short, ou seja, tem que criar o new Short
		pessoa.setBairro("Taguatinga Centro");
		pessoa.setCep("72010010");
		pessoa.setComplemento("Residencial Taguaparque");
		pessoa.setTelefone("61 982488332");
		pessoa.setCelular("61 982488332");
		pessoa.setEmail("mraquel625@gmail.com");
		pessoa.setCidade(cidade); //Cidade é a CHAVE ESTRANGEIRA
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.salvar(pessoa);
			
		System.out.println("Pessoa Incluída com Sucesso!");
	}
	
	@Ignore
	@Test
	public void listar() { //162 - desafio "listar" concluído
		
		PessoaDAO pessoaDAO = new PessoaDAO(); //buscando a entidade pessoa
		List<Pessoa> resultado = pessoaDAO.listar(); //listando a Pessoa
		
		for (Pessoa pessoa : resultado) {
			System.out.println("A pessoa incluída é: " + pessoa.getNome());
		}
	}
	
	@Ignore
	@Test  //162 - desafio "buscar" concluído
	public void buscar() { 
		Long codigo = 2L;
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigo);
		
		System.out.println("O código da pessoa é: " + pessoa.getCodigo());
		System.out.println("O nome da pessoa é: " + pessoa.getNome());
	}
	
	@Ignore
	@Test
	public void editar() { //162 - desafio "editar" a pessoa concluído
		
		Long codigoPessoa = 3L;
		Long codigoCidade = 1L; //para editar uma pessoa, tem que incluir a chave estrangeira
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		@SuppressWarnings("unused")
		Cidade cidade = cidadeDAO.buscar(codigoCidade); //pesquisando a chave estrangeira(Cidade)
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigoPessoa); //pesquisando a pessoa a ser editada
		
		System.out.println("O código da pessoa é: " + pessoa.getCodigo());
		System.out.println("O nome da pessoa é: " + pessoa.getNome());
		
		//para listar a chave estrangeira, tem que estar acompanhado com um campo da mesma tabela dessa chave
		System.out.println("Cidade: " + pessoa.getCidade().getNome());		
	}
	
	@Ignore
	@Test
	public void excluir() { //162 - desafio "excluir" o pessoa concluído
		Long codigo = 3L;
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigo);
		
		pessoaDAO.excluir(pessoa);
		
		System.out.println("Pessoa REMOVIDA:");
		System.out.println("O código da pessoa removida é: " + pessoa.getCodigo());
		System.out.println("Nome: " + pessoa.getNome());
		
		//para listar a chave estrangeira, tem que estar acompanhado com um campo da mesma tabela dessa chave
		System.out.println("Cidade: " + pessoa.getCidade().getNome());	
	}	
}
