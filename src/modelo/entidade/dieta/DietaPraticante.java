package modelo.entidade.dieta;

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
@Table(name = "TB_DIP_DIETA_PRATICANTE")
@SequenceGenerator(name = "SQ_DIP_ID", sequenceName = "SQ_DIP_ID", allocationSize = 1, initialValue = 1)
public class DietaPraticante implements Serializable, IEntidade{

	private static final long serialVersionUID = 7549213933962986774L;

	@Id
	@Column(name = "DIP_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DIP_ID")	
	private long id;
	
	@Column(name = "DIP_DT_DATA_INICIO", nullable = false)
	@Temporal(value = TemporalType.DATE)	
	private Date dataInicio;
	
	@Column(name = "DIP_DT_DATA_FIM", nullable = false)
	@Temporal(value = TemporalType.DATE)
	private Date dataFim;
	
	@ManyToOne
	@JoinColumn(name = "PRA_ID")
	private Praticante praticante;
	
	@ManyToOne
	@JoinColumn(name = "DIE_ID")
	private Dieta dieta;
	
	@Transient
	private boolean selecionado;

	public DietaPraticante(){}
	
	public DietaPraticante(Date dataInicio, Date dataFim,
			Praticante praticante, Dieta dieta) {
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.praticante = praticante;
		this.dieta = dieta;
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

	public Praticante getPraticante() {
		return praticante;
	}

	public void setPraticante(Praticante praticante) {
		this.praticante = praticante;
	}

	public Dieta getDieta() {
		return dieta;
	}

	public void setDieta(Dieta dieta) {
		this.dieta = dieta;
	}

	@Override
	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

}
