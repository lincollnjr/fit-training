package controle.bo;

import java.util.ArrayList;
import java.util.List;

import modelo.entidade.dieta.Alimento;
import modelo.entidade.dieta.AlimentoPraticante;
import modelo.entidade.dieta.Dieta;
import modelo.entidade.dieta.DietaPraticante;
import modelo.entidade.dieta.RefeicaoPraticante;
import modelo.entidade.usuario.Usuario;
import modelo.tipo.TipoContaEnum;
import modelo.tipo.TipoDiaEnum;
import modelo.tipo.TipoObjetivoEnum;
import modelo.tipo.TipoOperacaoEnum;
import modelo.tipo.TipoPorcaoEnum;
import modelo.tipo.TipoRefeicaoEnum;
import controle.service.dieta.AlimentoPraticanteService;
import controle.service.dieta.AlimentoService;
import controle.service.dieta.DietaPraticanteService;
import controle.service.dieta.DietaService;
import controle.service.dieta.RefeicaoPraticanteService;
import controle.util.MensagemUtil;

public class DietaBO extends MensagemUtil {

	private String argumentoDietaNome;
	private TipoDiaEnum argumentoRefeicaoPraticanteDia;
	private String argumentoAlimentoNome;

	private String horaAux;
	private String minutosAux;

	private List<Dieta> dietas;
	private List<DietaPraticante> dietasPraticante;
	private List<RefeicaoPraticante> refeicoesPraticante;
	private List<AlimentoPraticante> alimentosPraticante;
	private List<Alimento> alimentos;

	private Dieta dietaSelecionado;
	private DietaPraticante dietaPraticanteSelecionado;
	private RefeicaoPraticante refeicaoPraticanteSelecionado;
	private AlimentoPraticante alimentoPraticanteSelecionado;
	private Alimento alimentoSelecionado;

	private List<TipoDiaEnum> diasRefeicaoPraticante;
	private List<TipoDiaEnum> dias;
	private List<TipoRefeicaoEnum> tiposRefeicao;
	private List<TipoObjetivoEnum> objetivos;
	private List<TipoPorcaoEnum> tiposPorcao;

	private TipoOperacaoEnum operacaoRefeicaoPraticante;
	private TipoOperacaoEnum operacaoDieta;
	private TipoOperacaoEnum operacaoAlimentoPraticante;

	private Usuario usuario;

	private DietaService dietaService;
	private AlimentoPraticanteService alimentoPraticanteService;
	private DietaPraticanteService dietaPraticanteService;
	private RefeicaoPraticanteService refeicaoPraticanteService;
	private AlimentoService alimentoService;

	private final String LISTAR_DIETAS = "listarDietas";
	private final String LISTAR_REFEICOES_PRATICANTE = "listarRefeicoesPraticante";
	private final String LISTAR_ALIMENTOS_PRATICANTE = "listarAlimentosPraticante";
	private final String FORM_DIETA = "formDieta";
	private final String FORM_REFEICAO_PRATICANTE = "formRefeicaoPraticante";
	private final String FORM_ALIMENTO_PRATICANTE = "formAlimentoPraticante";

	public DietaBO() {
		tiposPorcao = TipoPorcaoEnum.getLista();

		dias = TipoDiaEnum.getLista();

		objetivos = TipoObjetivoEnum.getLista();

		tiposRefeicao = TipoRefeicaoEnum.getLista();

		dietaPraticanteService = new DietaPraticanteService();
		dietaService = new DietaService();
		refeicaoPraticanteService = new RefeicaoPraticanteService();
		alimentoPraticanteService = new AlimentoPraticanteService();
		alimentoService = new AlimentoService();

		dietas = new ArrayList<Dieta>();
		dietasPraticante = new ArrayList<DietaPraticante>();
		refeicoesPraticante = new ArrayList<RefeicaoPraticante>();
		alimentosPraticante = new ArrayList<AlimentoPraticante>();
		alimentos = new ArrayList<Alimento>();

		argumentoRefeicaoPraticanteDia = TipoDiaEnum.A;
	}

	public String iniciar(Usuario usuario){
		this.usuario = usuario;
		return consultarDietas();
	}
	
	public String consultarDietas() {

		if (usuario.getContaLogada() == TipoContaEnum.PRATICANTE) {
			consultarDietasPraticante();
		} else {
			consultarDietasNutricionista();
		}
		desabilitarMensagem();
		return LISTAR_DIETAS;

	};

