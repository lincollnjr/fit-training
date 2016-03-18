package modelo.entidade.usuario;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import modelo.entidade.dieta.Dieta;
import modelo.entidade.utils.IEntidade;

@Entity
@Table(name = "TB_NUT_NUTRICIONISTA")
@SequenceGenerator(name = "SQ_NUT_ID", sequenceName = "SQ_NUT_ID", allocationSize = 1, initialValue = 1)
public class Nutricionista implements Serializable, IEntidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4727424491685681714L;

	@Id
	@Column(name = "NUT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_NUT_ID")
	private long id;

	@Column(name = "NUT_DS_NOME", nullable = false)
	private String nome;

	@Column(name = "NUT_DS_EMAIL", nullable = false)
	private String email;

	@Column(name = "NUT_DS_SENHA", nullable = false)
	private String senha;

	@Column(name = "NUT_DT_DATA_NASCIMENTO", nullable = false)
	@Temporal(value = TemporalType.DATE)
	private Date dataNascimento;

	@OneToMany(mappedBy = "nutricionista", targetEntity = Praticante.class)
	private List<Praticante> praticantes;

	@OneToMany(mappedBy = "nutricionista", targetEntity = Dieta.class)
	private List<Dieta> dietas;

	@Transient
	private boolean selecionado;

	public Nutricionista() {
	}

	public Nutricionista(String email, String senha, String nome,
			Date dataNascimento) {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
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

	public List<Praticante> getPraticantes() {
		return praticantes;
	}

	public void setPraticantes(List<Praticante> praticantes) {
		this.praticantes = praticantes;
	}

	public List<Dieta> getDietas() {
		return dietas;
	}

	public void setDietas(List<Dieta> dietas) {
		this.dietas = dietas;
	}

	@Override
	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
