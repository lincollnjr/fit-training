package modelo.tipo;



public enum TipoOperacaoEnum{

	LISTAR("A","Listar"),
	INCLUIR("B","Incluir"),
	EDITAR("C","Editar"),
	EXCLUIR("D","Excluir"),
	VISUALIZAR("E","Visualizar");
	
	private final static String VALORES_SIGLA = "ABCDE";

	private final String codigo;
	private final String descricao;

	private TipoOperacaoEnum(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getSigla() {
		return codigo.toString();
	}

	public static TipoOperacaoEnum valorPorCodigo(String codigo) {

		if (codigo == null) {
			throw new IllegalArgumentException("Nao existe um "+ TipoOperacaoEnum.class +" para o valor informado");
		}

		int indice = VALORES_SIGLA.indexOf(codigo.toString());

		if (indice < 0) {
			throw new IllegalArgumentException("Nao existe um Enum para o valor informado");
		}

		return TipoOperacaoEnum.values()[indice];
	}

	
	
	public String getDescricao() {
		return descricao;
	}

	public String getCodigo() {
		return codigo;
	}

}
