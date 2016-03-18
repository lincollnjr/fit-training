package controle.service.treino;

import java.util.List;

import modelo.entidade.treino.Treino;
import modelo.entidade.usuario.Personal;
import persistencia.treino.TreinoDAO;

public class TreinoService {

	private TreinoDAO treinoDAO;

	public TreinoService() {
		treinoDAO = new TreinoDAO();
	}

	public List<Treino> buscarPorNomePersonal(String nome, Personal personal) {
		return treinoDAO.buscarPorNomePersonal(nome, personal);
	}
	
	public List<Treino> buscarPorPersonal(Personal personal) {
		return treinoDAO.buscarPorPersonal(personal);
	}

	public List<Treino> buscarPorNome(String nome) {
		return treinoDAO.buscarPorNome(nome);
	}

	public boolean excluir(Treino treino) {
		return treinoDAO.excluir(treino);
	}

	public boolean salvar(Treino treino) {
		return treinoDAO.salvarOuAtualizar(treino);
	}

}
