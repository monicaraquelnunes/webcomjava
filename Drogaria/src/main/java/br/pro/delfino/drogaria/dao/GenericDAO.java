package br.pro.delfino.drogaria.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import br.pro.delfino.drogaria.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

//vídeo 150

public class GenericDAO<Entidade> {
	
	private Class <Entidade> classe;
	
	@SuppressWarnings("unchecked")
	public GenericDAO() {  //necessário para fazer os métodos de listagem genéricos
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@SuppressWarnings("deprecation")
	public void salvar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();  //serve para qualquer coisa abaixo dessa linha está protegida pela transação
			sessao.save(entidade); //buscando o domain (entidades)
			transacao.commit();  //onde a transação finaliza
		}catch(RuntimeException erro) {
			if(transacao != null) {  //verificar se a transação é nula
				transacao.rollback();
			}
			throw erro;
		}finally {   //significa que tanto faz se der certo ou errado, ele executa	
			sessao.close();
		}
	}
	
	public List<Entidade> listar(){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		Transaction transacao = null;
        List<Entidade> resultado = null;
        
		try {
			transacao = sessao.beginTransaction();
            CriteriaBuilder builder = sessao.getCriteriaBuilder();
            CriteriaQuery<Entidade> query = builder.createQuery(classe);
            Root<Entidade> root = query.from(classe);
            query.select(root);

            Query<Entidade> q = sessao.createQuery(query);
            resultado = q.getResultList();
            transacao.commit();
            
			//CriteriaQuery<T> consulta = sessao.createCriteria(classe);
			//List<Entidade> resultado = consulta.list();
						
		}catch (RuntimeException erro) {
			throw erro;			
		}finally {
			sessao.close();
		}
		return resultado;
	}
	
	
	public Entidade buscar(Long codigo){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();	
		Transaction transacao = null;
        Entidade resultado = null;
        
		try {
			transacao = sessao.beginTransaction();
            CriteriaBuilder builder = sessao.getCriteriaBuilder();
            CriteriaQuery<Entidade> query = builder.createQuery(classe);
            Root<Entidade> root = query.from(classe);
            
            // Adicionando restrição de igualdade para o código
            Predicate restricao = builder.equal(root.get("codigo"), codigo);
            query.where(restricao);
            
            resultado = sessao.createQuery(query).uniqueResult();
            transacao.commit();
            
			//CriteriaQuery<T> consulta = sessao.createCriteria(classe);
			//List<Entidade> resultado = consulta.list();
					
		}catch (RuntimeException erro) {
			throw erro;			
		}finally {
			sessao.close();
		}		
		return resultado;	
	}
	
	@SuppressWarnings("deprecation")
	public void excluir(Entidade entidade) { //é bem parecido com o fluxo salvar
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();  //serve para qualquer coisa abaixo dessa linha está protegida pela transação
			sessao.delete(entidade); 
			transacao.commit();  //onde a transação finaliza
		}catch(RuntimeException erro) {
			if(transacao != null) {  //verificar se a transação é nula
				transacao.rollback();
			}
			throw erro;
		}finally {   //significa que tanto faz se der certo ou errado, ele executa	
			sessao.close();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void editar(Entidade entidade) { //é bem parecido com o fluxo salvar
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();  //serve para qualquer coisa abaixo dessa linha está protegida pela transação
			sessao.update(entidade); 
			transacao.commit();  //onde a transação finaliza
		}catch(RuntimeException erro) {
			if(transacao != null) {  //verificar se a transação é nula
				transacao.rollback();
			}
			throw erro;
		}finally {   //significa que tanto faz se der certo ou errado, ele executa	
			sessao.close();
		}
	}

}

