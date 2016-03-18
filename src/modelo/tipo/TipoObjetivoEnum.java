package modelo.tipo;

import java.util.ArrayList;
import java.util.List;



public enum TipoObjetivoEnum{

	HIPERTROFIA("A","Hipertrofia"),
	PERDADEPESO("B","Perda de peso");
	
	private final static String VALORES_SIGLA = "AB";

	private final String codigo;
	private final String descricao;

	private TipoObjetivoEnum(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getSigla() {
		return codigo.toString();
	}

	public static List<TipoObjetivoEnum> getLista(){
		List<TipoObjetivoEnum> lista = new ArrayList<>();
		lista.add(HIPERTROFIA);
		lista.add(PERDADEPESO);

		return lista;
	}
	
	public static TipoObjetivoEnum valorPorCodigo(String codigo) {

		if (codigo == null) {
			throw new IllegalArgumentException("Nao existe um "+ TipoObjetivoEnum.class +" para o valor informado");
		}

		int indice = VALORES_SIGLA.indexOf(codigo.toString());

		if (indice < 0) {
			throw new IllegalArgumentException("Nao existe um Enum para o valor informado");
		}

		return TipoObjetivoEnum.values()[indice];
	}

	
	
	public String getDescricao() {
		return descricao;
	}

	public String getCodigo() {
		return codigo;
	}

}
