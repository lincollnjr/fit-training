package controle.bo;

import java.util.ArrayList;
import java.util.List;

import modelo.entidade.treino.Exercicio;
import modelo.entidade.treino.ExercicioPraticante;
import modelo.entidade.treino.Treino;
import modelo.entidade.treino.TreinoPraticante;
import modelo.entidade.usuario.Usuario;
import modelo.tipo.TipoContaEnum;
import modelo.tipo.TipoDiaEnum;
import modelo.tipo.TipoObjetivoEnum;
import modelo.tipo.TipoOperacaoEnum;
import controle.service.treino.ExercicioPraticanteService;
import controle.service.treino.ExercicioService;
import controle.service.treino.TreinoPraticanteService;
import controle.service.treino.TreinoService;
import controle.util.MensagemUtil;

public class TreinoBO extends MensagemUtil{

	private Treino treinoSelecionado;
	private ExercicioPraticante exercicioPraticanteSelecionado;
	private TreinoPraticante treinoPraticanteSelecionado;
	private Exercicio exercicioSelecionado;

	private String argumentoExercicioNome;
	private String argumentoTreinoNome;
	private TipoDiaEnum argumentoExercicioPraticanteDia;

	private List<Exercicio> exercicios;
	private List<Treino> treinos;
	private List<TreinoPraticante> treinosPraticante;
	private List<ExercicioPraticante> exerciciosPraticante;

	private TipoOperacaoEnum operacaoTreino;
	private TipoOperacaoEnum operacaoExercicioPraticante;

	private List<TipoObjetivoEnum> objetivos;
	private List<TipoDiaEnum> dias;

	private List<TipoDiaEnum> diasTreino;

	private Usuario usuario;

	private TreinoPraticanteService treinoPraticanteService;
	private TreinoService treinoService;
	private ExercicioPraticanteService exercicioPraticanteService;
	private ExercicioService exercicioService;

	private final String LISTAR_TREINO = "listarTreinos";
	private final String LISTAR_EXERCICIOS_PRATICANTE = "listarExerciciosPraticante";
	private final String FORM_TREINO = "formTreino";
	private final String FORM_EXERCICIOS_PRATICANTE = "formExercicioPraticante";

	public TreinoBO() {
		objetivos = TipoObjetivoEnum.getLista();

		dias = TipoDiaEnum.getLista();

		treinoService = new TreinoService();
		treinoPraticanteService = new TreinoPraticanteService();
		exercicioPraticanteService = new ExercicioPraticanteService();
		exercicioService = new ExercicioService();

		treinos = new ArrayList<Treino>();
		treinosPraticante = new ArrayList<TreinoPraticante>();
		exerciciosPraticante = new ArrayList<ExercicioPraticante>();
	}

	public String iniciar(Usuario usuario) {

		this.usuario = usuario;
		return consultarTreinos();

	}

	public String consultarTreinos() {
		if (usuario.getContaLogada() == TipoContaEnum.PRATICANTE) {
			consultarTreinosPraticante();
		} else {
			consultarTreinosPersonal();
		}

		desabilitarMensagem();
		return LISTAR_TREINO;
	}

	public void consultarTreinosPraticante() {

		treinosPraticante = treinoPraticanteService.buscarPorPraticante(usuario
				.getPraticante());
		treinos = null;
		desabilitarMensagem();

	}

	public void consultarTreinosPersonal() {

		treinosPraticante = null;
		treinos = treinoService.buscarPorPersonal(usuario.getPersonal());
		desabilitarMensagem();

	}

	public String consultarTreinoNome() {

		if (argumentoTreinoNome.equals("")) {
			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;
		}

		if (usuario.getContaLogada() == TipoContaEnum.PRATICANTE) {

			treinosPraticante = treinoPraticanteService
					.buscarPorNome(argumentoTreinoNome);

		} else {

			treinos = treinoService.buscarPorNome(argumentoTreinoNome);
		}
		desabilitarMensagem();
		return LISTAR_TREINO;
	}

	public String incluirTreino() {
		operacaoTreino = TipoOperacaoEnum.INCLUIR;
		treinoSelecionado = new Treino();
		desabilitarMensagem();
		return FORM_TREINO;
	}

