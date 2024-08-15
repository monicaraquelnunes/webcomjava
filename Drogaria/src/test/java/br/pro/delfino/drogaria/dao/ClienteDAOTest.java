package br.pro.delfino.drogaria.dao;

import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Cliente;
import br.pro.delfino.drogaria.domain.Pessoa;

// aula 163 (DESAFIO): listar, buscar, editar, excluir

public class ClienteDAOTest {
	
	@Ignore
	@Test
	public void salvar() throws ParseException { //throws ParseException é para Tratamento de erro do (new SimpleDateFormat("dd/MM/yyyy").parse("31/07/2024"), ou seja, não quero tratar caso eu digite a data errado.  
		PessoaDAO pessoaDAO = new PessoaDAO(); //Pesquisando a chave estrangeira DAO "PessoaDAO"
		Pessoa pessoa = pessoaDAO.buscar(3L); //Buscando a chave estrangeira Entidade "Pessoa"(3L é o código)		
		
		Cliente cliente = new Cliente(); //criando o cliente para salvar
		cliente.setDataCadastro(new Date()); //campo data de cadastro, new Date é para pegar a Data e hora do sistema
		
		// e outro jeito para trabalhar com a data:
		//cliente.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy").parse("31/07/2024"));
		
		cliente.setLiberado(false); //liberado é o campo booleam - necessário pesquisar a pessoa conforme acima (chave estrangeria)
		cliente.setPessoa(pessoa); //pessoa é o campo identificando qual a pessoa está vinculada (chave estrangeira)
		
		//Criando o ClienteDAO para salvar ele
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.salvar(cliente);
		
		System.out.println("Cliente Salvo com Sucesso!");
	}
	
	@Ignore
	@Test
	public void listar() { //163 - desafio "listar" concluído
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> resultado = clienteDAO.listar();
		
		for (Cliente cliente : resultado) {
			//para listar a chave estrangeira, tem que estar acompanhado com um campo da mesma tabela dessa chave
			System.out.println("O cliente cadastrado é: " + cliente.getPessoa().getNome());
			System.out.println("Data do cadastro: " + cliente.getDataCadastro());
			System.out.println("Foi liberado? " + cliente.getLiberado());		
		}
	}
	
	@Ignore
	@Test
	public void buscar() { //163 - desafio "buscar" concluído 
		Long codigo = 2L;
		
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(codigo);
		
		//para listar a chave estrangeira, tem que estar acompanhado com um campo da mesma tabela dessa chave
		System.out.println("Cliente: " + cliente.getPessoa().getNome());
		System.out.println("Data do cadastro: " + cliente.getDataCadastro());
		System.out.println("Foi liberado? " + cliente.getLiberado());		 
	}
	
	@Ignore
	@Test
	public void excluir() { //163 - desafio "excluir" concluído (esse método é bem parecido com o buscar)
		Long codigo = 102L;
		
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(codigo);
		
		//para listar a chave estrangeira, tem que estar acompanhado com um campo da mesma tabela dessa chave
		System.out.println("Cliente REMOVIDO: " + cliente.getPessoa().getNome());
		System.out.println("O código do cliente removido é: " + cliente.getCodigo());
		System.out.println("Data do cadastro: " + cliente.getDataCadastro());		
	}
}
