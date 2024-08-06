package br.pro.delfino.drogaria.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import br.pro.delfino.drogaria.domain.Cidade;
import br.pro.delfino.drogaria.domain.Cliente;
import br.pro.delfino.drogaria.domain.Estado;
import br.pro.delfino.drogaria.domain.Fabricante;
import br.pro.delfino.drogaria.domain.Funcionario;
import br.pro.delfino.drogaria.domain.ItemVenda;
import br.pro.delfino.drogaria.domain.Pessoa;
import br.pro.delfino.drogaria.domain.Produto;
import br.pro.delfino.drogaria.domain.Usuario;
import br.pro.delfino.drogaria.domain.Venda;


public class HibernateUtil {
	private static SessionFactory fabricaDeSessoes = criarFabricaDeSessoes();
	
	public static SessionFactory getFabricaDeSessoes() {
		return fabricaDeSessoes;
	}
	
	private static SessionFactory criarFabricaDeSessoes() {
		try {
			Configuration configuration = new Configuration();
			
			// Configurações do Hibernate
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/drogaria");
            configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.password", "sua_senha_aqui");
            //configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.format_sql", "true");
            configuration.setProperty("hibernate.hbm2ddl.auto", "validate");

            // Adiciona as classes de entidade
            configuration.addAnnotatedClass(Cidade.class);
            configuration.addAnnotatedClass(Cliente.class);
            configuration.addAnnotatedClass(Estado.class);
            configuration.addAnnotatedClass(Fabricante.class);
            configuration.addAnnotatedClass(Funcionario.class);
            configuration.addAnnotatedClass(ItemVenda.class);
            configuration.addAnnotatedClass(Pessoa.class);
            configuration.addAnnotatedClass(Produto.class);
            configuration.addAnnotatedClass(Usuario.class);
            configuration.addAnnotatedClass(Venda.class);
            
			
			ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			 
			SessionFactory fabrica = configuration.buildSessionFactory(registro);
			return fabrica;
		}catch(Throwable ex) {
			//Make sure you log the exception, as it might be swallowed
			System.err.println("A fábrica de sessões não pode ser criada." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

}
