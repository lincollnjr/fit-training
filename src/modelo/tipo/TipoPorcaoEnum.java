package modelo.tipo;

import java.util.ArrayList;
import java.util.List;



public enum TipoPorcaoEnum{

	GRAMAS("A","Gramas"),
	LITROS("B","Litros"),
	COLHERES("C","Colheres");
	
	private final static String VALORES_SIGLA = "ABC";

	private final String codigo;
	private final String descricao;

	private TipoPorcaoEnum(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getSigla() {
		return codigo.toString();
	}

	public static List<TipoPorcaoEnum> getLista(){
		List<TipoPorcaoEnum> lista = new ArrayList<>();
		lista.add(GRAMAS);
		lista.add(LITROS);
		lista.add(COLHERES);
		return lista;
	}
	
	public static TipoPorcaoEnum valorPorCodigo(String codigo) {

		if (codigo == null) {
			throw new IllegalArgumentException("Nao existe um "+ TipoPorcaoEnum.class +" para o valor informado");
		}

		int indice = VALORES_SIGLA.indexOf(codigo.toString());

		if (indice < 0) {
			throw new IllegalArgumentException("Nao existe um Enum para o valor informado");
		}

		return TipoPorcaoEnum.values()[indice];
	}

	
	
	public String getDescricao() {
		return descricao;
	}

	public String getCodigo() {
		return codigo;
	}

}
