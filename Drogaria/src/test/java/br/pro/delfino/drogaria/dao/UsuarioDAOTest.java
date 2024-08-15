package br.pro.delfino.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Pessoa;
import br.pro.delfino.drogaria.domain.Usuario;

public class UsuarioDAOTest {
	
	@Ignore
	@Test
	public void salvar() {
		//pesquisando a chave estrangeira Pessoa
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(3L);
		
		System.out.println("Pessoa Encontrada:");
		System.out.println("Nome: " + pessoa.getNome());
		System.out.println("CPF: " + pessoa.getCpf());
		
		Usuario usuario = new Usuario();
		
		usuario.setAtivo(true); //boolean
		usuario.setPessoa(pessoa);
		usuario.setSenha("123456");
		usuario.setTipo('C'); //Tipo é um Caracter, que deverá ser preenchido com uma letra e aspas simples
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
		
		System.out.println("Usuario Salvo com Sucesso!");
	}
	
	@Ignore
	@Test
	public void listar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> resultado = usuarioDAO.listar();
		
		for (Usuario usuario : resultado) {
			System.out.println("Usuário Ativo? " + usuario.getAtivo());
			System.out.println("Nome: " + usuario.getPessoa().getNome());
			System.out.println("Senha: " + usuario.getSenha());
			System.out.println("Tipo: " + usuario.getTipo());
		}
	}
	
	@Ignore
	@Test 
	public void buscar() {
		Long codigo = 1L;
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigo);
		
		System.out.println("O código do usuário é: " + usuario.getCodigo());
		System.out.println("Usuário Ativo? " + usuario.getAtivo());
		System.out.println("Nome: " + usuario.getPessoa().getNome());
		System.out.println("Senha: " + usuario.getSenha());
		System.out.println("Tipo: " + usuario.getTipo());
	}
	
	@Ignore
	@Test
	public void editar() {
		Long codigoUsuario = 1L;
		Long codigoPessoa = 1L;
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigoPessoa); //pesquisando a nova chave estrangeira (Pessoa)
		
		System.out.println("O código da pessoa é: " + pessoa.getCodigo());
		System.out.println("Nome: " + pessoa.getNome());
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigoUsuario);
		
		//alterando a Pessoa (chave estrangeira) junto com o Usuario:
		usuario.setPessoa(pessoa);
		
		usuario.setAtivo(false); //boolean
		usuario.setPessoa(pessoa);
		usuario.setSenha("123456");
		usuario.setTipo('D'); //Tipo é um Caracter, que deverá ser preenchido com uma letra e aspas simples
		
		usuarioDAO.editar(usuario);
		
		System.out.println("Usuário Editado:");
		System.out.println("O código do usuário é: " + usuario.getCodigo());
		System.out.println("Usuário Ativo? " + usuario.getAtivo());
		System.out.println("Nome: " + usuario.getPessoa().getNome());
		System.out.println("Senha: " + usuario.getSenha());
		System.out.println("Tipo: " + usuario.getTipo());	
	}
	
	@Ignore
	@Test
	public void excluir() {
		Long codigo = 2L;
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigo);
		
		usuarioDAO.excluir(usuario);
		
		System.out.println("Usuário REMOVIDO:");
		System.out.println("O código do usuário é: " + usuario.getCodigo());
		System.out.println("Usuário Ativo? " + usuario.getAtivo());
		System.out.println("Nome: " + usuario.getPessoa().getNome());
		System.out.println("Senha: " + usuario.getSenha());
		System.out.println("Tipo: " + usuario.getTipo());
	}

}
