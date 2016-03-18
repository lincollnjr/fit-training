package persistencia.dieta;

import java.util.List;

import modelo.entidade.dieta.Alimento;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import persistencia.util.AbstratoDAO;
import persistencia.util.HibernateUtil;


public class AlimentoDAO extends AbstratoDAO<Alimento>{

	private Criteria criteria;
	
	@SuppressWarnings("unchecked")
	public List<Alimento> buscarPorNome(String nome){
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(Alimento.class);
		criteria.add(Restrictions.ilike("nome",nome,MatchMode.ANYWHERE));
		list = criteria.list();
		sessao.close();
		return list;
	} 
	
	@SuppressWarnings("unchecked")
	public List<Alimento> listar(){
		sessao = HibernateUtil.getSessionFactory().openSession();
		list = sessao.createCriteria(Alimento.class).list();
		sessao.close();
		return list;
	}
	
}
