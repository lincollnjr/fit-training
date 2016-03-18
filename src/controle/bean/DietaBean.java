package controle.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controle.bo.DietaBO;

@ManagedBean(name = "dietaBean")
@SessionScoped
public class DietaBean implements Serializable {

	private static final long serialVersionUID = -2852893274666356963L;

	private DietaBO dietaBO;

	@PostConstruct
	private void init() {

		dietaBO = new DietaBO();

	}

	public DietaBO getDietaBO() {
		return dietaBO;
	}

	public void setDietaBO(DietaBO dietaBO) {
		this.dietaBO = dietaBO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
