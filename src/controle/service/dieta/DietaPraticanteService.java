package controle.service.dieta;

import java.util.List;

import modelo.entidade.dieta.DietaPraticante;
import modelo.entidade.usuario.Praticante;
import persistencia.dieta.DietaPraticanteDAO;

public class DietaPraticanteService {

	private DietaPraticanteDAO dietaPraticanteDAO;
	
	public DietaPraticanteService(){
		dietaPraticanteDAO = new DietaPraticanteDAO();
	}
	
	public List<DietaPraticante> buscarPorPraticante(Praticante praticante){
		return dietaPraticanteDAO.buscarPorPraticante(praticante);
	}
	
	public List<DietaPraticante> buscarPorNome(String nome){
		return dietaPraticanteDAO.buscarPorNome(nome);
	}
	
	public boolean salvar(DietaPraticante dietaPraticante){
		return dietaPraticanteDAO.salvarOuAtualizar(dietaPraticante);
	}
	
	public boolean excluir(DietaPraticante dietaPraticante){
		return dietaPraticanteDAO.excluir(dietaPraticante);
	}
	
}
