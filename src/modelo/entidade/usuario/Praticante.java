package modelo.entidade.usuario;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import modelo.entidade.dieta.DietaPraticante;
import modelo.entidade.resultado.Resultado;
import modelo.entidade.treino.TreinoPraticante;
import modelo.entidade.utils.IEntidade;
import modelo.tipo.TipoObjetivoEnum;

@Entity
@Table(name = "TB_PRA_PRATICANTE")
@SequenceGenerator(name = "SQ_PRA_ID", sequenceName = "SQ_PRA_ID", allocationSize = 1, initialValue = 1)
public class Praticante implements Serializable, IEntidade{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PRA_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRA_ID")
	private long id;

	
	@Column(name = "PRA_DS_NOME", nullable = false)
	private String nome;

	@Column(name = "PRA_DS_EMAIL", nullable = false)
	private String email;

	@Column(name = "PRA_DS_SENHA", nullable = false)
	private String senha;

	@Column(name = "PRA_DT_DATA_NASCIMENTO", nullable = false)
	@Temporal(value = TemporalType.DATE)
	private Date dataNascimento;

	@Column(name = "PRA_TP_OBJETIVO_ATUAL", nullable = true)
	@Enumerated(EnumType.ORDINAL)
	private TipoObjetivoEnum objetivoAtual;

	@ManyToOne
	@JoinColumn(name = "PER_ID")
	private Personal personal;

	@ManyToOne
	@JoinColumn(name = "NUT_ID")
	private Nutricionista nutricionista;

	@OneToMany(mappedBy = "praticante", targetEntity = DietaPraticante.class)
	private List<DietaPraticante> dietas;

	@OneToMany(mappedBy = "praticante", targetEntity = TreinoPraticante.class)
	private List<TreinoPraticante> treinos;

	@OneToMany(mappedBy = "praticante", targetEntity = Resultado.class)
	private List<Resultado> resultados;

	@Transient
	private boolean selecionado;

	
	public Praticante(){};
	
	public Praticante(String email, String senha, String nome,
			Date dataNascimento) {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}
	
	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	@Override
	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public TipoObjetivoEnum getObjetivoAtual() {
		return objetivoAtual;
	}

	public void setObjetivoAtual(TipoObjetivoEnum objetivoAtual) {
		this.objetivoAtual = objetivoAtual;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Nutricionista getNutricionista() {
		return nutricionista;
	}

	public void setNutricionista(Nutricionista nutricionista) {
		this.nutricionista = nutricionista;
	}

	public List<DietaPraticante> getDietas() {
		return dietas;
	}

	public void setDietas(List<DietaPraticante> dietas) {
		this.dietas = dietas;
	}

	public List<TreinoPraticante> getTreinos() {
		return treinos;
	}

	public void setTreinos(List<TreinoPraticante> treinos) {
		this.treinos = treinos;
	}

	public List<Resultado> getResultados() {
		return resultados;
	}

	public void setResultados(List<Resultado> resultados) {
		this.resultados = resultados;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
