package controle.service.treino;

import java.util.List;

import modelo.entidade.treino.ExercicioPraticante;
import modelo.entidade.treino.Treino;
import modelo.tipo.TipoDiaEnum;
import persistencia.treino.ExercicioPraticanteDAO;

public class ExercicioPraticanteService {

	private ExercicioPraticanteDAO exercicioPraticanteDAO;

	public ExercicioPraticanteService() {
		exercicioPraticanteDAO = new ExercicioPraticanteDAO();
	}

	public List<ExercicioPraticante> buscarPorDiaTreino(TipoDiaEnum dia, Treino treino) {
		return exercicioPraticanteDAO.buscarPorDiaTreino(dia,treino);
	}

	public List<ExercicioPraticante> buscarPorTreino(Treino treino) {
		return exercicioPraticanteDAO.buscarPorTreino(treino);
	}

	public boolean excluir(ExercicioPraticante exercicioPraticante) {
		return exercicioPraticanteDAO.excluir(exercicioPraticante);
	}

	public boolean salvar(ExercicioPraticante exercicioPraticante) {
		return exercicioPraticanteDAO.salvarOuAtualizar(exercicioPraticante);
	}

}
