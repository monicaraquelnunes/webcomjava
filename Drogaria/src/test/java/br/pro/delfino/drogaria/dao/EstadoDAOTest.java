package br.pro.delfino.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Estado;


public class EstadoDAOTest {
	
	@Ignore
	@Test
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("Brasília");
		estado.setSigla("DF");
		
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);		
	}
	
	@Ignore
	@Test
	public void listar() { //vídeo 153
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();
		
		System.out.println("Total de registros encontrados: " + resultado.size());
		
		for(Estado estado : resultado) {
			System.out.println(estado.getSigla() + " - " + estado.getNome());
		}
	}
	
	@Ignore
	@Test
	public void buscar() { //vídeo 154
		Long codigo = 52L;
		
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		if(estado == null) {
			System.out.println("Nenhum registro encontrado!");
		}else {
			System.out.println("Registro encontrado!");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}		
	}
	
	@Ignore
	@Test
	public void excluir() { //vídeo 155
		Long codigo = 1L;		
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		if(estado == null) {
			System.out.println("Nenhum registro encontrado!");
		}else {
			estadoDAO.excluir(estado);
			System.out.println("Registro removido:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}		
	}
	
	@Ignore
	@Test
	public void editar() { //vídeo 156
		Long codigo = 2L;		
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		if(estado == null) {
			System.out.println("Nenhum registro encontrado!");
		}else {
			System.out.println("Registro editado antes:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
			
			estado.setNome("Minas Gerais");
			estado.setSigla("MG");
			estadoDAO.editar(estado);
			
			System.out.println("Registro editado depois:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}			
	}
}
