package persistencia.dieta;

import java.util.List;

import modelo.entidade.dieta.Dieta;
import modelo.entidade.usuario.Nutricionista;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import persistencia.util.AbstratoDAO;
import persistencia.util.HibernateUtil;

public class DietaDAO extends AbstratoDAO<Dieta> {

	private Criteria criteria;
	
	@SuppressWarnings("unchecked")
	public List<Dieta> buscarPorNutricionista(Nutricionista nutricionista) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(Dieta.class);
		criteria.add(Restrictions.eq("nutricionista",nutricionista));
		list = criteria.list();
		sessao.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Dieta> buscarPorNome(String nome) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(Dieta.class);
		criteria.add(Restrictions.ilike("nome",nome,MatchMode.ANYWHERE));
		list = criteria.list();
		sessao.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Dieta> buscarPorNomeNutricionista(String nome, Nutricionista nutricionista) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(Dieta.class);
		criteria.add(Restrictions.ilike("nome",nome,MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("nutricionista",nutricionista));
		list = criteria.list();
		sessao.close();
		return list;
	}
}
