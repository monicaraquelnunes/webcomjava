package br.pro.delfino.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.delfino.drogaria.dao.CidadeDAO;
import br.pro.delfino.drogaria.dao.EstadoDAO;
import br.pro.delfino.drogaria.dao.PessoaDAO;
import br.pro.delfino.drogaria.domain.Cidade;
import br.pro.delfino.drogaria.domain.Estado;
import br.pro.delfino.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean // Classe responsável por tratar do Controle e o Modelo dentro da Aplicação Web
@ViewScoped //informa que o ManagedBean é View, que o tempo de vida dele fica vivo enquanto está na tela de Pessoa
public class PessoaBean implements Serializable {
	
	// gerando um atributo para guardar os dados da tela - PARA A VISÃO CONVERSAR COM O MODELO
	private Pessoa pessoa;   //consta no pessoas.xhtml em value="#{pessoaBean.pessoa.nome} - necessário para fazer a amarra
	
	
	//criando uma variável que será o modelo da tabela do banco, ou seja, que vai armazerar os dados que a tabela possui
	private List<Pessoa> pessoas; //criando uma lista de Pessoas - em seguida criar o get e set dessa lista
										//em seguida, vincular a visão com o modelo no "pessoas.xhtml" em Pessoas - Listagem > p:dataTable > value="#{pessoaBean.pessoas}"
										//após, necessário carregar as Pessoas conforme abaixo em "public void listar()"

	//Criando uma variável temporária de Estado para filtrar as Cidades com o Estado (combos dependentes)
	private Estado estado;
	
	//Criando 2 listagens, uma para guardar a lista de Estados e outra para guardar a lista de Cidades
	private List<Estado> estados;	
	private List<Cidade> cidades;
 
	//Métodos Getter e Setter
	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	public List<Pessoa> getPessoas() {
		return pessoas;
	}


	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	public List<Cidade> getCidades() {
		return cidades;
	}
	
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
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
	
	
	@PostConstruct //é o mesmo que o construtor, ou seja, o método listar é chamado no momento em que a tela é criada - em seguida, acertar a visão em: Pessoas - Listagem > p:dataTable > var="pessoa" ><h:outputText value="#{pessoa.nome}" e no sigla
	public void listar() {
		//obs: sempre que for mexer com algo de banco, é necessário colocar o "try e catch"
		try {
			PessoaDAO pessoaDAO = new PessoaDAO(); //preenchendo a listagem com informações de banco
			pessoas = pessoaDAO.listar("nome"); //Método responsável por popular as Pessoas
			
		}catch (RuntimeException erro) { //só será chamado em situações que acontecer Erros
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as Pessoas");
			erro.printStackTrace(); //necessário para mostrar o Erro no log
		}
	}
	
	public void novo() {
		try {
		pessoa = new Pessoa(); //para buscar no banco de dados a Pessoa
		
		estado = new Estado(); //para limpar a tela no novo
		
		//para buscar no banco de dados os Estados(chave estrangeira)
		EstadoDAO estadoDAO = new EstadoDAO();
		estados = estadoDAO.listar("nome"); //buscando os Estados, e no parêntese está o listar ordenando pelo "nome"
		
		//instanciando uma lista de Cidades vazia
		cidades = new ArrayList<Cidade>();
		}catch(RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar uma nova Pessoa!");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		
	}
	
	public void salvar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.merge(pessoa);
			
			pessoas = pessoaDAO.listar("nome");
			
			pessoa = new Pessoa();
			
			estado = new Estado(); //para limpar a tela de salvar
			
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");
			
			cidades = new ArrayList<>();
			
		} catch(RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a Pessoa!");
			erro.printStackTrace();
		}
	}
	
	public void excluir() {
		
	}
	
	//método criado para popular as cidades com base no Estado
	public void popular() {
		try {
			if (estado != null) {
				//System.out.println("Código: " + estado.getCodigo() + "\nNome: " + estado.getNome());	
				
				//para chamar as Cidades, vamos criar o Dao das cidades	
				CidadeDAO cidadeDAO = new CidadeDAO();
				cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
				//System.out.println("Total: " + cidades.size());
			} else {
				cidades = new ArrayList<Cidade>();
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as cidades!");
			erro.printStackTrace();
		}
		
		
	}
	
}