	public String editarTreino() {
		treinoSelecionado = obterTreinoSelecionado();
		if (treinoSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}

		operacaoTreino = TipoOperacaoEnum.EDITAR;
		desabilitarMensagem();
		return FORM_TREINO;
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

	public String excluirTreino() {
		treinoSelecionado = obterTreinoSelecionado();
		if (treinoSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}

		treinoSelecionado.setPersonal(null);
		treinoService.salvar(treinoSelecionado);
		adicionarMensagemInfo(EXCLUIDO_SUCESSO);
		return consultarTreinos();
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

	public String consultarExercicioNome() {
		if (argumentoExercicioNome.equals("")) {
			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;
		}

		exercicios = exercicioService.buscarPorNome(argumentoExercicioNome);
		desabilitarMensagem();
		return FORM_EXERCICIOS_PRATICANTE;
	}

	public String consultarExerciciosPraticante() {
		argumentoExercicioPraticanteDia = TipoDiaEnum.A;
		if (usuario.getContaLogada() == TipoContaEnum.PRATICANTE) {
			treinoPraticanteSelecionado = obterTreinoPraticanteSelecionado();
			if (treinoPraticanteSelecionado == null) {
				adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
				return ERRO;
			}

			exerciciosPraticante = exercicioPraticanteService
					.buscarPorDiaTreino(TipoDiaEnum.A,
							treinoPraticanteSelecionado.getTreino());

		} else {
			treinoSelecionado = obterTreinoSelecionado();
			if (treinoSelecionado == null) {
				adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
				return ERRO;
			}

			exerciciosPraticante = exercicioPraticanteService
					.buscarPorDiaTreino(TipoDiaEnum.A, treinoSelecionado);
		}
		desabilitarMensagem();

		return LISTAR_EXERCICIOS_PRATICANTE;
	}

	public String voltarListarTreino() {
		desabilitarMensagem();
		return HOME;
	}

	public String consultarExerciciosPraticanteDia() {

		if (argumentoExercicioPraticanteDia == null) {
			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;
		}

		if (usuario.getContaLogada() == TipoContaEnum.PRATICANTE) {
			exerciciosPraticante = exercicioPraticanteService
					.buscarPorDiaTreino(argumentoExercicioPraticanteDia,
							treinoPraticanteSelecionado.getTreino());
		} else {
			exerciciosPraticante = exercicioPraticanteService
					.buscarPorDiaTreino(argumentoExercicioPraticanteDia,
							treinoSelecionado);
		}

		desabilitarMensagem();
		return LISTAR_EXERCICIOS_PRATICANTE;
	}

	public String incluirExercicioPraticante() {
		exercicios = null;
		operacaoExercicioPraticante = TipoOperacaoEnum.INCLUIR;
		exercicioPraticanteSelecionado = new ExercicioPraticante();
		desabilitarMensagem();
		return FORM_EXERCICIOS_PRATICANTE;
	}

	public Exercicio obterExercicioSelecionado() {
		if (exercicios == null)
			exercicios = new ArrayList<Exercicio>();
		for (Exercicio exercicio : exercicios) {
			if (exercicio.isSelecionado()) {
				return exercicio;
			}
		}

		return null;
	}

	public ExercicioPraticante obterExercicioPraticanteSelecionado() {
		if (exerciciosPraticante == null)
			exerciciosPraticante = new ArrayList<ExercicioPraticante>();
		for (ExercicioPraticante exercicioPraticante : exerciciosPraticante) {
			if (exercicioPraticante.isSelecionado()) {
				return exercicioPraticante;
			}
		}
		return null;
	}

	public String editarExercicioPraticante() {
		exercicios = null;
		exercicioPraticanteSelecionado = obterExercicioPraticanteSelecionado();
		if (exercicioPraticanteSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}
		operacaoExercicioPraticante = TipoOperacaoEnum.EDITAR;
		desabilitarMensagem();
		return FORM_EXERCICIOS_PRATICANTE;
	}

	public String excluirExercicioPraticante() {
		exercicioPraticanteSelecionado = obterExercicioPraticanteSelecionado();
		if (exercicioPraticanteSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}

		exercicioPraticanteService.excluir(exercicioPraticanteSelecionado);
		adicionarMensagemInfo(EXCLUIDO_SUCESSO);
		return consultarExerciciosPraticante();
	}

	public String voltarListarExerciciosPraticante() {
		if (usuario.getContaLogada() == TipoContaEnum.PRATICANTE) {
			consultarTreinosPraticante();
		} else {
			consultarTreinosPersonal();
		}

		desabilitarMensagem();
		return consultarTreinos();
	}

	public String salvarTreino() {

		if (treinoSelecionado.getNome() == null
				|| treinoSelecionado.getNome().equals("")
				|| treinoSelecionado.getObjetivo() == null) {
			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;
		}

		treinoSelecionado.setPersonal(usuario.getPersonal());

		treinoService.salvar(treinoSelecionado);

		adicionarMensagemInfo(SALVO_SUCESSO);
		return consultarTreinos();
	}

	public String voltarFormTreino() {
		desabilitarMensagem();
		return consultarTreinos();
	}

	public String salvarExercicioPraticante() {
		exercicioSelecionado = obterExercicioSelecionado();
		if (exercicioSelecionado == null
				|| exercicioPraticanteSelecionado.getDia() == null
				|| exercicioPraticanteSelecionado.getQtdRepeticoes() == 0
				|| exercicioPraticanteSelecionado.getQtdSeries() == 0) {

			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;

		}

		exercicioPraticanteSelecionado.setExercicio(exercicioSelecionado);
		exercicioPraticanteSelecionado.setTreino(treinoSelecionado);

		exercicioPraticanteService.salvar(exercicioPraticanteSelecionado);
		adicionarMensagemInfo(SALVO_SUCESSO);
		return consultarExerciciosPraticante();
	}

	public String voltarFormExerciciosPraticante() {
		desabilitarMensagem();
		return consultarExerciciosPraticante();
	}

	public Treino getTreinoSelecionado() {
		return treinoSelecionado;
	}

	public void setTreinoSelecionado(Treino treinoSelecionado) {
		this.treinoSelecionado = treinoSelecionado;
	}

	public ExercicioPraticante getExercicioPraticanteSelecionado() {
		return exercicioPraticanteSelecionado;
	}

	public void setExercicioPraticanteSelecionado(
			ExercicioPraticante exercicioPraticanteSelecionado) {
		this.exercicioPraticanteSelecionado = exercicioPraticanteSelecionado;
	}

	public String getArgumentoTreinoNome() {
		return argumentoTreinoNome;
	}

	public void setArgumentoTreinoNome(String argumentoTreinoNome) {
		this.argumentoTreinoNome = argumentoTreinoNome;
	}

	public TipoDiaEnum getArgumentoExercicioPraticanteDia() {
		return argumentoExercicioPraticanteDia;
	}

	public void setArgumentoExercicioPraticanteDia(
			TipoDiaEnum argumentoExercicioPraticanteDia) {
		this.argumentoExercicioPraticanteDia = argumentoExercicioPraticanteDia;
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

	public List<ExercicioPraticante> getExerciciosPraticante() {
		return exerciciosPraticante;
	}

	public void setExerciciosPraticante(
			List<ExercicioPraticante> exerciciosPraticante) {
		this.exerciciosPraticante = exerciciosPraticante;
	}

	public List<TipoObjetivoEnum> getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(List<TipoObjetivoEnum> objetivos) {
		this.objetivos = objetivos;
	}

	public List<TipoDiaEnum> getDias() {
		return dias;
	}

	public void setDias(List<TipoDiaEnum> dias) {
		this.dias = dias;
	}

	public List<TipoDiaEnum> getDiasTreino() {
		return diasTreino;
	}

	public void setDiasTreino(List<TipoDiaEnum> diasTreino) {
		this.diasTreino = diasTreino;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoOperacaoEnum getOperacaoTreino() {
		return operacaoTreino;
	}

	public void setOperacaoTreino(TipoOperacaoEnum operacaoTreino) {
		this.operacaoTreino = operacaoTreino;
	}

	public TipoOperacaoEnum getOperacaoExercicioPraticante() {
		return operacaoExercicioPraticante;
	}

	public void setOperacaoExercicioPraticante(
			TipoOperacaoEnum operacaoExercicioPraticante) {
		this.operacaoExercicioPraticante = operacaoExercicioPraticante;
	}

	public TreinoPraticante getTreinoPraticanteSelecionado() {
		return treinoPraticanteSelecionado;
	}

	public void setTreinoPraticanteSelecionado(
			TreinoPraticante treinoPraticanteSelecionado) {
		this.treinoPraticanteSelecionado = treinoPraticanteSelecionado;
	}

	public TreinoPraticanteService getTreinoPraticanteService() {
		return treinoPraticanteService;
	}

	public void setTreinoPraticanteService(
			TreinoPraticanteService treinoPraticanteService) {
		this.treinoPraticanteService = treinoPraticanteService;
	}

	public TreinoService getTreinoService() {
		return treinoService;
	}

	public void setTreinoService(TreinoService treinoService) {
		this.treinoService = treinoService;
	}

	public Exercicio getExercicioSelecionado() {
		return exercicioSelecionado;
	}

	public void setExercicioSelecionado(Exercicio exercicioSelecionado) {
		this.exercicioSelecionado = exercicioSelecionado;
	}

	public String getArgumentoExercicioNome() {
		return argumentoExercicioNome;
	}

	public void setArgumentoExercicioNome(String argumentoExercicioNome) {
		this.argumentoExercicioNome = argumentoExercicioNome;
	}

	public List<Exercicio> getExercicios() {
		return exercicios;
	}

	public void setExercicios(List<Exercicio> exercicios) {
		this.exercicios = exercicios;
	}

	public ExercicioPraticanteService getExercicioPraticanteService() {
		return exercicioPraticanteService;
	}

	public void setExercicioPraticanteService(
			ExercicioPraticanteService exercicioPraticanteService) {
		this.exercicioPraticanteService = exercicioPraticanteService;
	}

	public String getLISTAR_TREINO() {
		return LISTAR_TREINO;
	}

	public String getLISTAR_EXERCICIOS_PRATICANTE() {
		return LISTAR_EXERCICIOS_PRATICANTE;
	}

	public String getFORM_TREINO() {
		return FORM_TREINO;
	}

	public String getFORM_EXERCICIOS_PRATICANTE() {
		return FORM_EXERCICIOS_PRATICANTE;
	}

}
