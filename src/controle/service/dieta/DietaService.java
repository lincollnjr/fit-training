package controle.service.dieta;

import java.util.List;

import modelo.entidade.dieta.Dieta;
import modelo.entidade.usuario.Nutricionista;
import persistencia.dieta.DietaDAO;

public class DietaService {

	private DietaDAO dietaDAO;

	public DietaService() {
		dietaDAO = new DietaDAO();
	}

	public List<Dieta> buscarPorNomeNutricionista(String nome, Nutricionista nutricionista) {
		return dietaDAO.buscarPorNomeNutricionista(nome, nutricionista);
	}
	
	public List<Dieta> buscarPorNutricionista(Nutricionista nutricionista) {
		return dietaDAO.buscarPorNutricionista(nutricionista);
	}

	public List<Dieta> buscarPorNome(String nome) {
		return dietaDAO.buscarPorNome(nome);
	}

	public boolean excluir(Dieta dieta) {
		return dietaDAO.excluir(dieta);
	}

	public boolean salvar(Dieta dieta) {
		return dietaDAO.salvarOuAtualizar(dieta);
	}

}
