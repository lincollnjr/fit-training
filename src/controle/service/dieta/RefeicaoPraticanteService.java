package controle.service.dieta;

import java.util.ArrayList;
import java.util.List;

import modelo.entidade.dieta.Dieta;
import modelo.entidade.dieta.RefeicaoPraticante;
import modelo.tipo.TipoDiaEnum;
import persistencia.dieta.AlimentoPraticanteDAO;
import persistencia.dieta.RefeicaoPraticanteDAO;

public class RefeicaoPraticanteService {

	private RefeicaoPraticanteDAO refeicaoPraticanteDAO;
	private AlimentoPraticanteDAO alimentoPraticanteDAO;

	public RefeicaoPraticanteDAO getRefeicaoPraticanteDAO() {
		return refeicaoPraticanteDAO;
	}

	public void setRefeicaoPraticanteDAO(
			RefeicaoPraticanteDAO refeicaoPraticanteDAO) {
		this.refeicaoPraticanteDAO = refeicaoPraticanteDAO;
		
	}

	public RefeicaoPraticanteService() {
		refeicaoPraticanteDAO = new RefeicaoPraticanteDAO();
		alimentoPraticanteDAO = new AlimentoPraticanteDAO();
	}

	public List<RefeicaoPraticante> buscarPorDieta(Dieta dieta) {
		List<RefeicaoPraticante> refeicoesPraticante = new ArrayList<RefeicaoPraticante>();
		refeicoesPraticante = refeicaoPraticanteDAO.buscarPorDieta(dieta);
		for(RefeicaoPraticante refeicaoPraticante : refeicoesPraticante){
			refeicaoPraticante.setAlimentosPraticante(alimentoPraticanteDAO.buscarPorRefeicao(refeicaoPraticante));
		}
		return refeicoesPraticante;
	}

	public List<RefeicaoPraticante> buscarPorDiaDieta(TipoDiaEnum dia,
			Dieta dieta) {
		List<RefeicaoPraticante> refeicoesPraticante = new ArrayList<RefeicaoPraticante>();
		refeicoesPraticante = refeicaoPraticanteDAO.buscarPorDiaDieta(dia, dieta);
		for(RefeicaoPraticante refeicaoPraticante : refeicoesPraticante){
			refeicaoPraticante.setAlimentosPraticante(alimentoPraticanteDAO.buscarPorRefeicao(refeicaoPraticante));
		}
		return refeicoesPraticante;
	}

	public boolean excluir(RefeicaoPraticante refeicaoPraticante) {

		return refeicaoPraticanteDAO.excluir(refeicaoPraticante);
	}

	public boolean salvar(RefeicaoPraticante refeicaoPraticante) {

		return refeicaoPraticanteDAO.salvarOuAtualizar(refeicaoPraticante);
	}

}
