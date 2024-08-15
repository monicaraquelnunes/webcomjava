package br.pro.delfino.drogaria.bean;

//import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import org.omnifaces.util.Messages;
//import javax.faces.context.FacesContext;

@ManagedBean // Classe responsável por tratar do Controle e o Modelo dentro da Aplicação Web
public class EstadoBean {	
	
	//public void salvar() { //obs: LINHAS 10 A 22 COMENTADAS PARA UTILIZAÇÃO DO OMNIFACES
		//internamente o TomCat transforma a Classe EstadoBem em objeto: EstadoBean estadoBean, para ser usado no xhtml em "actionListener"
		//System.out.println("Programação Web com Java");
		
		//String texto = "Programação Web com Java";
		//FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, texto, texto); //Tipo do Erro, a mensagem resumida, e a msg detalhada vão ser iguais
		
		//Para capturar o contexto do JSF, ou seja, capturar a instancia do JSF
		//FacesContext contexto = FacesContext.getCurrentInstance();
		//contexto.addMessage(null, mensagem); //adicionando a mensagem a ser exibida
	//}
	
	public void salvar() { //utilizando a dependência OmniFaces
		Messages.addGlobalInfo("Programação Web com Java");
	}

}
