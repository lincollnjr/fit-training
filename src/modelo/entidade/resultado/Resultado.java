package modelo.entidade.resultado;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import modelo.entidade.usuario.Praticante;
import modelo.entidade.utils.IEntidade;

@Entity
@Table(name = "TB_RES_RESULTADO")
@SequenceGenerator(name = "SQ_RES_ID", sequenceName = "SQ_RES_ID", allocationSize = 1, initialValue = 1)
public class Resultado implements Serializable, IEntidade{

	private static final long serialVersionUID = 7529713913962186774L;

	@Id
	@Column(name = "RES_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RES_ID")
	private long id;
	
	@Column(name = "RES_VL_PESO", nullable = false)
	private float peso;
	
	@Column(name = "RES_VL_ALTURA", nullable = false)
	private float altura;
	
	@Column(name = "RES_VL_PER_GORDURA", nullable = false)
	private float percentualGordura;
	
	@Column(name = "RES_DT_DATA", nullable = false)
	@Temporal(value = TemporalType.DATE)
	private Date data;
	
	@ManyToOne
	@JoinColumn(name = "PRA_ID")
	private Praticante praticante;
	
	@Transient
	private boolean selecionado;

	public Resultado(){};
	
	public Resultado(float peso, float altura, float percentualGordura,
			Date data, Praticante praticante) {
		this.peso = peso;
		this.altura = altura;
		this.percentualGordura = percentualGordura;
		this.data = data;
		this.praticante = praticante;
	}

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getPercentualGordura() {
		return percentualGordura;
	}

	public void setPercentualGordura(float percentualGordura) {
		this.percentualGordura = percentualGordura;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Praticante getPraticante() {
		return praticante;
	}

	public void setPraticante(Praticante praticante) {
		this.praticante = praticante;
	}

	@Override
	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

}
