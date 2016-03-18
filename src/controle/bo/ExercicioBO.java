package controle.bo;

import java.util.ArrayList;
import java.util.List;

import modelo.entidade.treino.Exercicio;
import modelo.entidade.usuario.Usuario;
import modelo.tipo.TipoGrupoMuscularEnum;
import modelo.tipo.TipoOperacaoEnum;
import controle.service.treino.ExercicioService;
import controle.util.MensagemUtil;

public class ExercicioBO extends MensagemUtil{

	private String argumentoExercicioNome;
	private List<Exercicio> exercicios;

	private TipoOperacaoEnum operacaoExercicio;

	private List<TipoGrupoMuscularEnum> gruposMusculares;

	private Exercicio exercicioSelecionado;

	private final String LISTAR_EXERCICIOS = "listarExercicios";
	private final String FORM_EXERCICIO = "formExercicio";

	private ExercicioService exercicioService;

	private Usuario usuario;
	
	public ExercicioBO(){
		exercicioService = new ExercicioService();
		gruposMusculares = TipoGrupoMuscularEnum.getLista();
		exercicios = new ArrayList<Exercicio>();
	}
	
	public String iniciar(Usuario usuario) {
		this.usuario = usuario;
		return consultarExercicios();
	}
	
	public String consultarExercicios() {
		exercicios = exercicioService.listar();
		desabilitarMensagem();
		return LISTAR_EXERCICIOS;
	}

	public String consultarExercicioNome() {
		if (argumentoExercicioNome.equals("")) {
			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;
		}

		exercicios = exercicioService.buscarPorNome(argumentoExercicioNome);
		desabilitarMensagem();
		return LISTAR_EXERCICIOS;
	}

	public String incluirExercicio() {
		exercicioSelecionado = new Exercicio();
		operacaoExercicio = TipoOperacaoEnum.INCLUIR;
		desabilitarMensagem();
		return FORM_EXERCICIO;
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

	public String editarExercicio() {
		exercicioSelecionado = obterExercicioSelecionado();
		if (exercicioSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}

		operacaoExercicio = TipoOperacaoEnum.EDITAR;
		desabilitarMensagem();
		return FORM_EXERCICIO;
	}

	public String excluirExercicio() {
		exercicioSelecionado = obterExercicioSelecionado();
		if (exercicioSelecionado == null) {
			adicionarMensagemErro(MENSAGEM_ITEM_NAO_SELECIONADO);
			return ERRO;
		}

		exercicioService.excluir(exercicioSelecionado);
		adicionarMensagemInfo(EXCLUIDO_SUCESSO);
		return consultarExercicios();
	}

	public String voltarListarExercicios() {
		desabilitarMensagem();
		return HOME;
	}

	public String salvarExercicio() {

		if (exercicioSelecionado.getNome().equals("")
				|| exercicioSelecionado.getGrupoMuscular() == null) {
			adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
			return ERRO;
		}
		exercicioService.salvar(exercicioSelecionado);
		adicionarMensagemInfo(SALVO_SUCESSO);
		return consultarExercicios();
	}

	public String voltarFormExercicio() {
		return consultarExercicios();
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

	public TipoOperacaoEnum getOperacaoExercicio() {
		return operacaoExercicio;
	}

	public void setOperacaoExercicio(TipoOperacaoEnum operacaoExercicio) {
		this.operacaoExercicio = operacaoExercicio;
	}

	public List<TipoGrupoMuscularEnum> getGruposMusculares() {
		return gruposMusculares;
	}

	public void setGruposMusculares(List<TipoGrupoMuscularEnum> gruposMusculares) {
		this.gruposMusculares = gruposMusculares;
	}

	public Exercicio getExercicioSelecionado() {
		return exercicioSelecionado;
	}

	public void setExercicioSelecionado(Exercicio exercicioSelecionado) {
		this.exercicioSelecionado = exercicioSelecionado;
	}

	public ExercicioService getExercicioService() {
		return exercicioService;
	}

	public void setExercicioService(ExercicioService exercicioService) {
		this.exercicioService = exercicioService;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getLISTAR_EXERCICIOS() {
		return LISTAR_EXERCICIOS;
	}

	public String getFORM_EXERCICIO() {
		return FORM_EXERCICIO;
	}
	
	
	
}
