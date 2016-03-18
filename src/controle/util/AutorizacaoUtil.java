package controle.util;

import java.util.ArrayList;
import java.util.List;

import modelo.tipo.TipoContaEnum;

public class AutorizacaoUtil {

	private List<EstruturaAutorizacao> autorizacoes;
	private List<String> paginas;
	
	private final String HOME = "/FitTraining/pages/home/home.jsf";
	private final String PAGINA_INICIAL = "/FitTraining/";
	private final String PAGINA_INICIAL_SEM_BARRA = "/FitTraining";
	private final String INDEX = "/FitTraining/index.jsf";
	private final String LISTAR_ALIMENTOS = "/FitTraining/pages/alimento/listarAlimentos.jsf";
	private final String FORM_ALIMENTO = "/FitTraining/pages/alimento/formAlimento.jsf";
	private final String LOGIN_USUARIO = "/FitTraining/pages/usuario/loginUsuario.jsf";
	private final String FORM_USUARIO = "/FitTraining/pages/usuario/formUsuario.jsf";
	private final String LISTAR_DIETAS = "/FitTraining/pages/dieta/listarDietas.jsf";
	private final String LISTAR_REFEICOES_PRATICANTE = "/FitTraining/pages/dieta/listarRefeicoesPraticante.jsf";
	private final String LISTAR_ALIMENTOS_PRATICANTE = "/FitTraining/pages/dieta/listarAlimentosPraticante.jsf";
	private final String FORM_DIETA = "/FitTraining/pages/dieta/formDieta.jsf";
	private final String FORM_REFEICAO_PRATICANTE = "/FitTraining/pages/dieta/formRefeicaoPraticante.jsf";
	private final String FORM_ALIMENTO_PRATICANTE = "/FitTraining/pages/dieta/formAlimentoPraticante.jsf";
	private final String LISTAR_EXERCICIOS = "/FitTraining/pages/exercicio/listarExercicios.jsf.jsf";
	private final String FORM_EXERCICIO = "/FitTraining/pages/exercicio/formExercicio.jsf";
	private final String LISTAR_PRATICANTES = "/FitTraining/pages/praticante/listarPraticantes.jsf";
	private final String FORM_PRATICANTE_DIETA = "/FitTraining/pages/praticante/formPraticanteDieta.jsf";
	private final String FORM_PRATICANTE_TREINO = "/FitTraining/pages/praticante/formPraticanteTreino.jsf";
	private final String FORM_CADASTRAR_PRATICANTE = "/FitTraining/pages/praticante/formCadastrarPraticante.jsf";
	private final String LISTAR_RESULTADOS_PRATICANTE = "/FitTraining/pages/praticante/listarResultadosPraticante.jsf";
	private final String LISTAR_DIETAS_PRATICANTE = "/FitTraining/pages/praticante/listarDietasPraticante.jsf";
	private final String LISTAR_TREINOS_PRATICANTE = "/FitTraining/pages/praticante/listarTreinosPraticante.jsf";
	private final String LISTAR_RESULTADOS = "/FitTraining/pages/resultado/listarResultados.jsf";
	private final String FORM_RESULTADO = "/FitTraining/pages/resultado/formResultado.jsf";
	private final String LISTAR_TREINOS = "/FitTraining/pages/treino/listarTreinos.jsf";
	private final String LISTAR_EXERCICIOS_PRATICANTE = "/FitTraining/pages/treino/listarExerciciosPraticante.jsf";
	private final String FORM_TREINO = "/FitTraining/pages/treino/formTreino.jsf";
	private final String FORM_EXERCICIOS_PRATICANTE = "/FitTraining/pages/treino/formExercicioPraticante.jsf";
	private final String ERRO_ACESSO = "/FitTraining/pages/erro/erroAcesso.jsf";
	private final String ERRO_ACESSO_LOGOUT = "/FitTraining/pages/erro/erroAcessoLogout.jsf";

	public AutorizacaoUtil() {
		autorizacoes = new ArrayList<AutorizacaoUtil.EstruturaAutorizacao>();
		instanciarAutorizacoes();
		
		paginas = new ArrayList<String>();
		instanciarPaginas();
	}

	private void instanciarPaginas() {
		paginas.add(HOME);
		paginas.add(PAGINA_INICIAL);
		paginas.add(PAGINA_INICIAL_SEM_BARRA);
		paginas.add(INDEX);
		paginas.add(LISTAR_ALIMENTOS);
		paginas.add(FORM_ALIMENTO);
		paginas.add(LOGIN_USUARIO);
		paginas.add(FORM_USUARIO);
		paginas.add(LISTAR_DIETAS);
		paginas.add(LISTAR_REFEICOES_PRATICANTE);
		paginas.add(LISTAR_ALIMENTOS_PRATICANTE);
		paginas.add(FORM_DIETA);
		paginas.add(FORM_REFEICAO_PRATICANTE);
		paginas.add(FORM_ALIMENTO_PRATICANTE);
		paginas.add(LISTAR_EXERCICIOS);
		paginas.add(FORM_EXERCICIO);
		paginas.add(LISTAR_PRATICANTES);
		paginas.add(FORM_PRATICANTE_DIETA);
		paginas.add(FORM_PRATICANTE_TREINO);
		paginas.add(FORM_CADASTRAR_PRATICANTE);
		paginas.add(LISTAR_RESULTADOS_PRATICANTE);
		paginas.add(LISTAR_DIETAS_PRATICANTE);
		paginas.add(LISTAR_TREINOS_PRATICANTE);
		paginas.add(LISTAR_RESULTADOS);
		paginas.add(FORM_RESULTADO);
		paginas.add(LISTAR_TREINOS);
		paginas.add(LISTAR_EXERCICIOS_PRATICANTE);
		paginas.add(FORM_TREINO);
		paginas.add(FORM_EXERCICIOS_PRATICANTE);
		paginas.add(ERRO_ACESSO);
		paginas.add(ERRO_ACESSO_LOGOUT);		
		
	}

