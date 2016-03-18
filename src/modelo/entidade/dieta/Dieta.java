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

import modelo.entidade.usuario.Nutricionista;
import modelo.entidade.utils.IEntidade;
import modelo.tipo.TipoDiaEnum;
import modelo.tipo.TipoObjetivoEnum;

@Entity
@Table(name = "TB_DIE_DIETA")
@SequenceGenerator(name = "SQ_DIE_ID", sequenceName = "SQ_DIE_ID", allocationSize = 1, initialValue = 1)
public class Dieta implements Serializable, IEntidade {

	private static final long serialVersionUID = 7049713933962986774L;

	@Id
	@Column(name = "DIE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DIE_ID")
	private long id;

	@Column(name = "DIE_DS_NOME", nullable = false)
	private String nome;

	@Column(name = "DIE_DS_OBJETIVO", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private TipoObjetivoEnum objetivo;

	@ManyToOne
	@JoinColumn(name = "NUT_ID")
	private Nutricionista nutricionista;

	@OneToMany(mappedBy = "dieta", targetEntity = RefeicaoPraticante.class, cascade = CascadeType.ALL)
	private List<RefeicaoPraticante> refeicoesPraticante;

	@Transient
	private boolean selecionado;

	public Dieta() {
	}

	public Dieta(String nome, TipoObjetivoEnum objetivo,
			Nutricionista nutricionista,
			List<RefeicaoPraticante> refeicoesPraticante) {
		this.nome = nome;
		this.objetivo = objetivo;
		this.nutricionista = nutricionista;
		this.refeicoesPraticante = refeicoesPraticante;
	}

	@Override
	public long getId() {
		return id;
	}

	public String valorCalorias(TipoDiaEnum dia) {
		int valor = 0;
		for (RefeicaoPraticante refeicaoPraticante : refeicoesPraticante) {
			if (dia == refeicaoPraticante.getDia()) {
				for (AlimentoPraticante alimentoPraticante : refeicaoPraticante
						.getAlimentosPraticante()) {
					valor = valor + alimentoPraticante.valorCaloria();
				}
			}

		}
		return "" + valor;
	}

	public String valorProteinas(TipoDiaEnum dia) {
		int valor = 0;
		for (RefeicaoPraticante refeicaoPraticante : refeicoesPraticante) {
			if (dia == refeicaoPraticante.getDia()) {
				for (AlimentoPraticante alimentoPraticante : refeicaoPraticante
						.getAlimentosPraticante()) {
					valor = valor + alimentoPraticante.valorProteina();
				}
			}
		}
		return "" + valor;
	}

	public String valorGorduras(TipoDiaEnum dia) {
		int valor = 0;
		for (RefeicaoPraticante refeicaoPraticante : refeicoesPraticante) {
			if (dia == refeicaoPraticante.getDia()) {
				for (AlimentoPraticante alimentoPraticante : refeicaoPraticante
						.getAlimentosPraticante()) {
					valor = valor + alimentoPraticante.valorGordura();
				}
			}
		}
		return "" + valor;
	}

	public String valorCarboidratos(TipoDiaEnum dia) {
		int valor = 0;
		for (RefeicaoPraticante refeicaoPraticante : refeicoesPraticante) {
			if (dia == refeicaoPraticante.getDia()) {
				for (AlimentoPraticante alimentoPraticante : refeicaoPraticante
						.getAlimentosPraticante()) {
					valor = valor + alimentoPraticante.valorCarboidrato();
				}
			}
		}
		return "" + valor;
	}

	public String valorFibras(TipoDiaEnum dia) {
		int valor = 0;
		for (RefeicaoPraticante refeicaoPraticante : refeicoesPraticante) {
			if (dia == refeicaoPraticante.getDia()) {
				for (AlimentoPraticante alimentoPraticante : refeicaoPraticante
						.getAlimentosPraticante()) {
					valor = valor + alimentoPraticante.valorFibra();
				}
			}
		}
		return "" + valor;
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

	public TipoObjetivoEnum getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(TipoObjetivoEnum objetivo) {
		this.objetivo = objetivo;
	}

	public Nutricionista getNutricionista() {
		return nutricionista;
	}

	public void setNutricionista(Nutricionista nutricionista) {
		this.nutricionista = nutricionista;
	}

	public List<RefeicaoPraticante> getRefeicoesPraticante() {
		return refeicoesPraticante;
	}

	public void setRefeicoesPraticante(
			List<RefeicaoPraticante> refeicoesPraticante) {
		this.refeicoesPraticante = refeicoesPraticante;
	}

	@Override
	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

}
