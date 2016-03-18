package controle.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.entidade.usuario.Nutricionista;
import modelo.entidade.usuario.Personal;
import modelo.entidade.usuario.Praticante;
import modelo.entidade.usuario.Usuario;
import modelo.tipo.TipoContaEnum;
import modelo.tipo.TipoObjetivoEnum;
import modelo.tipo.TipoOperacaoEnum;
import controle.service.usuario.NutricionistaService;
import controle.service.usuario.PersonalService;
import controle.service.usuario.PraticanteService;
import controle.util.MensagemUtil;

public class AutenticacaoBO extends MensagemUtil {

	private boolean logado;

	private TipoOperacaoEnum operacao;

	private String email;
	private String senha;
	private String nome;
	private Date dataNascimento;
	private TipoObjetivoEnum objetivo;
	private TipoContaEnum tipoConta;

	private Usuario usuario;

	private List<TipoContaEnum> tiposConta;
	private List<TipoObjetivoEnum> objetivos;

	private PraticanteService praticanteService;
	private NutricionistaService nutricionistaService;
	private PersonalService personalService;

	private boolean flagPraticante;

	private final String LOGIN_USUARIO = "loginUsuario";
	private final String FORM_USUARIO = "formUsuario";

	public void iniciar() {
		usuario = new Usuario();
		logado = false;
		flagPraticante = false;

		objetivos = TipoObjetivoEnum.getLista();

		tiposConta = new ArrayList<TipoContaEnum>();
		tiposConta.add(TipoContaEnum.PRATICANTE);
		tiposConta.add(TipoContaEnum.PERSONAL);
		tiposConta.add(TipoContaEnum.NUTRICIONISTA);

		praticanteService = new PraticanteService();
		personalService = new PersonalService();
		nutricionistaService = new NutricionistaService();
	}

	public String entrar() {

		if (!email.equals("") && !senha.equals("")) {
			Praticante praticante;
			praticante = praticanteService.buscarPorEmailSenha(email, senha);

			if (!validarEmail(email)) {
				adicionarMensagemErro(MENSAGEM_EMAIL_SENHA_INCORRETOS);
				return ERRO;
			}

			if (praticante != null) {
				nome = praticante.getNome();
				objetivo = praticante.getObjetivoAtual();
				dataNascimento = praticante.getDataNascimento();
				logado = true;
				usuario.setPraticante(praticante);
				usuario.setContaLogada(TipoContaEnum.PRATICANTE);
				flagPraticante = true;
				desabilitarMensagem();
				return HOME;
			}

			Personal personal;
			personal = personalService.buscarPorEmailSenha(email, senha);

			if (personal != null) {
				nome = personal.getNome();
				logado = true;
				usuario.setPersonal(personal);
				dataNascimento = personal.getDataNascimento();
				usuario.setContaLogada(TipoContaEnum.PERSONAL);
				flagPraticante = false;
				desabilitarMensagem();
				return HOME;
			}

			Nutricionista nutricionista;
			nutricionista = nutricionistaService.buscarPorEmailSenha(email,
					senha);

			if (nutricionista != null) {
				nome = nutricionista.getNome();
				logado = true;
				dataNascimento = nutricionista.getDataNascimento();
				usuario.setNutricionista(nutricionista);
				usuario.setContaLogada(TipoContaEnum.NUTRICIONISTA);
				desabilitarMensagem();
				flagPraticante = false;
				return HOME;
			}

		}

		adicionarMensagemErro(MENSAGEM_EMAIL_SENHA_INCORRETOS);
		return LOGIN_USUARIO;
	}

