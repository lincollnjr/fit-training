package modelo.entidade.dieta;

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
import modelo.tipo.TipoPorcaoEnum;

@Entity
@Table(name = "TB_ALP_ALIMENTO_PRATICANTE")
@SequenceGenerator(name = "SQ_ALP_ID", sequenceName = "SQ_ALP_ID", allocationSize = 1, initialValue = 1)
public class AlimentoPraticante implements Serializable, IEntidade{

	private static final long serialVersionUID = 7549720933962986774L;

	@Id
	@Column(name = "ALP_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ALP_ID")
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "ALI_ID")
	private Alimento alimento;
	
	@Column(name = "ALP_VL_PORCAO", nullable = false)
	private int porcao;
	
	@Column(name = "ALP_TP_TIPO_PORCAO", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private TipoPorcaoEnum tipoPorcao;
	
	@ManyToOne
	@JoinColumn(name = "REP_ID")
	private RefeicaoPraticante refeicaoPraticante;
	
	@Transient
	private boolean selecionado;

	
	public int valorCaloria(){
		if(alimento.getTipoPorcaoUm() == tipoPorcao){
			return alimento.getQtdCaloria()*porcao/alimento.getPorcaoUm();
		}
		if(alimento.getTipoPorcaoDois() == tipoPorcao){
			return alimento.getQtdCaloria()*porcao/alimento.getPorcaoDois();
		}
		return 0;
	}
	
	public int valorProteina(){
		if(alimento.getTipoPorcaoUm() == tipoPorcao){
			return alimento.getQtdProteina()*porcao/alimento.getPorcaoUm();
		}
		if(alimento.getTipoPorcaoDois() == tipoPorcao){
			return alimento.getQtdProteina()*porcao/alimento.getPorcaoDois();
		}
		return 0;
	}
	
	public int valorCarboidrato(){
		if(alimento.getTipoPorcaoUm() == tipoPorcao){
			return alimento.getQtdCarboidrato()*porcao/alimento.getPorcaoUm();
		}
		if(alimento.getTipoPorcaoDois() == tipoPorcao){
			return alimento.getQtdCarboidrato()*porcao/alimento.getPorcaoDois();
		}
		return 0;
	}
	
	public int valorFibra(){
		if(alimento.getTipoPorcaoUm() == tipoPorcao){
			return alimento.getQtdFibra()*porcao/alimento.getPorcaoUm();
		}
		if(alimento.getTipoPorcaoDois() == tipoPorcao){
			return alimento.getQtdFibra()*porcao/alimento.getPorcaoDois();
		}
		return 0;
	}
	
	public int valorGordura(){
		if(alimento.getTipoPorcaoUm() == tipoPorcao){
			return alimento.getQtdGordura()*porcao/alimento.getPorcaoUm();
		}
		if(alimento.getTipoPorcaoDois() == tipoPorcao){
			return alimento.getQtdGordura()*porcao/alimento.getPorcaoDois();
		}
		return 0;
	}
	
	public AlimentoPraticante(){}
	
	public AlimentoPraticante(Alimento alimento, int porcao,
			TipoPorcaoEnum tipoPorcao) {
		this.alimento = alimento;
		this.porcao = porcao;
		this.tipoPorcao = tipoPorcao;
	}

	public RefeicaoPraticante getRefeicaoPraticante() {
		return refeicaoPraticante;
	}

	public void setRefeicaoPraticante(RefeicaoPraticante refeicaoPraticante) {
		this.refeicaoPraticante = refeicaoPraticante;
	}

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public int getPorcao() {
		return porcao;
	}

	public void setPorcao(int porcao) {
		this.porcao = porcao;
	}

	public TipoPorcaoEnum getTipoPorcao() {
		return tipoPorcao;
	}

	public void setTipoPorcao(TipoPorcaoEnum tipoPorcao) {
		this.tipoPorcao = tipoPorcao;
	}

	@Override
	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

}
