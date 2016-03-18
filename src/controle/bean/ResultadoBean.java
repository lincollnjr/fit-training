package controle.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controle.bo.ResultadoBO;
import controle.util.MensagemUtil;

@ManagedBean(name = "resultadoBean")
@SessionScoped
public class ResultadoBean extends MensagemUtil implements Serializable {

	private static final long serialVersionUID = -7564610117168903443L;

	private ResultadoBO resultadoBO;

	@PostConstruct
	private void init() {
		resultadoBO = new ResultadoBO();

	}

	public ResultadoBO getResultadoBO() {
		return resultadoBO;
	}

	public void setResultadoBO(ResultadoBO resultadoBO) {
		this.resultadoBO = resultadoBO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
