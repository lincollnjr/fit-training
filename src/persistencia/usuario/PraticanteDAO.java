package persistencia.usuario;

import java.util.List;

import modelo.entidade.usuario.Nutricionista;
import modelo.entidade.usuario.Personal;
import modelo.entidade.usuario.Praticante;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import persistencia.util.AbstratoDAO;
import persistencia.util.HibernateUtil;

public class PraticanteDAO extends AbstratoDAO<Praticante> {

	private Criteria criteria;

	@SuppressWarnings("unchecked")
	public Praticante buscarPorEmailSenha(String email, String senha) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(Praticante.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("senha", senha));
		list = criteria.list();
		sessao.close();
		if (list.isEmpty())
			return null;
		return (Praticante) list.get(0);
	}

	@SuppressWarnings("unchecked")
	public Praticante buscarPorEmail(String email) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(Praticante.class);
		criteria.add(Restrictions.eq("email", email));
		list = criteria.list();
		sessao.close();
		if (list.isEmpty())
			return null;
		return (Praticante) list.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Praticante> listar() {
		sessao = HibernateUtil.getSessionFactory().openSession();
		list = sessao.createCriteria(Praticante.class).list();
		return list;
	}

	public void excluirPorPersonal(Praticante praticante) {
		praticante.setPersonal(null);
		salvarOuAtualizar(praticante);
	}

	public void excluirPorNutricionista(Praticante praticante) {
		praticante.setNutricionista(null);
		salvarOuAtualizar(praticante);
	}

	public void salvarPorPersonal(Praticante praticante, Personal personal) {
		praticante.setPersonal(personal);
		salvarOuAtualizar(praticante);
	}

	public void salvarPorNutricionista(Praticante praticante,
			Nutricionista nutricionista) {
		praticante.setNutricionista(nutricionista);
		salvarOuAtualizar(praticante);

	}

	@SuppressWarnings("unchecked")
	public List<Praticante> buscarPorNomePersonal(String nome, Personal personal) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(Praticante.class);
		criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("personal", personal));

		list = criteria.list();
		sessao.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Praticante> buscarPorNomeNaoPersonal(String nome,
			Personal personal) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(Praticante.class);
		criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		criteria.add(Restrictions.isNull("personal"));
		list = criteria.list();
		sessao.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Praticante> buscarPorNomeNutricionista(String nome,
			Nutricionista nutricionista) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(Praticante.class);
		criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("nutricionista", nutricionista));

		list = criteria.list();
		sessao.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Praticante> buscarPorNomeNaoNutricionista(String nome,
			Nutricionista nutricionista) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(Praticante.class);
		criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		criteria.add(Restrictions.isNull("nutricionista"));  
		list = criteria.list();
		sessao.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Praticante> buscarPorPersonal(Personal personal) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(Praticante.class);
		criteria.add(Restrictions.eq("personal", personal));
		list = criteria.list();
		sessao.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Praticante> buscarPorNutricionista(Nutricionista nutricionista) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		criteria = sessao.createCriteria(Praticante.class);
		criteria.add(Restrictions.eq("nutricionista", nutricionista));
		list = criteria.list();
		sessao.close();
		return list;
	}
}
