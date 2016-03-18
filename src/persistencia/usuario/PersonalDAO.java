package persistencia.usuario;

import modelo.entidade.usuario.Personal;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import persistencia.util.AbstratoDAO;
import persistencia.util.HibernateUtil;

public class PersonalDAO extends AbstratoDAO<Personal> {

	private Criteria criteria;

	@SuppressWarnings("unchecked")
	public Personal buscarPorEmailSenha(String email, String senha) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(Personal.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("senha", senha));
		list = criteria.list();
		sessao.close();
		if (list.isEmpty())
			return null;
		return (Personal) list.get(0);
	}

	@SuppressWarnings("unchecked")
	public Personal buscarPorEmail(String email) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(Personal.class);
		criteria.add(Restrictions.eq("email", email));
		list = criteria.list();
		sessao.close();
		if (list.isEmpty())
			return null;
		return (Personal) list.get(0);
	}
	
}
