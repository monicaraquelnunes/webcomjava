package br.pro.delfino.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.delfino.drogaria.dao.CidadeDAO;
import br.pro.delfino.drogaria.dao.EstadoDAO;
import br.pro.delfino.drogaria.domain.Cidade;
import br.pro.delfino.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {
	
	// gerando um atributo para guardar os dados da tela para salvar no futuro - PARA A VISÃO CONVERSAR COM O MODELO
	private Cidade cidade; //Para fazer a amarra com a tela ir em cidades.xhtml em <p:dialog, <h:panelGrid, <p:inputText value="#{cidadeBean.cidade.nome}" 
	
	private List<Cidade> cidades;  //1º criar uma listagem onde vai guardar as Cidades do Banco
	   //2º vincular o objeto na visão (cidades.xhtml) dentro de <p:dataTable em value="#{cidadeBean.cidades}", isso para ligar o managedBean com o xhtml	

	private List<Estado> estados; //1ºAtributo para criar a listagem de Estado (que é a chave estrangeira)
								  //2º para amarrar esse atribudo do ManagedBean no xhtml, ir em: cidades.xhtml <p:dialog header="Cidades - Cadastro", <p:selectOneMenu <f:selectItems value="#{cidadeBean.estados}"
	
	//Getter e Setter do Atributo Cidade
	public Cidade getCidade() {
		return cidade;
	}
	
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
		
	//Getter e Setter da Lista cidades
	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	//Getter e Setter Lista estados
	public List<Estado> getEstados() {
		return estados;
	}
	
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
	//Criando o método de Listar, que é chamado quando a tela é criada
	@PostConstruct
	public void listar() {
		
		//para acessar ao banco, é necessário usar o try e catch para evitar problemas
		try{		
			//chamando o método listar
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listar();
			
		}catch(RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as Cidades!");
			erro.printStackTrace();
		}	
	}
	
	
	//Método para Instanciar a Cidade
	public void novo() {  //para chamar no xhtml, ir em cidades xhtml: <p:panel <f:facet <p:commandButton value="Novo" actionListener="#{cidadeBean.novo}
		
		//para acessar ao banco, é necessário usar o try e catch para evitar problemas
		try {
		cidade = new Cidade();
		
		//populando a coleção(lista) de Estados, que é a chave estrangeira
		EstadoDAO estadoDAO = new EstadoDAO();
		estados = estadoDAO.listar("nome");
		
		}catch(RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar uma nova Cidade!");
			erro.printStackTrace();
		}
	}
	
	//Implementando o botão salvar, que também serve para Editar
	public void salvar() {
		//para acessar ao banco, é necessário usar o try e catch para evitar problemas
		try {
			//Criando o cidadeDAO e o merge, que serve tanto para editar como salvar:
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.merge(cidade);
			
			//limpando o objeto:
			cidade = new Cidade();
			
			//atualizando os Estados:
			EstadoDAO estadoDAO = new EstadoDAO();
			
			//recarregando o combo de Estados:
			estados = estadoDAO.listar();
			
			//atualizando a tabela de cidades:
			cidades = cidadeDAO.listar();
			
			//Implementando a mensagem de sucesso
			Messages.addGlobalInfo("Cidade salva com sucesso!"); 
		}catch(RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova Cidade!");
			erro.printStackTrace();
		}
	}
	
	//Criando o método excluir
	public void excluir(ActionEvent evento) {  //ActionEvent evento - esse objeto serve para capturar eventos que me mandaram de "cidades.xhtml", que é "cidadeSelecionada"
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");
			
			//para excluir a Cidade da tela
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.excluir(cidade);
			
			//refazendo a pesquisa para excluir automaticamente a Cidade da tela no momento em que for removida
			cidades = cidadeDAO.listar(); //forçando a recarregar as Cidades - em seguida, no cidades.xhtml, no botão excluir(opções), em update=":mensagem :formListagem:tabela"
			
			Messages.addGlobalInfo("Nome: " + cidade.getNome());
			Messages.addGlobalInfo("Cidade Removida com Sucesso!");
		}catch(RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a Cidade!");
			erro.printStackTrace();
		}	
		
	}
	
	public void editar(ActionEvent evento) {
		//para acessar ao banco, é necessário usar o try e catch para evitar problemas
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");
		
			//populando a coleção(lista) de Estados, que é a chave estrangeira
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();
		
		}catch(RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma Cidade!");
			erro.printStackTrace();
		}
		
	}
}
