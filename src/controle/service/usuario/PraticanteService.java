package controle.service.usuario;

import java.util.List;

import modelo.entidade.usuario.Nutricionista;
import modelo.entidade.usuario.Personal;
import modelo.entidade.usuario.Praticante;
import persistencia.usuario.PraticanteDAO;

public class PraticanteService {

	private PraticanteDAO praticanteDAO;

	public PraticanteService() {
		praticanteDAO = new PraticanteDAO();
	}

	public List<Praticante> listar() {
		return praticanteDAO.listar();
	}
	
	public boolean excluir(Praticante praticante){
		return praticanteDAO.excluir(praticante);
	}

	public boolean salvar(Praticante praticante) {
		return praticanteDAO.salvarOuAtualizar(praticante);
	}

	public Praticante buscarPorEmailSenha(String email, String senha) {
		return praticanteDAO.buscarPorEmailSenha(email, senha);
	}

	public Praticante buscarPorEmail(String email) {
		return praticanteDAO.buscarPorEmail(email);
	}

	public void excluirPorPersonal(Praticante praticante) {
		praticanteDAO.excluirPorPersonal(praticante);
	}

	public void excluirPorNutricionista(Praticante praticante) {
		praticanteDAO.excluirPorNutricionista(praticante);
	}

	public void salvarPorPersonal(Praticante praticante, Personal personal) {
		praticanteDAO.salvarPorPersonal(praticante, personal);
	}

	public void salvarPorNutricionista(Praticante praticante,
			Nutricionista nutricionista) {
		praticanteDAO.salvarPorNutricionista(praticante, nutricionista);
	}

	public List<Praticante> buscarPorNomePersonal(String nome, Personal personal) {
		return praticanteDAO.buscarPorNomePersonal(nome, personal);
	}

	public List<Praticante> buscarPorNomeNutricionista(String nome,
			Nutricionista nutricionista) {
		return praticanteDAO.buscarPorNomeNutricionista(nome, nutricionista);
	}

	public List<Praticante> buscarPorNomeNaoPersonal(String nome,
			Personal personal) {
		return praticanteDAO.buscarPorNomeNaoPersonal(nome, personal);
	}

	public List<Praticante> buscarPorNomeNaoNutricionista(String nome,
			Nutricionista nutricionista) {
		return praticanteDAO.buscarPorNomeNaoNutricionista(nome, nutricionista);
	}

	public List<Praticante> buscarPorPersonal(Personal personal) {
		return praticanteDAO.buscarPorPersonal(personal);
	}

	public List<Praticante> buscarPorNutricionista(Nutricionista nutricionista) {
		return praticanteDAO.buscarPorNutricionista(nutricionista);
	}
}