	public void consultarDietasPraticante() {

		dietasPraticante = dietaPraticanteService.buscarPorPraticante(usuario
				.getPraticante());
		dietas = null;
		desabilitarMensagem();

	}

	public void consultarDietasNutricionista() {

		dietas = dietaService
				.buscarPorNutricionista(usuario.getNutricionista());
		dietasPraticante = null;
		desabilitarMensagem();

	}

	public String consultarDietaNome() {

		if (argumentoDietaNome.equals("")) {
			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;
		}

		if (usuario.getContaLogada() == TipoContaEnum.PRATICANTE) {

			dietasPraticante = dietaPraticanteService
					.buscarPorNome(argumentoDietaNome);

		} else {

			dietas = dietaService.buscarPorNome(argumentoDietaNome);
		}
		desabilitarMensagem();
		return LISTAR_DIETAS;
	}

	public String incluirDieta() {
		operacaoDieta = TipoOperacaoEnum.INCLUIR;
		dietaSelecionado = new Dieta();
		return FORM_DIETA;
	}

	public Dieta obterDietaSelecionado() {
		if (dietas == null)
			dietas = new ArrayList<Dieta>();
		for (Dieta dieta : dietas) {
			if (dieta.isSelecionado()) {
				return dieta;
			}
		}
		return null;
	}

	public DietaPraticante obterDietaPraticanteSelecionado() {
		if (dietasPraticante == null)
			dietasPraticante = new ArrayList<DietaPraticante>();
		for (DietaPraticante dietaPraticante : dietasPraticante) {
			if (dietaPraticante.isSelecionado()) {
				return dietaPraticante;
			}
		}
		return null;
	}

	public String editarDieta() {
		dietaSelecionado = obterDietaSelecionado();
		if (dietaSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}

		operacaoDieta = TipoOperacaoEnum.EDITAR;
		desabilitarMensagem();
		return FORM_DIETA;
	}

	public String excluirDieta() {
		dietaSelecionado = obterDietaSelecionado();
		if (dietaSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}

		dietaSelecionado.setNutricionista(null);
		dietaService.salvar(dietaSelecionado);
		adicionarMensagemInfo(EXCLUIDO_SUCESSO);
		return consultarDietas();
	}

	public String consultarRefeicoesPraticante() {
		Dieta dieta;

		if (usuario.getContaLogada() == TipoContaEnum.PRATICANTE) {
			dietaPraticanteSelecionado = obterDietaPraticanteSelecionado();

			if (dietaPraticanteSelecionado == null) {
				adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
				return ERRO;
			}

			dieta = dietaPraticanteSelecionado.getDieta();
			dietaSelecionado = new Dieta();

		} else {
			dietaSelecionado = obterDietaSelecionado();
			if (dietaSelecionado == null) {
				adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
				return ERRO;
			}

			dieta = dietaSelecionado;
		}
		dietaSelecionado = dieta;
		refeicoesPraticante = refeicaoPraticanteService.buscarPorDiaDieta(
				argumentoRefeicaoPraticanteDia, dieta);
		dietaSelecionado.setRefeicoesPraticante(refeicoesPraticante);
		desabilitarMensagem();

		return LISTAR_REFEICOES_PRATICANTE;
	}

	public String voltarListarDietas() {
		desabilitarMensagem();
		return HOME;
	}

	public String consultarRefeicaoPraticanteDia() {

		if (argumentoRefeicaoPraticanteDia == null) {
			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;
		}

		refeicoesPraticante = refeicaoPraticanteService.buscarPorDiaDieta(
				argumentoRefeicaoPraticanteDia, dietaSelecionado);

		dietaSelecionado.setRefeicoesPraticante(refeicoesPraticante);
		desabilitarMensagem();

		return LISTAR_REFEICOES_PRATICANTE;
	}

	public String incluirRefeicaoPraticante() {

		operacaoRefeicaoPraticante = TipoOperacaoEnum.INCLUIR;
		refeicaoPraticanteSelecionado = new RefeicaoPraticante();
		desabilitarMensagem();
		return FORM_REFEICAO_PRATICANTE;
	}

	public RefeicaoPraticante obterRefeicaoPraticanteSelecionado() {
		if (refeicoesPraticante == null)
			refeicoesPraticante = new ArrayList<RefeicaoPraticante>();
		for (RefeicaoPraticante refeicaoPraticante : refeicoesPraticante) {
			if (refeicaoPraticante.isSelecionado()) {
				return refeicaoPraticante;
			}
		}
		return null;
	}

