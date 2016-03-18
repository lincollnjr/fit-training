package controle.service.treino;

import java.util.List;

import modelo.entidade.treino.Exercicio;
import persistencia.treino.ExercicioDAO;

public class ExercicioService {

	private ExercicioDAO exercicioDAO;

	public ExercicioService() {
		exercicioDAO = new ExercicioDAO();
	}

	public List<Exercicio> listar() {
		return exercicioDAO.listar();
	}

	public List<Exercicio> buscarPorNome(String nome) {
		return exercicioDAO.buscarPorNome(nome);
	}

	public boolean salvar(Exercicio exercicio) {
		return exercicioDAO.salvarOuAtualizar(exercicio);
	}

	public boolean excluir(Exercicio exercicio) {
		return exercicioDAO.excluir(exercicio);
	}

}
