package controle.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controle.bo.AutenticacaoBO;
import controle.util.MensagemUtil;

@ManagedBean(name = "autenticacaoBean")
@SessionScoped
public class AutenticacaoBean extends MensagemUtil implements Serializable {

	private static final long serialVersionUID = 2030806237366089252L;

	private AutenticacaoBO autenticacaoBO;

	@PostConstruct
	public void init() {

		autenticacaoBO = new AutenticacaoBO();

		autenticacaoBO.iniciar();

	}

	public AutenticacaoBO getAutenticacaoBO() {
		return autenticacaoBO;
	}

	public void setAutenticacaoBO(AutenticacaoBO autenticacaoBO) {
		this.autenticacaoBO = autenticacaoBO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
