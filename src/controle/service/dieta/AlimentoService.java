package controle.service.dieta;

import java.util.List;

import modelo.entidade.dieta.Alimento;
import persistencia.dieta.AlimentoDAO;

public class AlimentoService {

	private AlimentoDAO alimentoDAO; 
	
	public AlimentoService(){
		alimentoDAO = new AlimentoDAO();
	}
	
	public boolean salvar(Alimento alimento) {
		return alimentoDAO.salvarOuAtualizar(alimento);
	}

	public boolean excluir(Alimento alimento) {
		return alimentoDAO.excluir(alimento);
	}
	
	public List<Alimento> listar(){
		return alimentoDAO.listar();
	}
	
	public List<Alimento> buscarPorNome(String nome){
		return alimentoDAO.buscarPorNome(nome);
	} 
	
	
}
