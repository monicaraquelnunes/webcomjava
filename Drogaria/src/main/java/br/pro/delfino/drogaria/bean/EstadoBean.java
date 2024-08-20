package br.pro.delfino.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.delfino.drogaria.dao.EstadoDAO;
import br.pro.delfino.drogaria.domain.Estado;
//import javax.faces.context.FacesContext;

@SuppressWarnings("serial")
@ManagedBean // Classe responsável por tratar do Controle e o Modelo dentro da Aplicação Web
@ViewScoped //informa que o ManagedBean é View, que o tempo de vida dele fica vivo enquanto está na tela de Estado
public class EstadoBean implements Serializable { //o implements Serializable é necessário para resolver a ADVERTÊNCIA: Definindo valor de atributo não serializável em ViewMap (chave: estadoBean, classe do valor: br.pro.delfino.drogaria.bean.EstadoBean).
	
	/*public void salvar() { //obs: LINHAS COMENTADAS PARA MOSTRAR COMO É SEM A UTILIZAÇÃO DO OMNIFACES
	internamente o TomCat transforma a Classe EstadoBem em objeto: EstadoBean estadoBean, para ser usado no xhtml em "actionListener"
	System.out.println("Programação Web com Java");
	
	String texto = "Programação Web com Java";
	FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, texto, texto); //Tipo do Erro, a mensagem resumida, e a msg detalhada vão ser iguais
	
	Para capturar o contexto do JSF, ou seja, capturar a instancia do JSF
	FacesContext contexto = FacesContext.getCurrentInstance();
	contexto.addMessage(null, mensagem); //adicionando a mensagem a ser exibida
	}*/
	
	
	// gerando um atributo para guardar os dados da tela - PARA A VISÃO CONVERSAR COM O MODELO
	private Estado estado;   //consta no estados.xhtml em value="#{estadoBean.estado.nome} - necessário para fazer a amarra
	
	//criando uma variável que será o modelo da tabela do banco, ou seja, que vai armazerar os dados que a tabela possui
	private List<Estado> estados; //criando uma lista de Estados - em seguida criar o get e set dessa lista
									//em seguida, vincular a visão com o modelo no "estados.xhtml" em Estados - Listagem > p:dataTable > value="#{estadoBean.estados}"
									//após, necessário carregar os Estados conforme abaixo em "public void listar()"	
	
	
	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
	public List<Estado> getEstados() {
		return estados;
	}
	
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
	
	@PostConstruct //é o mesmo que o construtor, ou seja, o método listar é chamado no momento em que a tela é criada - em seguida, acertar a visão em: Estados - Listagem > p:dataTable > var="estado" ><h:outputText value="#{estado.nome}" e no sigla
	public void listar() {
		//obs: sempre que for mexer com algo de banco, é necessário colocar o "try e catch"
		try {
			EstadoDAO estadoDAO = new EstadoDAO(); //preenchendo a listagem com informações de banco
			estados = estadoDAO.listar(); //Método responsável por popular os Estados
			
		}catch (RuntimeException erro) { //só será chamado em situações que acontecer Erros
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Estados");
			erro.printStackTrace(); //necessário para mostrar o Erro no log
		}
	}
	
	
	public void novo() {
		estado = new Estado();  //chamado no estados.xhtml em actionListener="#{estadoBean.novo}"
	}
	
	
	//Controle
	public void salvar() { //utilizando a dependência OmniFaces
		try {
			EstadoDAO estadoDAO = new EstadoDAO(); //Para salvar no Controlador, conforme feito no JUnit
			
			//PARA EDITAR, DEVERÁ SER TROCADO DE "estadoDAO.salvar(estado)" PARA estadoDAO.merge(estado)
			//estadoDAO.salvar(estado); 
			estadoDAO.merge(estado); //o merge serve para: quando tem chave primária, ele EDITA, quando não tem chave primária, ele EXCLUI
			
			Messages.addGlobalInfo("Nome: " + estado.getNome() + " Sigla: " + estado.getSigla());
			
			//para limpar a tela após o "salvar", chamar o Controle "Novo" - em seguida, acertar na tela em estados.xhtml > Estados - Cadastro > update=":mensagem :formCadastro:painel"
			//novo(); //pode ser usado também conforme abaixo:
			estado = new Estado();
			estados = estadoDAO.listar(); //para recarregar(atualizar) a tela de salvar e aparecer o Estado que foi salvo automaticamente, e no "estado.xhtml" atualizar em <h:form id="formListagem", e em "update= :formListagem:tabela"
			
			Messages.addGlobalInfo("Estado salvo com sucesso!");
		
		}catch (RuntimeException erro) { //só será chamado em situações que acontecer Erros
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Estado");
			erro.printStackTrace(); //necessário para mostrar o Erro no log
		}		
	}
	
	
	public void excluir(ActionEvent evento) { //ActionEvent evento - esse objeto serve para capturar eventos que me mandaram de "estados.xhtml", que é "estadoSelecionado"
		try {
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
			
			//para excluir o Estado da tela
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.excluir(estado);
			
			//refazendo a pesquisa para excluir automaticamente o Estado da tela no momento em que for removido
			estados = estadoDAO.listar(); //forçando a recarregar os Estados - em seguida, no estados.xhtml, no botão excluir(opções), em update=":mensagem :formListagem:tabela"
			
			Messages.addGlobalInfo("Nome: " + estado.getNome() + " Sigla: " + estado.getSigla());
			Messages.addGlobalInfo("Estado Removido com Sucesso!");
		}catch(RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Estado!");
			erro.printStackTrace();
		}	
	}
	
	
	public void editar(ActionEvent evento) { //ActionEvent evento - esse objeto serve para capturar eventos que me mandaram de "estados.xhtml", que é <p:commandButton icon="ui-icon-pencil"> "estadoSelecionado"
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
		Messages.addGlobalInfo("Nome: " + estado.getNome() + " Sigla: " + estado.getSigla());
	}
}	
