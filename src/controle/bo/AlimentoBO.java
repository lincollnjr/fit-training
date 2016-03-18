package controle.bo;

import java.util.ArrayList;
import java.util.List;

import modelo.entidade.dieta.Alimento;
import modelo.entidade.usuario.Usuario;
import modelo.tipo.TipoOperacaoEnum;
import modelo.tipo.TipoPorcaoEnum;
import controle.service.dieta.AlimentoService;
import controle.util.MensagemUtil;

public class AlimentoBO extends MensagemUtil{

	private static final long serialVersionUID = -2851293274666356963L;

	private String argumentoAlimentoNome;

	private List<Alimento> alimentos;
	private List<TipoPorcaoEnum> tiposPorcoes;

	private Alimento alimentoSelecionado;

	private TipoOperacaoEnum operacaoAlimento;

	private final String LISTAR_ALIMENTOS = "listarAlimentos";
	private final String FORM_ALIMENTO = "formAlimento";

	private AlimentoService alimentoService;

	private Usuario usuario;

	public AlimentoBO() {
		alimentoService = new AlimentoService();
		tiposPorcoes = TipoPorcaoEnum.getLista();
	}

	public String iniciar(Usuario usuario) {
		this.usuario = usuario;
		return consultarAlimentos();
	}
	
	public String consultarAlimentos() {
		alimentos = alimentoService.listar();
		desabilitarMensagem();
		return LISTAR_ALIMENTOS;
	}

	public String consultarAlimentoNome() {
		if (argumentoAlimentoNome.equals("")) {
			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;
		}
		alimentos = alimentoService.buscarPorNome(argumentoAlimentoNome);

		desabilitarMensagem();
		return LISTAR_ALIMENTOS;
	}

	public String incluirAlimento() {
		operacaoAlimento = TipoOperacaoEnum.INCLUIR;
		alimentoSelecionado = new Alimento();
		desabilitarMensagem();
		return FORM_ALIMENTO;
	}

	public Alimento obterAlimentoSelecionado() {
		if (alimentos == null)
			alimentos = new ArrayList<Alimento>();
		for (Alimento alimento : alimentos) {
			if (alimento.isSelecionado()) {
				return alimento;
			}
		}
		return null;
	}

	public String editarAlimento() {

		alimentoSelecionado = obterAlimentoSelecionado();
		if (alimentoSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}
		operacaoAlimento = TipoOperacaoEnum.EDITAR;
		desabilitarMensagem();
		return FORM_ALIMENTO;
	}

	public String excluirAlimento() {

		alimentoSelecionado = obterAlimentoSelecionado();
		if (alimentoSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}
		alimentoService.excluir(alimentoSelecionado);
		adicionarMensagemInfo(EXCLUIDO_SUCESSO);
		return consultarAlimentos();
	}

	public String voltarListarAlimentos() {
		desabilitarMensagem();
		return HOME;
	}

	public String salvarAlimento() {

		if (alimentoSelecionado.getNome().equals("")
				|| alimentoSelecionado.getQtdCaloria() < 0
				|| alimentoSelecionado.getQtdCarboidrato() < 0
				|| alimentoSelecionado.getQtdFibra() < 0
				|| alimentoSelecionado.getQtdProteina() < 0
				|| alimentoSelecionado.getQtdGordura() < 0) {

			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;
		}

		alimentoService.salvar(alimentoSelecionado);
		adicionarMensagemInfo(SALVO_SUCESSO);
		return consultarAlimentos();
	}

	public String voltarFormAlimento() {
		desabilitarMensagem();
		return consultarAlimentos();
	}

	public String getArgumentoAlimentoNome() {
		return argumentoAlimentoNome;
	}

	public void setArgumentoAlimentoNome(String argumentoAlimentoNome) {
		this.argumentoAlimentoNome = argumentoAlimentoNome;
	}

	public List<Alimento> getAlimentos() {
		return alimentos;
	}

	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}

	public List<TipoPorcaoEnum> getTiposPorcoes() {
		return tiposPorcoes;
	}

	public void setTiposPorcoes(List<TipoPorcaoEnum> tiposPorcoes) {
		this.tiposPorcoes = tiposPorcoes;
	}

	public Alimento getAlimentoSelecionado() {
		return alimentoSelecionado;
	}

	public void setAlimentoSelecionado(Alimento alimentoSelecionado) {
		this.alimentoSelecionado = alimentoSelecionado;
	}

	public TipoOperacaoEnum getOperacaoAlimento() {
		return operacaoAlimento;
	}

	public void setOperacaoAlimento(TipoOperacaoEnum operacaoAlimento) {
		this.operacaoAlimento = operacaoAlimento;
	}

	public AlimentoService getAlimentoService() {
		return alimentoService;
	}

	public void setAlimentoService(AlimentoService alimentoService) {
		this.alimentoService = alimentoService;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLISTAR_ALIMENTOS() {
		return LISTAR_ALIMENTOS;
	}

	public String getFORM_ALIMENTO() {
		return FORM_ALIMENTO;
	}

}
