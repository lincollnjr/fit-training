package persistencia.resultado;

import java.util.List;

import modelo.entidade.resultado.Resultado;
import modelo.entidade.usuario.Praticante;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import persistencia.util.AbstratoDAO;
import persistencia.util.HibernateUtil;

public class ResultadoDAO extends AbstratoDAO<Resultado> {

	private Criteria criteria;

	@SuppressWarnings("unchecked")
	public List<Resultado> buscarPorPraticante(Praticante praticante) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(Resultado.class);
		criteria.add(Restrictions.eq("praticante", praticante));
		criteria.addOrder(Order.asc("data"));
		list = criteria.list();
		sessao.close();
		return list;
	}

}
