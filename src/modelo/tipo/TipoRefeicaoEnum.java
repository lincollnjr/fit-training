package modelo.tipo;

import java.util.ArrayList;
import java.util.List;



public enum TipoRefeicaoEnum{

	CAFEMANHA("A","Café da manha"),
	CAFETARDE("B","Café da tarde"),
	DESJEJUM("C","Desjejum"),
	ALMOCO("D","Almoço"),
	JANTA("E","Janta"),
	CEIA("F","Ceia");
	
	private final static String VALORES_SIGLA = "ABCDEF";

	private final String codigo;
	private final String descricao;

	private TipoRefeicaoEnum(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getSigla() {
		return codigo.toString();
	}

	public static List<TipoRefeicaoEnum> getLista(){
		List<TipoRefeicaoEnum> lista = new ArrayList<>();
		lista.add(CAFEMANHA);
		lista.add(CAFETARDE);
		lista.add(DESJEJUM);
		lista.add(ALMOCO);
		lista.add(JANTA);
		lista.add(CEIA);
		return lista;
	}
	
	public static TipoRefeicaoEnum valorPorCodigo(String codigo) {

		if (codigo == null) {
			throw new IllegalArgumentException("Nao existe um "+ TipoRefeicaoEnum.class +" para o valor informado");
		}

		int indice = VALORES_SIGLA.indexOf(codigo.toString());

		if (indice < 0) {
			throw new IllegalArgumentException("Nao existe um Enum para o valor informado");
		}

		return TipoRefeicaoEnum.values()[indice];
	}

	
	
	public String getDescricao() {
		return descricao;
	}

	public String getCodigo() {
		return codigo;
	}

}
