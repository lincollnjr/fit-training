package controle.service.usuario;

import modelo.entidade.usuario.Nutricionista;
import persistencia.usuario.NutricionistaDAO;

public class NutricionistaService {

	private NutricionistaDAO nutricionistaDAO;

	public NutricionistaService(){
		nutricionistaDAO = new NutricionistaDAO();
	}
	
	public boolean salvar(Nutricionista nutricionista){
		return nutricionistaDAO.salvarOuAtualizar(nutricionista);
	}
	
	public Nutricionista buscarPorEmailSenha(String email, String senha) {
		return nutricionistaDAO.buscarPorEmailSenha(email, senha);
	}
	
	public Nutricionista buscarPorEmail(String email) {
		return nutricionistaDAO.buscarPorEmail(email);
	}
}
