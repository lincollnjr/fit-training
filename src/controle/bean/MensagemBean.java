package controle.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controle.bo.MensagemBO;

@ManagedBean(name = "mensagemBean")
@SessionScoped
public class MensagemBean implements Serializable {

	private static final long serialVersionUID = -2851293274666356963L;

	private MensagemBO mensagemBO;

	@PostConstruct
	private void ini() {
		mensagemBO = new MensagemBO();

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public MensagemBO getMensagemBO() {
		return mensagemBO;
	}

	public void setMensagemBO(MensagemBO mensagemBO) {
		this.mensagemBO = mensagemBO;
	}

}
