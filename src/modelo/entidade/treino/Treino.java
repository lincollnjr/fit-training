package modelo.entidade.treino;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import modelo.entidade.usuario.Personal;
import modelo.entidade.utils.IEntidade;
import modelo.tipo.TipoObjetivoEnum;

@Entity
@Table(name = "TB_TRE_TREINO")
@SequenceGenerator(name = "SQ_TRE_ID", sequenceName = "SQ_TRE_ID", allocationSize = 1, initialValue = 1)
public class Treino implements Serializable, IEntidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4784324033139580525L;

	@Id
	@Column(name = "TRE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TRE_ID")
	private long id;

	@Column(name = "TRE_DS_NOME", nullable = false)
	private String nome;

	@ManyToOne
	@JoinColumn(name = "PER_ID")
	private Personal personal;

	@Column(name = "TRE_TP_OBJETIVO", nullable =	 false)
	@Enumerated(EnumType.ORDINAL)
	private TipoObjetivoEnum objetivo;

	@OneToMany(mappedBy = "treino", targetEntity = ExercicioPraticante.class, cascade = CascadeType.ALL)
	private List<ExercicioPraticante> exerciciosPraticante;

	@Transient
	private boolean selecionado;

	public Treino() {
	}

	public Treino(String nome, TipoObjetivoEnum objetivo, Personal personal,
			List<ExercicioPraticante> exerciciosPraticante) {
		this.nome = nome;
		this.objetivo = objetivo;
		this.personal = personal;
	}

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public TipoObjetivoEnum getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(TipoObjetivoEnum objetivo) {
		this.objetivo = objetivo;
	}

	@Override
	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public List<ExercicioPraticante> getExerciciosPraticante() {
		return exerciciosPraticante;
	}

	public void setExerciciosPraticante(
			List<ExercicioPraticante> exerciciosPraticante) {
		this.exerciciosPraticante = exerciciosPraticante;
	}

}
