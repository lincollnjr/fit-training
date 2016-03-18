package modelo.entidade.dieta;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import modelo.entidade.utils.IEntidade;
import modelo.tipo.TipoPorcaoEnum;

@Entity
@Table(name = "TB_ALI_ALIMENTO")
@SequenceGenerator(name = "SQ_ALI_ID", sequenceName = "SQ_ALI_ID", allocationSize = 1, initialValue = 1)
public class Alimento implements Serializable, IEntidade {

	private static final long serialVersionUID = 7549712233962186774L;

	@Id
	@Column(name = "ALI_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ALI_ID")
	private long id;

	@Column(name = "ALI_DS_NOME", nullable = false)
	private String nome;

	@Column(name = "ALI_VL_QTD_CALORIAS", nullable = false)
	private int qtdCaloria;

	@Column(name = "ALI_VL_QTD_PROTEINA", nullable = false)
	private int qtdProteina;

	@Column(name = "ALI_VL_QTD_CARBOIDRATO", nullable = false)
	private int qtdCarboidrato;

	@Column(name = "ALI_VL_QTD_FIBRA", nullable = false)
	private int qtdFibra;

	@Column(name = "ALI_VL_QTD_GORDURA", nullable = false)
	private int qtdGordura;

	@Column(name = "ALI_TP_TIPO_PORCAO_UM")
	@Enumerated(EnumType.ORDINAL)
	private TipoPorcaoEnum tipoPorcaoUm;

	@Column(name = "ALI_VL_PORCAO_UM")
	private int porcaoUm;

	@Column(name = "ALI_TP_TIPO_PORCAO_DOIS")
	@Enumerated(EnumType.ORDINAL)
	private TipoPorcaoEnum tipoPorcaoDois;

	@Column(name = "ALI_VL_PORCAO_DOIS")
	private int porcaoDois;

	@Transient
	private boolean selecionado;

	public Alimento() {
	}

	public Alimento(String nome, int qtdCaloria, int qtdProteina,
			int qtdCarboidrato, int qtdFibra, int qtdGordura) {
		this.nome = nome;
		this.qtdCaloria = qtdCaloria;
		this.qtdProteina = qtdProteina;
		this.qtdCarboidrato = qtdCarboidrato;
		this.qtdFibra = qtdFibra;
		this.qtdGordura = qtdGordura;
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

	public int getQtdCaloria() {
		return qtdCaloria;
	}

	public void setQtdCaloria(int qtdCaloria) {
		this.qtdCaloria = qtdCaloria;
	}

	public int getQtdProteina() {
		return qtdProteina;
	}

	public void setQtdProteina(int qtdProteina) {
		this.qtdProteina = qtdProteina;
	}

	public int getQtdCarboidrato() {
		return qtdCarboidrato;
	}

	public void setQtdCarboidrato(int qtdCarboidrato) {
		this.qtdCarboidrato = qtdCarboidrato;
	}

	public int getQtdFibra() {
		return qtdFibra;
	}

	public void setQtdFibra(int qtdFibra) {
		this.qtdFibra = qtdFibra;
	}

	public int getQtdGordura() {
		return qtdGordura;
	}

	public void setQtdGordura(int qtdGordura) {
		this.qtdGordura = qtdGordura;
	}

	public TipoPorcaoEnum getTipoPorcaoUm() {
		return tipoPorcaoUm;
	}

	public void setTipoPorcaoUm(TipoPorcaoEnum tipoPorcaoUm) {
		this.tipoPorcaoUm = tipoPorcaoUm;
	}

	public int getPorcaoUm() {
		return porcaoUm;
	}

	public void setPorcaoUm(int porcaoUm) {
		this.porcaoUm = porcaoUm;
	}

	public TipoPorcaoEnum getTipoPorcaoDois() {
		return tipoPorcaoDois;
	}

	public void setTipoPorcaoDois(TipoPorcaoEnum tipoPorcaoDois) {
		this.tipoPorcaoDois = tipoPorcaoDois;
	}

	public int getPorcaoDois() {
		return porcaoDois;
	}

	public void setPorcaoDois(int porcaoDois) {
		this.porcaoDois = porcaoDois;
	}

	@Override
	public boolean isSelecionado() {
		return selecionado;
	}

	@Override
	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	

}
