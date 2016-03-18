package controle.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controle.bo.PraticanteBO;

@ManagedBean(name = "praticanteBean")
@SessionScoped
public class PraticanteBean implements Serializable {

	private static final long serialVersionUID = 7184162617769699588L;

	private PraticanteBO praticanteBO;

	@PostConstruct
	private void init() {

		praticanteBO = new PraticanteBO();

	}

	public PraticanteBO getPraticanteBO() {
		return praticanteBO;
	}

	public void setPraticanteBO(PraticanteBO praticanteBO) {
		this.praticanteBO = praticanteBO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	

}