	public String editarRefeicaoPraticante() {

		refeicaoPraticanteSelecionado = obterRefeicaoPraticanteSelecionado();
		if (refeicaoPraticanteSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}
		operacaoRefeicaoPraticante = TipoOperacaoEnum.EDITAR;
		desabilitarMensagem();
		return FORM_REFEICAO_PRATICANTE;
	}

	public String excluirRefeicaoPraticante() {

		refeicaoPraticanteSelecionado = obterRefeicaoPraticanteSelecionado();
		if (refeicaoPraticanteSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}
		refeicaoPraticanteService.excluir(refeicaoPraticanteSelecionado);
		adicionarMensagemInfo(EXCLUIDO_SUCESSO);
		return consultarRefeicoesPraticante();
	}

	public String consultarAlimentosPraticante() {

		refeicaoPraticanteSelecionado = obterRefeicaoPraticanteSelecionado();
		if (refeicaoPraticanteSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}

		alimentosPraticante = alimentoPraticanteService
				.buscarPorRefeicao(refeicaoPraticanteSelecionado);
		desabilitarMensagem();
		return LISTAR_ALIMENTOS_PRATICANTE;
	}

	public String voltarListarRefeicoesPraticante() {

		if (usuario.getContaLogada() == TipoContaEnum.PRATICANTE) {
			consultarDietasPraticante();
		} else {
			consultarDietasNutricionista();
		}
		desabilitarMensagem();
		return consultarDietas();
	}

	public String incluirAlimentoPraticante() {
		alimentos.clear();
		operacaoAlimentoPraticante = TipoOperacaoEnum.INCLUIR;
		alimentoPraticanteSelecionado = new AlimentoPraticante();
		desabilitarMensagem();
		return FORM_ALIMENTO_PRATICANTE;
	}

	public AlimentoPraticante obterAlimentoPraticanteSelecionado() {
		if (alimentosPraticante == null)
			alimentosPraticante = new ArrayList<AlimentoPraticante>();
		for (AlimentoPraticante alimentoPraticante : alimentosPraticante) {
			if (alimentoPraticante.isSelecionado()) {
				return alimentoPraticante;
			}
		}
		return null;
	}

	public String editarAlimentoPraticante() {

		alimentoPraticanteSelecionado = obterAlimentoPraticanteSelecionado();
		if (alimentoPraticanteSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}
		operacaoAlimentoPraticante = TipoOperacaoEnum.EDITAR;
		desabilitarMensagem();
		return FORM_ALIMENTO_PRATICANTE;
	}

	public String excluirAlimentoPraticante() {

		alimentoPraticanteSelecionado = obterAlimentoPraticanteSelecionado();
		if (alimentoPraticanteSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}
		alimentoPraticanteService.excluir(alimentoPraticanteSelecionado);
		adicionarMensagemInfo(EXCLUIDO_SUCESSO);
		return consultarAlimentosPraticante();
	}

	public String voltarListarAlimentosPraticante() {
		return consultarRefeicoesPraticante();
	}

	public String salvarRefeicaoPraticante() {

		if (!(horaAux.length() > 0 && horaAux.length() < 3)) {
			adicionarMensagemErro(MENSAGEM_HORA);
			return ERRO;
		} else {
			if (horaAux.length() == 1)
				horaAux = "0" + horaAux;
		}

		if (!(minutosAux.length() > 0 && minutosAux.length() < 3)) {
			adicionarMensagemErro(MENSAGEM_MINUTO);
			return ERRO;
		} else {
			if (minutosAux.length() == 1)
				minutosAux = "0" + minutosAux;
		}

		refeicaoPraticanteSelecionado.setHorario(horaAux + ":" + minutosAux);
		if (refeicaoPraticanteSelecionado.getTipoRefeicao() == null
				|| refeicaoPraticanteSelecionado.getDia() == null
				|| refeicaoPraticanteSelecionado.getHorario() == null) {
			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;
		}

		refeicaoPraticanteSelecionado.setDieta(dietaSelecionado);
		refeicaoPraticanteService.salvar(refeicaoPraticanteSelecionado);

		adicionarMensagemInfo(SALVO_SUCESSO);
		return consultarRefeicoesPraticante();
	}

	public String voltarFormRefeicaoPraticante() {
		return consultarRefeicoesPraticante();
	}

	public String salvarDieta() {

		if (dietaSelecionado.getNome().equals("")
				|| dietaSelecionado.getObjetivo() == null) {
			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;
		}

		dietaSelecionado.setNutricionista(usuario.getNutricionista());
		dietaService.salvar(dietaSelecionado);

		adicionarMensagemInfo(SALVO_SUCESSO);
		return consultarDietas();
	}

