package controle.bo;

public class MensagemBO {

	private String mensagem;
	private boolean mostrarMensagem;
	private boolean erro;
	private boolean info;
	private boolean desabilitarInfo;

	public void adicionarMensagemErro(String mensagem) {
		erro = true;
		info = false;
		this.mensagem = mensagem;
		mostrarMensagem = true;
	}

	public void adicionarMensagemInfo(String mensagem) {
		erro = false;
		info = true;
		desabilitarInfo = false;
		this.mensagem = mensagem;
		mostrarMensagem = true;
	}

	public void desabilitarMensagem() {
		if (desabilitarInfo) {
			info = false;
		}

		if (info) {
			desabilitarInfo = true;
			return;
		}
		mostrarMensagem = false;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public boolean isMostrarMensagem() {
		return mostrarMensagem;
	}

	public void setMostrarMensagem(boolean mostrarMensagem) {
		this.mostrarMensagem = mostrarMensagem;
	}

	public boolean isErro() {
		return erro;
	}

	public void setErro(boolean erro) {
		this.erro = erro;
	}

	public boolean isInfo() {
		return info;
	}

	public void setInfo(boolean info) {
		this.info = info;
	}

	public boolean isDesabilitarInfo() {
		return desabilitarInfo;
	}

	public void setDesabilitarInfo(boolean desabilitarInfo) {
		this.desabilitarInfo = desabilitarInfo;
	}

}
