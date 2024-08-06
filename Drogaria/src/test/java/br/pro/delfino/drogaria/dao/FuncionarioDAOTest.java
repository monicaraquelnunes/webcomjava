package br.pro.delfino.drogaria.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import br.pro.delfino.drogaria.domain.Funcionario;
import br.pro.delfino.drogaria.domain.Pessoa;



public class FuncionarioDAOTest {
	@Disabled
	@Test
	public void salvar() throws ParseException {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(3L);
		
		Funcionario funcionario = new Funcionario();
		funcionario.setCarteiraTrabalho("1234567");
		funcionario.setDataAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse("29/08/2022"));
		funcionario.setPessoa(pessoa);
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.salvar(funcionario);
		
		System.out.println("Funcionário Cadastrado com Sucesso!");
	}
	
	@Disabled
	@Test
	public void listar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		List<Funcionario> resultado = funcionarioDAO.listar();
		
		for (Funcionario funcionario : resultado) {
			//para listar a chave estrangeira, tem que estar acompanhado com um campo da mesma tabela dessa chave
			System.out.println("O funcionário cadastrado é: " + funcionario.getPessoa().getNome());
			System.out.println("Data da Admissão: " + funcionario.getDataAdmissao());
		}	
	}
	
	@Disabled
	@Test
	public void buscar() {
		Long codigo = 1L;
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codigo);
		
		System.out.println("Funcionário: " + funcionario.getPessoa().getNome());
		System.out.println("Admissão: " + funcionario.getDataAdmissao());
	}
	
	@Disabled
	@Test
	public void excluir() {
		Long codigo = 2L;
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codigo);
		
		System.out.println("O Funcionário REMOVIDO foi: " + funcionario.getPessoa().getNome());
	}
}

