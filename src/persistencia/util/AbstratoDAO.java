package persistencia.util;

import java.util.List;

import modelo.entidade.utils.IEntidade;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class AbstratoDAO<T> {

	protected Session sessao;
	protected Transaction transacao;
	protected List<T> list;

	public boolean salvarOuAtualizar(T t) {
		if (((IEntidade) t).getId() == 0) {
			return salvar(t);
		} else {
			return atualizar(t);
		}
	}
	
	public boolean salvar(T t) {
		boolean retorno = false;
		try {			
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.save(t);
			transacao.commit();
			retorno = true;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}

	public boolean atualizar(T t) {
		boolean retorno = false;
		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.update(t);
			transacao.commit();
			retorno = true;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}

	public boolean excluir(T t) {
		boolean retorno = false;
		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.delete(t);
			transacao.commit();
			retorno = true;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}
	

}
