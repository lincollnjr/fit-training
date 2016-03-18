package persistencia.dieta;

import java.util.List;

import modelo.entidade.dieta.Dieta;
import modelo.entidade.dieta.RefeicaoPraticante;
import modelo.tipo.TipoDiaEnum;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import persistencia.util.AbstratoDAO;
import persistencia.util.HibernateUtil;

public class RefeicaoPraticanteDAO extends AbstratoDAO<RefeicaoPraticante>{
	
	private Criteria criteria;
		
	@SuppressWarnings("unchecked")
	public List<RefeicaoPraticante> buscarPorDieta(Dieta dieta){
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(RefeicaoPraticante.class);
		criteria.add(Restrictions.eq("dieta",dieta));
		list = criteria.list();
		sessao.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<RefeicaoPraticante> buscarPorDiaDieta(TipoDiaEnum dia, Dieta dieta){
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(RefeicaoPraticante.class);
		criteria.add(Restrictions.eq("dia",dia));
		criteria.add(Restrictions.eq("dieta",dieta));
		list = criteria.list();
		sessao.close();
		return list;
	}
}
