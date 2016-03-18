package persistencia.dieta;

import java.util.List;

import modelo.entidade.dieta.AlimentoPraticante;
import modelo.entidade.dieta.RefeicaoPraticante;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import persistencia.util.AbstratoDAO;
import persistencia.util.HibernateUtil;

public class AlimentoPraticanteDAO extends AbstratoDAO<AlimentoPraticante> {

	private Criteria criteria;

	@SuppressWarnings("unchecked")
	public List<AlimentoPraticante> listar() {
		sessao = HibernateUtil.getSessionFactory().openSession();
		list = sessao.createCriteria(AlimentoPraticante.class).list();
		sessao.close();
		return list;

	}

	@SuppressWarnings("unchecked")
	public List<AlimentoPraticante> buscarPorRefeicao(
			RefeicaoPraticante refeicaoPraticante) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(AlimentoPraticante.class);
		criteria.add(Restrictions.eq("refeicaoPraticante", refeicaoPraticante));
		list = criteria.list();
		sessao.close();
		return list;
	}

}
