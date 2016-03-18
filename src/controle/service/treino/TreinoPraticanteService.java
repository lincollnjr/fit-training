package controle.service.treino;

import java.util.List;

import modelo.entidade.treino.TreinoPraticante;
import modelo.entidade.usuario.Praticante;
import persistencia.treino.TreinoPraticanteDAO;

public class TreinoPraticanteService {

	private TreinoPraticanteDAO treinoPraticanteDAO;

	public TreinoPraticanteService() {
		treinoPraticanteDAO = new TreinoPraticanteDAO();
	}

	public List<TreinoPraticante> buscarPorPraticante(Praticante praticante) {
		return treinoPraticanteDAO.buscarPorPraticante(praticante);
	}

	public List<TreinoPraticante> buscarPorNome(String nome) {
		return treinoPraticanteDAO.buscarPorNome(nome);
	}
	
	public boolean salvar(TreinoPraticante treinoPraticante){
		return treinoPraticanteDAO.salvarOuAtualizar(treinoPraticante);
	}
	
	public boolean excluir(TreinoPraticante treinoPraticante){
		return treinoPraticanteDAO.excluir(treinoPraticante);
	}

}
