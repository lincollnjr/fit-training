package controle.service.usuario;

import modelo.entidade.usuario.Personal;
import persistencia.usuario.PersonalDAO;

public class PersonalService {

	private PersonalDAO personalDAO;
	
	public PersonalService(){
		personalDAO = new PersonalDAO();
	}

	public boolean salvar(Personal personal) {
		return personalDAO.salvarOuAtualizar(personal);
	}

	public Personal buscarPorEmailSenha(String email, String senha) {
		return personalDAO.buscarPorEmailSenha(email, senha);
	}
	
	public Personal buscarPorEmail(String email) {
		return personalDAO.buscarPorEmail(email);
	}
}
