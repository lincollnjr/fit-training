package modelo.tipo;



public enum TipoContaEnum{

	PRATICANTE("A","Praticante"),
	NUTRICIONISTA("B","Nutricionista"),
	PERSONAL("C","Personal");
	
	private final static String VALORES_SIGLA = "ABC";

	private final String codigo;
	private final String descricao;

	private TipoContaEnum(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getSigla() {
		return codigo.toString();
	}

	public static TipoContaEnum valorPorCodigo(String codigo) {

		if (codigo == null) {
			throw new IllegalArgumentException("Nao existe um "+ TipoContaEnum.class +" para o valor informado");
		}

		int indice = VALORES_SIGLA.indexOf(codigo.toString());

		if (indice < 0) {
			throw new IllegalArgumentException("Nao existe um Enum para o valor informado");
		}

		return TipoContaEnum.values()[indice];
	}

	
	
	public String getDescricao() {
		return descricao;
	}

	public String getCodigo() {
		return codigo;
	}

}