	public String voltarFormDieta() {
		return consultarDietas();
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

	public String consultarAlimentoNome() {
		if (argumentoAlimentoNome.equals("")) {
			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;
		}
		alimentos = alimentoService.buscarPorNome(argumentoAlimentoNome);

		desabilitarMensagem();
		return FORM_ALIMENTO_PRATICANTE;

	}

	public String salvarAlimentoPraticante() {
		alimentoSelecionado = obterAlimentoSelecionado();
		if (alimentoPraticanteSelecionado.getPorcao() == 0
				|| alimentoPraticanteSelecionado.getTipoPorcao() == null
				|| alimentoSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;
		}
		alimentoPraticanteSelecionado.setAlimento(alimentoSelecionado);
		alimentoPraticanteSelecionado
				.setRefeicaoPraticante(refeicaoPraticanteSelecionado);

		alimentoPraticanteService.salvar(alimentoPraticanteSelecionado);
		adicionarMensagemInfo(SALVO_SUCESSO);
		return consultarAlimentosPraticante();
	}

	public String voltarFormAlimentoPraticante() {
		return consultarAlimentosPraticante();
	}

	public String getArgumentoDietaNome() {
		return argumentoDietaNome;
	}

	public void setArgumentoDietaNome(String argumentoDietaNome) {
		this.argumentoDietaNome = argumentoDietaNome;
	}

	public TipoDiaEnum getArgumentoRefeicaoPraticanteDia() {
		return argumentoRefeicaoPraticanteDia;
	}

	public void setArgumentoRefeicaoPraticanteDia(
			TipoDiaEnum argumentoRefeicaoPraticanteDia) {
		this.argumentoRefeicaoPraticanteDia = argumentoRefeicaoPraticanteDia;
	}

	public String getArgumentoAlimentoNome() {
		return argumentoAlimentoNome;
	}

	public void setArgumentoAlimentoNome(String argumentoAlimentoNome) {
		this.argumentoAlimentoNome = argumentoAlimentoNome;
	}

	public String getHoraAux() {
		return horaAux;
	}

	public void setHoraAux(String horaAux) {
		this.horaAux = horaAux;
	}

	public String getMinutosAux() {
		return minutosAux;
	}

	public void setMinutosAux(String minutosAux) {
		this.minutosAux = minutosAux;
	}

	public List<Dieta> getDietas() {
		return dietas;
	}

	public void setDietas(List<Dieta> dietas) {
		this.dietas = dietas;
	}

	public List<DietaPraticante> getDietasPraticante() {
		return dietasPraticante;
	}

	public void setDietasPraticante(List<DietaPraticante> dietasPraticante) {
		this.dietasPraticante = dietasPraticante;
	}

	public List<RefeicaoPraticante> getRefeicoesPraticante() {
		return refeicoesPraticante;
	}

	public void setRefeicoesPraticante(
			List<RefeicaoPraticante> refeicoesPraticante) {
		this.refeicoesPraticante = refeicoesPraticante;
	}

	public List<AlimentoPraticante> getAlimentosPraticante() {
		return alimentosPraticante;
	}

	public void setAlimentosPraticante(
			List<AlimentoPraticante> alimentosPraticante) {
		this.alimentosPraticante = alimentosPraticante;
	}

	public List<Alimento> getAlimentos() {
		return alimentos;
	}

	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}

	public Dieta getDietaSelecionado() {
		return dietaSelecionado;
	}

	public void setDietaSelecionado(Dieta dietaSelecionado) {
		this.dietaSelecionado = dietaSelecionado;
	}

	public DietaPraticante getDietaPraticanteSelecionado() {
		return dietaPraticanteSelecionado;
	}

	public void setDietaPraticanteSelecionado(
			DietaPraticante dietaPraticanteSelecionado) {
		this.dietaPraticanteSelecionado = dietaPraticanteSelecionado;
	}

	public RefeicaoPraticante getRefeicaoPraticanteSelecionado() {
		return refeicaoPraticanteSelecionado;
	}

	public void setRefeicaoPraticanteSelecionado(
			RefeicaoPraticante refeicaoPraticanteSelecionado) {
		this.refeicaoPraticanteSelecionado = refeicaoPraticanteSelecionado;
	}

	public AlimentoPraticante getAlimentoPraticanteSelecionado() {
		return alimentoPraticanteSelecionado;
	}

