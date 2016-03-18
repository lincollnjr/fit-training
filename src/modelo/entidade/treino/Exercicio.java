package modelo.entidade.treino;

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
import modelo.tipo.TipoGrupoMuscularEnum;

@Entity
@Table(name = "TB_EXE_EXERCICIO")
@SequenceGenerator(name = "SQ_EXE_ID", sequenceName = "SQ_EXE_ID", allocationSize = 1, initialValue = 1)
public class Exercicio implements Serializable, IEntidade{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4331692910631603950L;
	
	@Id
	@Column(name = "EXE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_EXE_ID")
	private long id;
	
	@Column(name = "EXE_DS_NOME", nullable = false)
	private String nome;
	
	@Column(name = "EXE_TP_GRUPO_MUSCULAR", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private TipoGrupoMuscularEnum grupoMuscular;
	
	@Transient
	private boolean selecionado;

	public Exercicio(){}
	
	public Exercicio(String nome, TipoGrupoMuscularEnum tipoGrupoMuscularEnum) {
		this.nome = nome;
		this.grupoMuscular = tipoGrupoMuscularEnum;
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

	public TipoGrupoMuscularEnum getGrupoMuscular() {
		return grupoMuscular;
	}

	public void setGrupoMuscular(TipoGrupoMuscularEnum grupoMuscular) {
		this.grupoMuscular = grupoMuscular;
	}

	@Override
	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

}
