package persistencia.dieta;

import java.util.List;

import modelo.entidade.dieta.DietaPraticante;
import modelo.entidade.usuario.Praticante;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import persistencia.util.AbstratoDAO;
import persistencia.util.HibernateUtil;

public class DietaPraticanteDAO extends AbstratoDAO<DietaPraticante>{
	
	private Criteria criteria;
	
	@SuppressWarnings("unchecked")
	public List<DietaPraticante> buscarPorPraticante(Praticante praticante){
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(DietaPraticante.class);
		criteria.add(Restrictions.eq("praticante",praticante));
		list = criteria.list();
		sessao.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<DietaPraticante> buscarPorNome(String nome){
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(DietaPraticante.class);
		criteria.add(Restrictions.ilike("nome",nome,MatchMode.ANYWHERE));
		list = criteria.list();
		sessao.close();
		return list;
	}
	
}
