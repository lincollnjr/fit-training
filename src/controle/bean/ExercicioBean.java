package controle.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controle.bo.ExercicioBO;
import controle.util.MensagemUtil;

@ManagedBean(name = "exercicioBean")
@SessionScoped
public class ExercicioBean extends MensagemUtil implements Serializable {

	private static final long serialVersionUID = -2851893274666356963L;

	private ExercicioBO exercicioBO;

	@PostConstruct
	private void init() {

		exercicioBO = new ExercicioBO();

	}

	public ExercicioBO getExercicioBO() {
		return exercicioBO;
	}

	public void setExercicioBO(ExercicioBO exercicioBO) {
		this.exercicioBO = exercicioBO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
