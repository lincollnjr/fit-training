package modelo.entidade.treino;

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
@Table(name = "TB_TRP_TREINO_PRATICANTE")
@SequenceGenerator(name = "SQ_TRP_ID", sequenceName = "SQ_TRP_ID", allocationSize = 1, initialValue = 1)
public class TreinoPraticante implements Serializable, IEntidade{

	private static final long serialVersionUID = 7529713933962186774L;
	
	@Id
	@Column(name = "TRP_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TRP_ID")
	private long id;
	
	@Column(name = "TRP_DT_DATA_INICIO", nullable = false)
	@Temporal(value = TemporalType.DATE)
	private Date dataInicio;
	
	@Column(name = "TRP_DT_DATA_FIM", nullable = false)
	@Temporal(value = TemporalType.DATE)
	private Date dataFim;
	
	@ManyToOne
	@JoinColumn(name = "TRE_ID")
	private Treino treino;
	
	@ManyToOne
	@JoinColumn(name = "PRA_ID")
	private Praticante praticante;
	
	@Transient
	private boolean selecionado;

	public TreinoPraticante(){}
	
	public TreinoPraticante(Date dataInicio, Date dataFim,
			Treino treino, Praticante praticante) {
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.treino = treino;
		this.praticante = praticante;
	}

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Treino getTreino() {
		return treino;
	}

	public void setTreino(Treino treino) {
		this.treino = treino;
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