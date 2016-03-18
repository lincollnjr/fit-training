package controle.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controle.bo.AlimentoBO;
import controle.util.MensagemUtil;

@ManagedBean(name = "alimentoBean")
@SessionScoped
public class AlimentoBean extends MensagemUtil implements Serializable {

	private static final long serialVersionUID = -2851293274666356963L;

	private AlimentoBO alimentoBO;

	@PostConstruct
	private void init() {
		alimentoBO = new AlimentoBO();
	}

	public AlimentoBO getAlimentoBO() {
		return alimentoBO;
	}

	public void setAlimentoBO(AlimentoBO alimentoBO) {
		this.alimentoBO = alimentoBO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
