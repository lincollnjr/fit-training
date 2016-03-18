package modelo.tipo;

import java.util.ArrayList;
import java.util.List;



public enum TipoDiaEnum{

	A("A","Dia A"),
	B("B","Dia B"),
	C("C","Dia C"),
	D("D","Dia D"),
	E("E","Dia E"),
	F("F","Dia F"),
	G("G","Dia G");
	
	private final static String VALORES_SIGLA = "ABCDEFG";

	private final String codigo;
	private final String descricao;

	private TipoDiaEnum(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getSigla() {
		return codigo.toString();
	}

	public static List<TipoDiaEnum> getLista(){
		List<TipoDiaEnum> lista = new ArrayList<>();
		lista.add(A);
		lista.add(B);
		lista.add(C);
		lista.add(D);
		lista.add(E);
		lista.add(F);
		lista.add(G);
		return lista;
	}
	
	public static TipoDiaEnum valorPorCodigo(String codigo) {

		if (codigo == null) {
			throw new IllegalArgumentException("Nao existe um "+ TipoDiaEnum.class +" para o valor informado");
		}

		int indice = VALORES_SIGLA.indexOf(codigo.toString());

		if (indice < 0) {
			throw new IllegalArgumentException("Nao existe um Enum para o valor informado");
		}

		return TipoDiaEnum.values()[indice];
	}

	
	
	public String getDescricao() {
		return descricao;
	}

	public String getCodigo() {
		return codigo;
	}

}
