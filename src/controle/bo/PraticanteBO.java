package controle.bo;

import java.util.ArrayList;
import java.util.List;

import modelo.entidade.dieta.Dieta;
import modelo.entidade.dieta.DietaPraticante;
import modelo.entidade.resultado.Resultado;
import modelo.entidade.treino.Treino;
import modelo.entidade.treino.TreinoPraticante;
import modelo.entidade.usuario.Praticante;
import modelo.entidade.usuario.Usuario;
import modelo.tipo.TipoContaEnum;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import controle.service.dieta.DietaPraticanteService;
import controle.service.dieta.DietaService;
import controle.service.resultado.ResultadoService;
import controle.service.treino.TreinoPraticanteService;
import controle.service.treino.TreinoService;
import controle.service.usuario.PraticanteService;
import controle.util.MensagemUtil;

public class PraticanteBO extends MensagemUtil {

	private String argumentoPraticanteNome;
	private String argumentoTreinoNome;
	private String argumentoDietaNome;

	private List<Dieta> dietas;
	private List<Praticante> praticantes;
	private List<Resultado> resultados;
	private List<Treino> treinos;
	private List<TreinoPraticante> treinosPraticante;
	private List<DietaPraticante> dietasPraticante;

	private Dieta dietaSelecionado;
	private Treino treinoSelecionado;
	private TreinoPraticante treinoPraticanteSelecionado;
	private Praticante praticanteSelecionado;
	private DietaPraticante dietaPraticanteSelecionado;

	private Usuario usuario;

	private PraticanteService praticanteService;
	private TreinoPraticanteService treinoPraticanteService;
	private DietaPraticanteService dietaPraticanteService;
	private ResultadoService resultadoService;
	private TreinoService treinoService;
	private DietaService dietaService;

	private LineChartModel graficoPeso;
	private LineChartModel graficoPercentualGordura;
	private LineChartModel graficoIMC;

	private final String LISTAR_PRATICANTES = "listarPraticantes";
	private final String FORM_PRATICANTE_DIETA = "formPraticanteDieta";
	private final String FORM_PRATICANTE_TREINO = "formPraticanteTreino";
	private final String FORM_CADASTRAR_PRATICANTE = "formCadastrarPraticante";
	private final String LISTAR_RESULTADOS_PRATICANTE = "listarResultadosPraticante";
	private final String LISTAR_DIETAS_PRATICANTE = "listarDietasPraticante";
	private final String LISTAR_TREINOS_PRATICANTE = "listarTreinosPraticante";

	public PraticanteBO() {
		usuario = new Usuario();
		praticantes = new ArrayList<Praticante>();
		praticanteService = new PraticanteService();
		treinoPraticanteService = new TreinoPraticanteService();
		dietaPraticanteService = new DietaPraticanteService();
		treinoService = new TreinoService();
		dietaService = new DietaService();
		resultadoService = new ResultadoService();
	}

	public String iniciar(Usuario usuario) {
		this.usuario = usuario;

		return consultarPraticantes();
	}