	public void setAlimentoPraticanteSelecionado(
			AlimentoPraticante alimentoPraticanteSelecionado) {
		this.alimentoPraticanteSelecionado = alimentoPraticanteSelecionado;
	}

	public Alimento getAlimentoSelecionado() {
		return alimentoSelecionado;
	}

	public void setAlimentoSelecionado(Alimento alimentoSelecionado) {
		this.alimentoSelecionado = alimentoSelecionado;
	}

	public List<TipoDiaEnum> getDiasRefeicaoPraticante() {
		return diasRefeicaoPraticante;
	}

	public void setDiasRefeicaoPraticante(
			List<TipoDiaEnum> diasRefeicaoPraticante) {
		this.diasRefeicaoPraticante = diasRefeicaoPraticante;
	}

	public List<TipoDiaEnum> getDias() {
		return dias;
	}

	public void setDias(List<TipoDiaEnum> dias) {
		this.dias = dias;
	}

	public List<TipoRefeicaoEnum> getTiposRefeicao() {
		return tiposRefeicao;
	}

	public void setTiposRefeicao(List<TipoRefeicaoEnum> tiposRefeicao) {
		this.tiposRefeicao = tiposRefeicao;
	}

	public List<TipoObjetivoEnum> getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(List<TipoObjetivoEnum> objetivos) {
		this.objetivos = objetivos;
	}

	public List<TipoPorcaoEnum> getTiposPorcao() {
		return tiposPorcao;
	}

	public void setTiposPorcao(List<TipoPorcaoEnum> tiposPorcao) {
		this.tiposPorcao = tiposPorcao;
	}

	public TipoOperacaoEnum getOperacaoRefeicaoPraticante() {
		return operacaoRefeicaoPraticante;
	}

	public void setOperacaoRefeicaoPraticante(
			TipoOperacaoEnum operacaoRefeicaoPraticante) {
		this.operacaoRefeicaoPraticante = operacaoRefeicaoPraticante;
	}

	public TipoOperacaoEnum getOperacaoDieta() {
		return operacaoDieta;
	}

	public void setOperacaoDieta(TipoOperacaoEnum operacaoDieta) {
		this.operacaoDieta = operacaoDieta;
	}

	public TipoOperacaoEnum getOperacaoAlimentoPraticante() {
		return operacaoAlimentoPraticante;
	}

	public void setOperacaoAlimentoPraticante(
			TipoOperacaoEnum operacaoAlimentoPraticante) {
		this.operacaoAlimentoPraticante = operacaoAlimentoPraticante;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public DietaService getDietaService() {
		return dietaService;
	}

	public void setDietaService(DietaService dietaService) {
		this.dietaService = dietaService;
	}

	public AlimentoPraticanteService getAlimentoPraticanteService() {
		return alimentoPraticanteService;
	}

	public void setAlimentoPraticanteService(
			AlimentoPraticanteService alimentoPraticanteService) {
		this.alimentoPraticanteService = alimentoPraticanteService;
	}

	public DietaPraticanteService getDietaPraticanteService() {
		return dietaPraticanteService;
	}

	public void setDietaPraticanteService(
			DietaPraticanteService dietaPraticanteService) {
		this.dietaPraticanteService = dietaPraticanteService;
	}

	public RefeicaoPraticanteService getRefeicaoPraticanteService() {
		return refeicaoPraticanteService;
	}

	public void setRefeicaoPraticanteService(
			RefeicaoPraticanteService refeicaoPraticanteService) {
		this.refeicaoPraticanteService = refeicaoPraticanteService;
	}

	public AlimentoService getAlimentoService() {
		return alimentoService;
	}

	public void setAlimentoService(AlimentoService alimentoService) {
		this.alimentoService = alimentoService;
	}

	public String getLISTAR_DIETAS() {
		return LISTAR_DIETAS;
	}

	public String getLISTAR_REFEICOES_PRATICANTE() {
		return LISTAR_REFEICOES_PRATICANTE;
	}

	public String getLISTAR_ALIMENTOS_PRATICANTE() {
		return LISTAR_ALIMENTOS_PRATICANTE;
	}

	public String getFORM_DIETA() {
		return FORM_DIETA;
	}

	public String getFORM_REFEICAO_PRATICANTE() {
		return FORM_REFEICAO_PRATICANTE;
	}

	public String getFORM_ALIMENTO_PRATICANTE() {
		return FORM_ALIMENTO_PRATICANTE;
	}

}
