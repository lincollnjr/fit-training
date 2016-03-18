package persistencia.treino;

import java.util.List;

import modelo.entidade.treino.ExercicioPraticante;
import modelo.entidade.treino.Treino;
import modelo.tipo.TipoDiaEnum;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import persistencia.util.AbstratoDAO;
import persistencia.util.HibernateUtil;

public class ExercicioPraticanteDAO extends AbstratoDAO<ExercicioPraticante> {

	private Criteria criteria;

	@SuppressWarnings("unchecked")
	public List<ExercicioPraticante> buscarPorDiaTreino(TipoDiaEnum dia,Treino treino) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(ExercicioPraticante.class);
		criteria.add(Restrictions.eq("dia", dia));
		criteria.add(Restrictions.eq("treino", treino));
		list = criteria.list();
		sessao.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ExercicioPraticante> buscarPorTreino(Treino treino) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(ExercicioPraticante.class);
		criteria.add(Restrictions.eq("treino", treino));
		list = criteria.list();
		sessao.close();
		return list;
	}
}
