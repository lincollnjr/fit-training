package persistencia.treino;

import java.util.List;

import modelo.entidade.treino.Exercicio;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import persistencia.util.AbstratoDAO;
import persistencia.util.HibernateUtil;

public class ExercicioDAO extends AbstratoDAO<Exercicio> {

	private Criteria criteria;

	@SuppressWarnings("unchecked")
	public List<Exercicio> listar() {
		sessao = HibernateUtil.getSessionFactory().openSession();
		list = sessao.createCriteria(Exercicio.class).list();
		sessao.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Exercicio> buscarPorNome(String nome) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(Exercicio.class);
		criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		list = criteria.list();
		sessao.close();
		return list;
	}

}
