package controle.util;

import javax.el.ELResolver;
import javax.faces.context.FacesContext;

import controle.bean.MensagemBean;

public class MensagemUtil {

	protected final String HOME = "home";
	protected final String ERRO = "";
	protected final String MENSAGEM_ITEM_NAO_SELECIONADO = "Selecione um item.";
	protected final String MENSAGEM_CAMPOS_VAZIOS = "Preencha todos os campos.";
	protected final String MENSAGEM_EMAIL_SENHA_INCORRETOS = "Email ou senha incorretos.";
	protected final String MENSAGEM_EMAIL_EXISTENTE = "E	mail já existente na base de dados, favor tentar outro.";
	protected final String MENSAGEM_HORA = "A hora deve ter um ou dois caracteres";
	protected final String MENSAGEM_MINUTO = "O minuto deve ter um ou dois caracteres";
	protected final String SALVO_SUCESSO = "Salvo com sucesso.";
	protected final String EXCLUIDO_SUCESSO = "Excluido com sucesso.";

	private MensagemBean mensagemBean;

	public MensagemUtil() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context != null) {
			ELResolver resolver = context.getApplication().getELResolver();
			mensagemBean = (MensagemBean) resolver.getValue(
					context.getELContext(), null, "mensagemBean");
		}

	}

	public void adicionarMensagemErro(String mensagem) {
		if (mensagemBean != null) {
			mensagemBean.getMensagemBO().adicionarMensagemErro(mensagem);
		}
	}

	public void adicionarMensagemInfo(String mensagem) {
		if (mensagemBean != null) {
			mensagemBean.getMensagemBO().adicionarMensagemInfo(mensagem);
		}
	}

	public void desabilitarMensagem() {
		if (mensagemBean != null) {
			mensagemBean.getMensagemBO().desabilitarMensagem();
		}
	}

}
