package modelo.entidade.usuario;

import modelo.tipo.TipoContaEnum;

public class Usuario {

	private Praticante praticante;
	private Personal personal;
	private Nutricionista nutricionista;
	private TipoContaEnum contaLogada;

	public Praticante getPraticante() {
		return praticante;
	}

	public void setPraticante(Praticante praticante) {
		this.praticante = praticante;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public Nutricionista getNutricionista() {
		return nutricionista;
	}

	public void setNutricionista(Nutricionista nutricionista) {
		this.nutricionista = nutricionista;
	}

	public TipoContaEnum getContaLogada() {
		return contaLogada;
	}

	public void setContaLogada(TipoContaEnum contaLogada) {
		this.contaLogada = contaLogada;
	}

}
