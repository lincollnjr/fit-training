package modelo.tipo;

import java.util.ArrayList;
import java.util.List;



public enum TipoGrupoMuscularEnum{

	COSTAS("A","Costas"),
	BICEPS("B","Bíceps"),
	TRICEPS("C","Tríceps"),
	DELTOIDES("D","Deltoides"),
	QUADRICEPS("E","Quadrceps"),
	ISQUIOTIBIAIS("F","Posterior de coxa"),
	PANTURILHA("G","Panturilha"),
	ABDOMEM("H","Abdomem"),
	PEITO("I","Peitoral");
	
	
	private final static String VALORES_SIGLA = "ABCDEFGHI";

	private final String codigo;
	private final String descricao;

	private TipoGrupoMuscularEnum(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getSigla() {
		return codigo.toString();
	}

	public static TipoGrupoMuscularEnum valorPorCodigo(String codigo) {

		if (codigo == null) {
			throw new IllegalArgumentException("Nao existe um "+ TipoGrupoMuscularEnum.class +" para o valor informado");
		}

		int indice = VALORES_SIGLA.indexOf(codigo.toString());

		if (indice < 0) {
			throw new IllegalArgumentException("Nao existe um Enum para o valor informado");
		}

		return TipoGrupoMuscularEnum.values()[indice];
	}

	
	public static List<TipoGrupoMuscularEnum> getLista(){
		List<TipoGrupoMuscularEnum> lista = new ArrayList<TipoGrupoMuscularEnum>();
		lista.add(ABDOMEM);
		lista.add(BICEPS);
		lista.add(COSTAS);
		lista.add(DELTOIDES);
		lista.add(QUADRICEPS);
		lista.add(ISQUIOTIBIAIS);
		lista.add(TRICEPS);
		lista.add(PEITO);
		return lista;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public String getCodigo() {
		return codigo;
	}

}
