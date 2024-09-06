package br.pro.delfino.drogaria.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

// a implementação do ServletContextListener tem 2 métodos, 1 para qdo o Tomcat é ligado e o outro para qdo o Tomcat é desligado
public class HibernateContexto implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent event) { //método para quando eu desligo o tomcat
		// destruindo a fábrica de sessões
		HibernateUtil.getFabricaDeSessoes().close();
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) { //método para quando ligar o tomcat
		// pedindo uma sessão para forçar a chamada ao hibernate quando o tomcat for ligado
		HibernateUtil.getFabricaDeSessoes().openSession();
		
	}

}
