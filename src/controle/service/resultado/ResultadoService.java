package controle.service.resultado;

import java.util.List;

import modelo.entidade.resultado.Resultado;
import modelo.entidade.usuario.Praticante;
import persistencia.resultado.ResultadoDAO;

public class ResultadoService {

	private ResultadoDAO resultadoDAO;

	public ResultadoService() {
		resultadoDAO = new ResultadoDAO();
	}

	public boolean salvar(Resultado resultado) {

		return resultadoDAO.salvarOuAtualizar(resultado);

	}

	public boolean excluir(Resultado resultado) {
		return resultadoDAO.excluir(resultado);

	}

	public List<Resultado> buscarPorPraticante(Praticante praticante) {
		return resultadoDAO.buscarPorPraticante(praticante);
	}

}
