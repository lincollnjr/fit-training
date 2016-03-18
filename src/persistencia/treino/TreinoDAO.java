package persistencia.treino;

import java.util.List;

import modelo.entidade.treino.Treino;
import modelo.entidade.usuario.Personal;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import persistencia.util.AbstratoDAO;
import persistencia.util.HibernateUtil;

public class TreinoDAO extends AbstratoDAO<Treino> {

	private Criteria criteria;

	@SuppressWarnings("unchecked")
	public List<Treino> buscarPorPersonal(Personal personal) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(Treino.class);
		criteria.add(Restrictions.eq("personal", personal));
		list = criteria.list();
		sessao.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Treino> buscarPorNome(String nome) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(Treino.class);
		criteria.add(Restrictions.ilike("nome", nome,MatchMode.ANYWHERE));
		list = criteria.list();
		sessao.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Treino> buscarPorNomePersonal(String nome, Personal personal) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(Treino.class);
		criteria.add(Restrictions.ilike("nome", nome,MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("personal", personal));
		list = criteria.list();
		sessao.close();
		return list;
	}
}