	public String consultarPraticantes() {
		if (usuario.getContaLogada() == TipoContaEnum.PERSONAL) {
			praticantes = praticanteService.buscarPorPersonal(usuario
					.getPersonal());
		} else {
			praticantes = praticanteService.buscarPorNutricionista(usuario
					.getNutricionista());
		}
		desabilitarMensagem();
		return LISTAR_PRATICANTES;
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

	public TreinoPraticante obterTreinoPraticanteSelecionado() {
		if (treinosPraticante == null)
			treinosPraticante = new ArrayList<>();
		for (TreinoPraticante treinoPraticante : treinosPraticante) {
			if (treinoPraticante.isSelecionado()) {
				return treinoPraticante;
			}
		}
		return null;
	}

	public String consultarPraticanteNomeLista() {
		if (argumentoPraticanteNome.equals("")) {
			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;
		}

		if (usuario.getContaLogada() == TipoContaEnum.PERSONAL) {
			praticantes = praticanteService.buscarPorNomePersonal(
					argumentoPraticanteNome, usuario.getPersonal());
		} else {
			praticantes = praticanteService.buscarPorNomeNutricionista(
					argumentoPraticanteNome, usuario.getNutricionista());
		}
		desabilitarMensagem();
		return LISTAR_PRATICANTES;
	}

	public String consultarTreinosPraticante() {
		praticanteSelecionado = obterPraticanteSelecionado();
		if (praticanteSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}

		treinosPraticante = treinoPraticanteService
				.buscarPorPraticante(praticanteSelecionado);
		desabilitarMensagem();
		return LISTAR_TREINOS_PRATICANTE;
	}

	public String consultarDietasPraticante() {
		praticanteSelecionado = obterPraticanteSelecionado();
		if (praticanteSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}

		dietasPraticante = dietaPraticanteService
				.buscarPorPraticante(praticanteSelecionado);
		desabilitarMensagem();
		return LISTAR_DIETAS_PRATICANTE;
	}

	public Praticante obterPraticanteSelecionado() {
		if (praticantes == null)
			return null;
		for (Praticante praticante : praticantes) {
			if (praticante.isSelecionado()) {
				return praticante;
			}
		}
		return null;
	}

	public String incluirPraticante() {
		desabilitarMensagem();
		praticantes.clear();
		return FORM_CADASTRAR_PRATICANTE;
	}

	public String consultarPraticanteNomeForm() {
		if (argumentoPraticanteNome.equals("")) {
			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;
		}

		if (usuario.getContaLogada() == TipoContaEnum.PERSONAL) {
			praticantes = praticanteService.buscarPorNomeNaoPersonal(
					argumentoPraticanteNome, usuario.getPersonal());

			for (Praticante praticante : praticantes) {
				if (praticante.getPersonal() != null) {
					praticantes.remove(praticante);
				}
			}

		} else {
			praticantes = praticanteService.buscarPorNomeNaoNutricionista(
					argumentoPraticanteNome, usuario.getNutricionista());

			for (Praticante praticante : praticantes) {
				if (praticante.getNutricionista() != null) {
					praticantes.remove(praticante);
				}
			}
		}
		desabilitarMensagem();
		return FORM_CADASTRAR_PRATICANTE;
	}

	public String excluirPraticante() {
		praticanteSelecionado = obterPraticanteSelecionado();
		if (praticanteSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}

		if (usuario.getContaLogada() == TipoContaEnum.PERSONAL) {
			praticanteService.excluirPorPersonal(praticanteSelecionado);
		} else {
			praticanteService.excluirPorNutricionista(praticanteSelecionado);
		}

		adicionarMensagemInfo(SALVO_SUCESSO);

		return consultarPraticantes();
	}

	public String excluirTreinoPraticante() {
		treinoPraticanteSelecionado = obterTreinoPraticanteSelecionado();
		if (treinoPraticanteSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;
		}

		treinoPraticanteService.excluir(treinoPraticanteSelecionado);
		adicionarMensagemInfo(EXCLUIDO_SUCESSO);
		return consultarTreinosPraticante();
	}

	public String excluirDietaPraticante() {
		dietaPraticanteSelecionado = obterDietaPraticanteSelecionado();
		if (dietaPraticanteSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;
		}

		dietaPraticanteService.excluir(dietaPraticanteSelecionado);
		adicionarMensagemInfo(EXCLUIDO_SUCESSO);
		return consultarDietasPraticante();
	}

	public String voltarListarTreinosPraticante() {
		desabilitarMensagem();
		return consultarPraticantes();
	}

	public String voltarListarDietasPraticante() {
		desabilitarMensagem();
		return consultarPraticantes();
	}

	public String consultarResultadosPraticante() {
		praticanteSelecionado = obterPraticanteSelecionado();
		if (praticanteSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}

		resultados = resultadoService
				.buscarPorPraticante(praticanteSelecionado);

		// Carregar graficos
		carregarGraficoPeso();
		carregarGraficoPercentualGordura();
		carregarGraficoIMC();
		desabilitarMensagem();
		return LISTAR_RESULTADOS_PRATICANTE;
	}

	public void carregarGraficoIMC() {
		graficoIMC = new LineChartModel();
		LineChartSeries serie = new LineChartSeries();
		serie.setLabel("IMC");

		int indice = 1;

		for (Resultado resultado : resultados) {

			serie.set(
					indice,
					(resultado.getPeso() / (((resultado.getAltura()) / 100) * ((resultado
							.getAltura()) / 100))));
			indice++;

		}

		graficoIMC.addSeries(serie);
		graficoIMC.setLegendPosition("e");
		graficoIMC.setShowPointLabels(true);
		graficoIMC.getAxis(AxisType.Y).setLabel("IMC");

	}

	public void carregarGraficoPeso() {
		graficoPeso = new LineChartModel();
		LineChartSeries serie = new LineChartSeries();
		serie.setLabel("Peso");

		int indice = 1;

		for (Resultado resultado : resultados) {

			serie.set(indice, resultado.getPeso());
			indice++;

		}

		graficoPeso.addSeries(serie);
		graficoPeso.setLegendPosition("e");
		graficoPeso.setShowPointLabels(true);
		graficoPeso.getAxis(AxisType.Y).setLabel("Peso");

	}

	public void carregarGraficoPercentualGordura() {
		graficoPercentualGordura = new LineChartModel();
		LineChartSeries serie = new LineChartSeries();
		serie.setLabel("Percentual de Gordurda");

		int indice = 1;
		for (Resultado resultado : resultados) {
			serie.set(indice, resultado.getPercentualGordura());
			indice++;
		}

		graficoPercentualGordura.addSeries(serie);
		graficoPercentualGordura.setLegendPosition("e");
		graficoPercentualGordura.setShowPointLabels(true);
		graficoPercentualGordura.getAxis(AxisType.Y).setLabel(
				"Percentual de Gordura");

	}

	public String atribuirTreino() {
		treinos = null;
		praticanteSelecionado = obterPraticanteSelecionado();
		if (praticanteSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}
		treinoPraticanteSelecionado = new TreinoPraticante();
		treinoPraticanteSelecionado.setPraticante(praticanteSelecionado);
		desabilitarMensagem();
		return FORM_PRATICANTE_TREINO;
	}

	public String atribuirDieta() {
		dietas = null;
		praticanteSelecionado = obterPraticanteSelecionado();
		if (praticanteSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}

		dietaPraticanteSelecionado = new DietaPraticante();
		dietaPraticanteSelecionado.setPraticante(praticanteSelecionado);
		desabilitarMensagem();
		return FORM_PRATICANTE_DIETA;
	}

	public String voltarListarPraticantes() {
		desabilitarMensagem();
		return HOME;
	}

	public String salvarTreinoPraticante() {
		treinoSelecionado = obterTreinoSelecionado();
		if (treinoSelecionado == null
				|| treinoPraticanteSelecionado.getDataFim() == null
				|| treinoPraticanteSelecionado.getDataInicio() == null) {
			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;

		}

		treinoPraticanteSelecionado.setTreino(treinoSelecionado);
		treinoPraticanteSelecionado.setPraticante(praticanteSelecionado);
		treinoPraticanteService.salvar(treinoPraticanteSelecionado);
		adicionarMensagemInfo(SALVO_SUCESSO);
		return consultarPraticantes();
	}

	public String voltarFormPraticanteTreino() {
		return consultarPraticantes();
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

	public Treino obterTreinoSelecionado() {
		if (treinos == null)
			treinos = new ArrayList<Treino>();
		for (Treino treino : treinos) {
			if (treino.isSelecionado()) {
				return treino;
			}
		}
		return null;
	}

	public String salvarDietaPraticante() {
		dietaSelecionado = obterDietaSelecionado();
		if (dietaSelecionado == null
				|| dietaPraticanteSelecionado.getDataFim() == null
				|| dietaPraticanteSelecionado.getDataInicio() == null) {
			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;

		}

		dietaPraticanteSelecionado.setDieta(dietaSelecionado);
		dietaPraticanteSelecionado.setPraticante(praticanteSelecionado);
		dietaPraticanteService.salvar(dietaPraticanteSelecionado);
		adicionarMensagemInfo(SALVO_SUCESSO);
		return consultarPraticantes();

	}

	public String voltarFormPraticanteDieta() {
		return consultarPraticantes();
	}

	public String adicionarPraticante() {
		praticanteSelecionado = obterPraticanteSelecionado();
		if (praticanteSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}

		if (usuario.getContaLogada() == TipoContaEnum.PERSONAL) {
			praticanteService.salvarPorPersonal(praticanteSelecionado,
					usuario.getPersonal());
		} else {
			praticanteService.salvarPorNutricionista(praticanteSelecionado,
					usuario.getNutricionista());
		}
		desabilitarMensagem();
		return consultarPraticantes();
	}

	public String voltarFormCadastrarPraticante() {
		return consultarPraticantes();
	}

	public String consultarTreinoNome() {
		if (argumentoTreinoNome.equals("")) {
			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;
		}

		treinos = treinoService.buscarPorNomePersonal(argumentoTreinoNome,
				usuario.getPersonal());

		desabilitarMensagem();
		return FORM_PRATICANTE_TREINO;
	}

	public String consultarDietaNome() {
		if (argumentoDietaNome.equals("")) {
			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;
		}

		dietas = dietaService.buscarPorNomeNutricionista(argumentoDietaNome,
				usuario.getNutricionista());
		desabilitarMensagem();
		return FORM_PRATICANTE_DIETA;

	}

	public String voltarListarResultados() {
		return consultarPraticantes();
	}

	public String getArgumentoPraticanteNome() {
		return argumentoPraticanteNome;
	}

	public void setArgumentoPraticanteNome(String argumentoPraticanteNome) {
		this.argumentoPraticanteNome = argumentoPraticanteNome;
	}

	public String getArgumentoTreinoNome() {
		return argumentoTreinoNome;
	}

	public void setArgumentoTreinoNome(String argumentoTreinoNome) {
		this.argumentoTreinoNome = argumentoTreinoNome;
	}

	public String getArgumentoDietaNome() {
		return argumentoDietaNome;
	}

	public void setArgumentoDietaNome(String argumentoDietaNome) {
		this.argumentoDietaNome = argumentoDietaNome;
	}

	public List<Dieta> getDietas() {
		return dietas;
	}

	public void setDietas(List<Dieta> dietas) {
		this.dietas = dietas;
	}

	public List<Praticante> getPraticantes() {
		return praticantes;
	}

	public void setPraticantes(List<Praticante> praticantes) {
		this.praticantes = praticantes;
	}

	public List<Resultado> getResultados() {
		return resultados;
	}

	public void setResultados(List<Resultado> resultados) {
		this.resultados = resultados;
	}

	public List<Treino> getTreinos() {
		return treinos;
	}

	public void setTreinos(List<Treino> treinos) {
		this.treinos = treinos;
	}

	public List<TreinoPraticante> getTreinosPraticante() {
		return treinosPraticante;
	}

	public void setTreinosPraticante(List<TreinoPraticante> treinosPraticante) {
		this.treinosPraticante = treinosPraticante;
	}

	public List<DietaPraticante> getDietasPraticante() {
		return dietasPraticante;
	}

	public void setDietasPraticante(List<DietaPraticante> dietasPraticante) {
		this.dietasPraticante = dietasPraticante;
	}

	public Dieta getDietaSelecionado() {
		return dietaSelecionado;
	}

	public void setDietaSelecionado(Dieta dietaSelecionado) {
		this.dietaSelecionado = dietaSelecionado;
	}

	public Treino getTreinoSelecionado() {
		return treinoSelecionado;
	}

	public void setTreinoSelecionado(Treino treinoSelecionado) {
		this.treinoSelecionado = treinoSelecionado;
	}

	public TreinoPraticante getTreinoPraticanteSelecionado() {
		return treinoPraticanteSelecionado;
	}

	public void setTreinoPraticanteSelecionado(
			TreinoPraticante treinoPraticanteSelecionado) {
		this.treinoPraticanteSelecionado = treinoPraticanteSelecionado;
	}

	public Praticante getPraticanteSelecionado() {
		return praticanteSelecionado;
	}

	public void setPraticanteSelecionado(Praticante praticanteSelecionado) {
		this.praticanteSelecionado = praticanteSelecionado;
	}

	public DietaPraticante getDietaPraticanteSelecionado() {
		return dietaPraticanteSelecionado;
	}

	public void setDietaPraticanteSelecionado(
			DietaPraticante dietaPraticanteSelecionado) {
		this.dietaPraticanteSelecionado = dietaPraticanteSelecionado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public PraticanteService getPraticanteService() {
		return praticanteService;
	}

	public void setPraticanteService(PraticanteService praticanteService) {
		this.praticanteService = praticanteService;
	}

	public TreinoPraticanteService getTreinoPraticanteService() {
		return treinoPraticanteService;
	}

	public void setTreinoPraticanteService(
			TreinoPraticanteService treinoPraticanteService) {
		this.treinoPraticanteService = treinoPraticanteService;
	}

	public DietaPraticanteService getDietaPraticanteService() {
		return dietaPraticanteService;
	}

	public void setDietaPraticanteService(
			DietaPraticanteService dietaPraticanteService) {
		this.dietaPraticanteService = dietaPraticanteService;
	}

	public ResultadoService getResultadoService() {
		return resultadoService;
	}

	public void setResultadoService(ResultadoService resultadoService) {
		this.resultadoService = resultadoService;
	}

	public TreinoService getTreinoService() {
		return treinoService;
	}

	public void setTreinoService(TreinoService treinoService) {
		this.treinoService = treinoService;
	}

	public DietaService getDietaService() {
		return dietaService;
	}

	public void setDietaService(DietaService dietaService) {
		this.dietaService = dietaService;
	}

	public LineChartModel getGraficoPeso() {
		return graficoPeso;
	}

	public void setGraficoPeso(LineChartModel graficoPeso) {
		this.graficoPeso = graficoPeso;
	}

	public LineChartModel getGraficoPercentualGordura() {
		return graficoPercentualGordura;
	}

	public void setGraficoPercentualGordura(
			LineChartModel graficoPercentualGordura) {
		this.graficoPercentualGordura = graficoPercentualGordura;
	}

	public LineChartModel getGraficoIMC() {
		return graficoIMC;
	}

	public void setGraficoIMC(LineChartModel graficoIMC) {
		this.graficoIMC = graficoIMC;
	}

	public String getLISTAR_PRATICANTES() {
		return LISTAR_PRATICANTES;
	}

	public String getFORM_PRATICANTE_DIETA() {
		return FORM_PRATICANTE_DIETA;
	}

	public String getFORM_PRATICANTE_TREINO() {
		return FORM_PRATICANTE_TREINO;
	}

	public String getFORM_CADASTRAR_PRATICANTE() {
		return FORM_CADASTRAR_PRATICANTE;
	}

	public String getLISTAR_RESULTADOS_PRATICANTE() {
		return LISTAR_RESULTADOS_PRATICANTE;
	}

	public String getLISTAR_DIETAS_PRATICANTE() {
		return LISTAR_DIETAS_PRATICANTE;
	}

	public String getLISTAR_TREINOS_PRATICANTE() {
		return LISTAR_TREINOS_PRATICANTE;
	}

}
