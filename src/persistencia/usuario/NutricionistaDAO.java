package persistencia.usuario;

import modelo.entidade.usuario.Nutricionista;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import persistencia.util.AbstratoDAO;
import persistencia.util.HibernateUtil;

public class NutricionistaDAO extends AbstratoDAO<Nutricionista> {

	private Criteria criteria;

	@SuppressWarnings("unchecked")
	public Nutricionista buscarPorEmailSenha(String email, String senha) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(Nutricionista.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("senha", senha));
		list = criteria.list();
		sessao.close();
		if (list.isEmpty())
			return null;
		return (Nutricionista) list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public Nutricionista buscarPorEmail(String email) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(Nutricionista.class);
		criteria.add(Restrictions.eq("email", email));
		list = criteria.list();
		sessao.close();
		if (list.isEmpty())
			return null;
		return (Nutricionista) list.get(0);
	}
}