	private void instanciarAutorizacoes() {
		/* Praticante */
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.PRATICANTE,
				LISTAR_TREINOS));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.PRATICANTE,
				LISTAR_EXERCICIOS_PRATICANTE));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.PRATICANTE,
				LISTAR_DIETAS));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.PRATICANTE,
				LISTAR_REFEICOES_PRATICANTE));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.PRATICANTE,
				LISTAR_RESULTADOS));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.PRATICANTE,
				FORM_RESULTADO));

		/* Personal */
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.PERSONAL,
				LISTAR_TREINOS));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.PERSONAL,
				LISTAR_EXERCICIOS_PRATICANTE));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.PERSONAL,
				FORM_TREINO));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.PERSONAL,
				FORM_EXERCICIOS_PRATICANTE));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.PERSONAL,
				LISTAR_EXERCICIOS));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.PERSONAL,
				FORM_EXERCICIO));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.PERSONAL,
				LISTAR_PRATICANTES));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.PERSONAL,
				LISTAR_TREINOS_PRATICANTE));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.PERSONAL,
				LISTAR_RESULTADOS_PRATICANTE));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.PERSONAL,
				FORM_CADASTRAR_PRATICANTE));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.PERSONAL,
				FORM_PRATICANTE_TREINO));

		/* Nutricionista */
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.NUTRICIONISTA,
				FORM_ALIMENTO));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.NUTRICIONISTA,
				LISTAR_ALIMENTOS));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.NUTRICIONISTA,
				FORM_ALIMENTO_PRATICANTE));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.NUTRICIONISTA,
				FORM_DIETA));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.NUTRICIONISTA,
				FORM_REFEICAO_PRATICANTE));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.NUTRICIONISTA,
				LISTAR_ALIMENTOS_PRATICANTE));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.NUTRICIONISTA,
				LISTAR_DIETAS));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.NUTRICIONISTA,
				LISTAR_ALIMENTOS_PRATICANTE));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.NUTRICIONISTA,
				FORM_CADASTRAR_PRATICANTE));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.NUTRICIONISTA,
				FORM_PRATICANTE_DIETA));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.NUTRICIONISTA,
				LISTAR_DIETAS_PRATICANTE));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.NUTRICIONISTA,
				LISTAR_PRATICANTES));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.NUTRICIONISTA,
				LISTAR_RESULTADOS_PRATICANTE));
		autorizacoes.add(new EstruturaAutorizacao(TipoContaEnum.NUTRICIONISTA,
				LISTAR_REFEICOES_PRATICANTE));

	}

	public boolean verificarVoltarPaginaLoginLogado(String pagina) {
		if (pagina.equals(PAGINA_INICIAL) || pagina.equals(LOGIN_USUARIO)
				|| pagina.equals(INDEX) 	|| pagina.equals(PAGINA_INICIAL_SEM_BARRA)) {
			return true;
		}
		return false;
	}

	public boolean verificarAutorizacao(TipoContaEnum conta, String pagina) {

		EstruturaAutorizacao aut = new EstruturaAutorizacao(conta, pagina);
		for (EstruturaAutorizacao estruturaAutorizacao : autorizacoes) {
			if (estruturaAutorizacao.equals(aut)) {
				return true;
			}
		}
		return false;
	}

	public boolean verificarPaginaDoSistema(String pag){
		for(String pagina : paginas){
			if(pagina.equals(pag)) return true;
		}
		return false;
	}
	
	public boolean verificarPaginasLivreDeAutorizacao(String pagina) {

		if (pagina.equals(PAGINA_INICIAL) || pagina.equals(HOME)
				|| pagina.equals(PAGINA_INICIAL_SEM_BARRA)
				|| pagina.equals(LOGIN_USUARIO) || pagina.equals(ERRO_ACESSO)
				|| pagina.equals(INDEX) || pagina.equals(FORM_USUARIO)
				|| pagina.equals(ERRO_ACESSO_LOGOUT)) {
			return true;
		}
		return false;
	}

	private class EstruturaAutorizacao {

		private TipoContaEnum tipoContaEnum;
		private String pagina;

		public EstruturaAutorizacao(TipoContaEnum tipoContaEnum, String pagina) {
			this.pagina = pagina;
			this.tipoContaEnum = tipoContaEnum;
		}

		@Override
		public boolean equals(Object obj) {
			EstruturaAutorizacao aut = (EstruturaAutorizacao) obj;
			if (tipoContaEnum == aut.getTipoContaEnum()
					&& pagina.equals(aut.getPagina())) {
				return true;
			}
			return false;
		}

		public TipoContaEnum getTipoContaEnum() {
			return tipoContaEnum;
		}

		public String getPagina() {
			return pagina;
		}

	}
}
