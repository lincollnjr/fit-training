package modelo.entidade.dieta;

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

import modelo.entidade.utils.IEntidade;
import modelo.tipo.TipoDiaEnum;
import modelo.tipo.TipoRefeicaoEnum;


@Entity
@Table(name = "TB_REP_REFEICAO_PRATICANTE")
@SequenceGenerator(name = "SQ_REP_ID", sequenceName = "SQ_REP_ID", allocationSize = 1, initialValue = 1)
public class RefeicaoPraticante implements Serializable, IEntidade{

	private static final long serialVersionUID = 7049713933962986774L;

	@Id
	@Column(name = "REP_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_REP_ID")
	private long id;
	
	@Column(name = "REP_TP_TIPO_REFEICAO", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private TipoRefeicaoEnum tipoRefeicao;
	
	@Column(name = "ALP_TP_DIA", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private TipoDiaEnum dia;
	
	@Column(name = "REP_DS_HORARIO", nullable = false)
	private String horario;
	
	@OneToMany(mappedBy = "refeicaoPraticante", targetEntity = AlimentoPraticante.class, cascade=CascadeType.ALL)
	private List<AlimentoPraticante> alimentosPraticante;
	
	@ManyToOne
	@JoinColumn(name = "DIE_ID")
	private Dieta dieta;
	
	@Transient
	private boolean selecionado;

	public RefeicaoPraticante() {
	}

	
	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TipoRefeicaoEnum getTipoRefeicao() {
		return tipoRefeicao;
	}

	public void setTipoRefeicao(TipoRefeicaoEnum tipoRefeicao) {
		this.tipoRefeicao = tipoRefeicao;
	}

	public TipoDiaEnum getDia() {
		return dia;
	}

	public void setDia(TipoDiaEnum dia) {
		this.dia = dia;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public List<AlimentoPraticante> getAlimentosPraticante() {
		return alimentosPraticante;
	}

	public void setAlimentosPraticante(
			List<AlimentoPraticante> alimentosPraticante) {
		this.alimentosPraticante = alimentosPraticante;
	}

	@Override
	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public Dieta getDieta() {
		return dieta;
	}

	public void setDieta(Dieta dieta) {
		this.dieta = dieta;
	}

}
