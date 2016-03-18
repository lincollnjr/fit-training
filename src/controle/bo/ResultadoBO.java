package controle.bo;

import java.util.ArrayList;
import java.util.List;

import modelo.entidade.resultado.Resultado;
import modelo.entidade.usuario.Praticante;
import modelo.entidade.usuario.Usuario;
import modelo.tipo.TipoOperacaoEnum;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import controle.service.resultado.ResultadoService;
import controle.util.MensagemUtil;

public class ResultadoBO extends MensagemUtil {

	private ResultadoService resultadoService;

	private Resultado resultadoSelecionado;

	private TipoOperacaoEnum operacaoResultado;

	private List<Resultado> resultados;

	private Usuario usuario;

	private LineChartModel graficoPeso;
	private LineChartModel graficoIMC;
	private LineChartModel graficoPercentualGordura;

	private final String LISTAR_RESULTADOS = "listarResultados";
	private final String FORM_RESULTADO = "formResultado";

	public ResultadoBO() {
		resultadoService = new ResultadoService();
	}

	public String iniciar(Usuario usuario) {
		this.usuario = usuario;
		return consultarResultados();
	}

	public String consultarResultados() {

		resultados = resultadoService.buscarPorPraticante(usuario
				.getPraticante());

		// Carregar graficos
		carregarGraficoPeso();
		carregarGraficoPercentualGordura();
		carregarGraficoIMC();
		desabilitarMensagem();
		return LISTAR_RESULTADOS;
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

	public String consultarResultadosPraticante(Praticante praticante,
			Usuario usuario) {

		if (praticante == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}
		this.usuario = usuario;
		resultados = resultadoService.buscarPorPraticante(praticante);
		desabilitarMensagem();
		return LISTAR_RESULTADOS;

	}

	public String voltarListarResultados() {
		desabilitarMensagem();
		return HOME;
	}

	public Resultado obterResultadoSelecionado() {
		if (resultados == null)
			resultados = new ArrayList<Resultado>();
		for (Resultado resultado : resultados) {
			if (resultado.isSelecionado()) {
				return resultado;
			}
		}
		return null;
	}

	public String excluirResultado() {

		resultadoSelecionado = obterResultadoSelecionado();
		if (resultadoSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}

		resultadoService.excluir(resultadoSelecionado);
		adicionarMensagemInfo(EXCLUIDO_SUCESSO);
		return consultarResultados();

	}

	public String editarResultado() {
		resultadoSelecionado = obterResultadoSelecionado();
		if (resultadoSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}

		operacaoResultado = TipoOperacaoEnum.EDITAR;
		desabilitarMensagem();
		return FORM_RESULTADO;
	}

	public String incluirResultado() {
		resultadoSelecionado = new Resultado();
		operacaoResultado = TipoOperacaoEnum.INCLUIR;
		return FORM_RESULTADO;
	}

	public String salvarResultado() {
		if (resultadoSelecionado.getAltura() < 0
				|| resultadoSelecionado.getData() == null
				|| resultadoSelecionado.getPercentualGordura() < 0
				|| resultadoSelecionado.getPeso() < 0) {
			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;
		}
		resultadoSelecionado.setPraticante(usuario.getPraticante());
		resultadoService.salvar(resultadoSelecionado);
		adicionarMensagemInfo(SALVO_SUCESSO);
		return consultarResultados();
	}

	public String voltarFormResultado() {
		desabilitarMensagem();
		return consultarResultados();
	}

	public ResultadoService getResultadoService() {
		return resultadoService;
	}

	public void setResultadoService(ResultadoService resultadoService) {
		this.resultadoService = resultadoService;
	}

	public Resultado getResultadoSelecionado() {
		return resultadoSelecionado;
	}

	public void setResultadoSelecionado(Resultado resultadoSelecionado) {
		this.resultadoSelecionado = resultadoSelecionado;
	}

	public TipoOperacaoEnum getOperacaoResultado() {
		return operacaoResultado;
	}

	public void setOperacaoResultado(TipoOperacaoEnum operacaoResultado) {
		this.operacaoResultado = operacaoResultado;
	}

	public List<Resultado> getResultados() {
		return resultados;
	}

	public void setResultados(List<Resultado> resultados) {
		this.resultados = resultados;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LineChartModel getGraficoPeso() {
		return graficoPeso;
	}

	public void setGraficoPeso(LineChartModel graficoPeso) {
		this.graficoPeso = graficoPeso;
	}

	public LineChartModel getGraficoIMC() {
		return graficoIMC;
	}

	public void setGraficoIMC(LineChartModel graficoIMC) {
		this.graficoIMC = graficoIMC;
	}

	public LineChartModel getGraficoPercentualGordura() {
		return graficoPercentualGordura;
	}

	public void setGraficoPercentualGordura(
			LineChartModel graficoPercentualGordura) {
		this.graficoPercentualGordura = graficoPercentualGordura;
	}

	public String getLISTAR_RESULTADOS() {
		return LISTAR_RESULTADOS;
	}

	public String getFORM_RESULTADO() {
		return FORM_RESULTADO;
	}

}