	public boolean validarEmail(String email) {
		boolean isEmailIdValid = false;
		if (email != null && email.length() > 0) {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expression,
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				isEmailIdValid = true;
			}
		}
		return isEmailIdValid;
	}

	public String sair() {
		desabilitarMensagem();
		usuario = new Usuario();
		logado = false;
		flagPraticante = false;
		limparCampos();
		return LOGIN_USUARIO;
	}

	public String cadastrar() {
		desabilitarMensagem();
		return FORM_USUARIO;
	}

	public boolean verificarEmailUnico(String email) {
		if (praticanteService.buscarPorEmail(email) == null
				&& personalService.buscarPorEmail(email) == null
				&& nutricionistaService.buscarPorEmail(email) == null) {
			return true;
		}
		return false;
	}

	public String cadastrarUsuario() {
		if (logado) {
			tipoConta = usuario.getContaLogada();
			if (nome.equals("") || email.equals("") || senha.equals("")
					|| dataNascimento == null) {
				adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);

				return ERRO;
			}

		} else {
			if (tipoConta == null || nome.equals("") || email.equals("")
					|| senha.equals("") || dataNascimento == null) {
				adicionarMensagemErro(MENSAGEM_CAMPOS_VAZIOS);
				return ERRO;
			}

			if (!verificarEmailUnico(email)) {
				adicionarMensagemErro(MENSAGEM_EMAIL_EXISTENTE);

				return ERRO;
			}
		}

		if (!validarEmail(email)) {
			adicionarMensagemErro(MENSAGEM_EMAIL_SENHA_INCORRETOS);
			return ERRO;
		}

		if (tipoConta == TipoContaEnum.PRATICANTE) {
			Praticante praticante;
			if (logado == true && usuario.getPraticante().getId() != 0) {
				praticante = usuario.getPraticante();
			} else {
				praticante = new Praticante(email, senha, nome, dataNascimento);
			}

			if (objetivo != null)
				praticante.setObjetivoAtual(objetivo);

			praticanteService.salvar(praticante);
			usuario.setPraticante(praticante);
			usuario.setContaLogada(TipoContaEnum.PRATICANTE);
			logado = true;
			flagPraticante = true;
			desabilitarMensagem();
			return HOME;
		}

		if (tipoConta == TipoContaEnum.PERSONAL) {
			Personal personal;
			if (logado == true && usuario.getPersonal().getId() != 0) {
				personal = usuario.getPersonal();
			} else {
				personal = new Personal(email, senha, nome, dataNascimento);
			}

			personalService.salvar(personal);
			usuario.setPersonal(personal);
			usuario.setContaLogada(TipoContaEnum.PERSONAL);
			logado = true;
			flagPraticante = false;
			desabilitarMensagem();
			return HOME;
		}

		if (tipoConta == TipoContaEnum.NUTRICIONISTA) {
			Nutricionista nutricionista;
			if (logado == true && usuario.getNutricionista().getId() != 0) {
				nutricionista = usuario.getNutricionista();
			} else {
				nutricionista = new Nutricionista(email, senha, nome,
						dataNascimento);
			}

			nutricionistaService.salvar(nutricionista);
			usuario.setNutricionista(nutricionista);
			usuario.setContaLogada(TipoContaEnum.NUTRICIONISTA);
			logado = true;
			flagPraticante = false;
			desabilitarMensagem();
			return HOME;
		}
		return ERRO;
	}

	public void limparCampos() {
		nome = "";
		email = "";
		senha = "";
		tipoConta = null;
		objetivo = null;
		dataNascimento = null;
	}

	public String voltarFormUsuario() {
		desabilitarMensagem();
		if (logado)
			return HOME;
		return LOGIN_USUARIO;
	}

	public String desabilitarNutricionista() {
		usuario.getPraticante().setNutricionista(null);
		praticanteService.salvar(usuario.getPraticante());
		return HOME;
	}

	public String desabilitarPersonal() {
		usuario.getPraticante().setPersonal(null);
		praticanteService.salvar(usuario.getPraticante());
		return HOME;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public TipoOperacaoEnum getOperacao() {
		return operacao;
	}

	public void setOperacao(TipoOperacaoEnum operacao) {
		this.operacao = operacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public TipoObjetivoEnum getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(TipoObjetivoEnum objetivo) {
		this.objetivo = objetivo;
	}

	public TipoContaEnum getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoContaEnum tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<TipoContaEnum> getTiposConta() {
		return tiposConta;
	}

	public void setTiposConta(List<TipoContaEnum> tiposConta) {
		this.tiposConta = tiposConta;
	}

	public List<TipoObjetivoEnum> getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(List<TipoObjetivoEnum> objetivos) {
		this.objetivos = objetivos;
	}

	public PraticanteService getPraticanteService() {
		return praticanteService;
	}

	public void setPraticanteService(PraticanteService praticanteService) {
		this.praticanteService = praticanteService;
	}

	public NutricionistaService getNutricionistaService() {
		return nutricionistaService;
	}

	public void setNutricionistaService(
			NutricionistaService nutricionistaService) {
		this.nutricionistaService = nutricionistaService;
	}

	public PersonalService getPersonalService() {
		return personalService;
	}

	public void setPersonalService(PersonalService personalService) {
		this.personalService = personalService;
	}

	public boolean isFlagPraticante() {
		return flagPraticante;
	}

	public void setFlagPraticante(boolean flagPraticante) {
		this.flagPraticante = flagPraticante;
	}

	public String getLOGIN_USUARIO() {
		return LOGIN_USUARIO;
	}

	public String getFORM_USUARIO() {
		return FORM_USUARIO;
	}

}
