package modelo.entidade.treino;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import modelo.entidade.utils.IEntidade;
import modelo.tipo.TipoDiaEnum;

@Entity
@Table(name = "TB_EXP_EXERCICIO_PRATICANTE")
@SequenceGenerator(name = "SQ_EXP_ID", sequenceName = "SQ_EXP_ID", allocationSize = 1, initialValue = 1)
public class ExercicioPraticante implements Serializable, IEntidade{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7529713033962186774L;
	
	@Id
	@Column(name = "EXP_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_EXP_ID")	
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "EXE_ID")
	private Exercicio exercicio;
	
	@Column(name = "EXP_VL_QTD_REPETICOES", nullable = false)
	private int qtdRepeticoes;
	
	@Column(name = "EXP_VL_QTD_SERIES", nullable = false)
	private int qtdSeries;
	
	@Column(name = "EXP_TP_DIA", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private TipoDiaEnum dia;	
	
	@ManyToOne
	@JoinColumn(name = "TRE_ID")
	private Treino treino;
	
	@Transient
	private boolean selecionado;

	public ExercicioPraticante() {
	}

	public ExercicioPraticante(Exercicio exercicio, int qtdRepeticoes,
			int qtdSeries, TipoDiaEnum dia) {
		this.exercicio = exercicio;
		this.qtdRepeticoes = qtdRepeticoes;
		this.qtdSeries = qtdSeries;
		this.dia = dia;
	}

	public Treino getTreino() {
		return treino;
	}

	public void setTreino(Treino treino) {
		this.treino = treino;
	}

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public int getQtdRepeticoes() {
		return qtdRepeticoes;
	}

	public void setQtdRepeticoes(int qtdRepeticoes) {
		this.qtdRepeticoes = qtdRepeticoes;
	}

	public int getQtdSeries() {
		return qtdSeries;
	}

	public void setQtdSeries(int qtdSeries) {
		this.qtdSeries = qtdSeries;
	}

	public TipoDiaEnum getDia() {
		return dia;
	}

	public void setDia(TipoDiaEnum dia) {
		this.dia = dia;
	}

	@Override
	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

}
