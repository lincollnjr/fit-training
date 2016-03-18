package controle.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controle.bo.TreinoBO;

@ManagedBean(name = "treinoBean")
@SessionScoped
public class TreinoBean  implements Serializable {

	private static final long serialVersionUID = 6478438242865190780L;

	private TreinoBO treinoBO;

	@PostConstruct
	private void init() {

		treinoBO = new TreinoBO();

	}

	public TreinoBO getTreinoBO() {
		return treinoBO;
	}

	public void setTreinoBO(TreinoBO treinoBO) {
		this.treinoBO = treinoBO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
