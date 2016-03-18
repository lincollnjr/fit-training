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

import modelo.entidade.treino.Treino;
import modelo.entidade.utils.IEntidade;

@Entity
@Table(name = "TB_PER_PERSONAL")
@SequenceGenerator(name = "SQ_PER_ID", sequenceName = "SQ_PER_ID", allocationSize = 1, initialValue = 1)
public class Personal implements Serializable, IEntidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7102029644379836465L;

	@Id
	@Column(name = "PER_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PER_ID")
	private long id;

	@Column(name = "PER_DS_NOME", nullable = false)
	private String nome;

	@Column(name = "PER_DS_EMAIL", nullable = false)
	private String email;

	@Column(name = "PER_DS_SENHA", nullable = false)
	private String senha;

	@Column(name = "PER_DT_DATA_NASCIMENTO", nullable = false)
	@Temporal(value = TemporalType.DATE)
	private Date dataNascimento;

	@OneToMany(mappedBy = "personal", targetEntity = Praticante.class)
	private List<Praticante> praticantes;

	@OneToMany(mappedBy = "personal", targetEntity = Treino.class)
	private List<Treino> treinos;

	@Transient
	private boolean selecionado;

	public Personal() {
	}

	public Personal(String email, String senha, String nome, Date dataNascimento) {
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

	public List<Treino> getTreinos() {
		return treinos;
	}

	public void setTreinos(List<Treino> treinos) {
		this.treinos = treinos;
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
