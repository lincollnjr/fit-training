package controle.service.dieta;

import java.util.List;

import modelo.entidade.dieta.AlimentoPraticante;
import modelo.entidade.dieta.RefeicaoPraticante;
import persistencia.dieta.AlimentoPraticanteDAO;

public class AlimentoPraticanteService {

	private AlimentoPraticanteDAO alimentoPraticanteDAO;

	public AlimentoPraticanteService() {
		alimentoPraticanteDAO = new AlimentoPraticanteDAO();
	}

	public List<AlimentoPraticante> buscarPorRefeicao(
			RefeicaoPraticante refeicaoPraticante) {
		return alimentoPraticanteDAO.buscarPorRefeicao(refeicaoPraticante);
	}

	public boolean excluir(AlimentoPraticante alimentoPraticante) {
		return alimentoPraticanteDAO.excluir(alimentoPraticante);
	}

	public boolean salvar(AlimentoPraticante alimentoPraticante) {
		return alimentoPraticanteDAO.salvarOuAtualizar(alimentoPraticante);
	}

}
