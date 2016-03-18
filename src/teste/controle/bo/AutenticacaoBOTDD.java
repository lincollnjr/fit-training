package teste.controle.bo;

import static org.junit.Assert.*;

import java.util.Date;

import modelo.entidade.usuario.Praticante;
import modelo.tipo.TipoContaEnum;

import org.junit.Test;

import controle.bo.AutenticacaoBO;
import controle.service.usuario.PraticanteService;

public class AutenticacaoBOTDD {

	private AutenticacaoBO autenticacaoBO;
	private Praticante praticante;
	private PraticanteService praticanteService;

	@Test
	public void entrarSucesso() {
		// primeiro inserir o usuario para teste
		praticante = new Praticante("teste@teste.com", "12", "lincoln",
				new Date());
		praticanteService = new PraticanteService();
		praticanteService.salvar(praticante);
		autenticacaoBO = new AutenticacaoBO();

		// setar as variaveis para execução
		autenticacaoBO.setEmail("teste@teste.com");
		autenticacaoBO.setSenha("12");
		autenticacaoBO.iniciar();

		// Realizar teste
		assertEquals("home", autenticacaoBO.entrar());

		// deletar usuario de teste
		praticanteService.excluir(praticante);

	}

	@Test
	public void entrarErroDeEmailSenha() {
		// primeiro inserir o usuario para teste
		praticante = new Praticante("teste@teste.com", "12", "lincoln",
				new Date());
		praticanteService = new PraticanteService();
		praticanteService.salvar(praticante);
		autenticacaoBO = new AutenticacaoBO();

		// setar as variaveis para execução
		autenticacaoBO.setEmail("teste@teste.com");
		autenticacaoBO.setSenha("14");
		autenticacaoBO.iniciar();

		// Realizar teste
		assertEquals("loginUsuario", autenticacaoBO.entrar());

		// deletar usuario de teste
		praticanteService.excluir(praticante);

	}

	@Test
	public void cadastrarUsuarioSucesso() {

		autenticacaoBO = new AutenticacaoBO();
		autenticacaoBO.iniciar();
		autenticacaoBO.setLogado(false);
		autenticacaoBO.setEmail("teste@teste.com");
		autenticacaoBO.setSenha("12");
		autenticacaoBO.setNome("lincoln");
		autenticacaoBO.setDataNascimento(new Date());
		autenticacaoBO.setTipoConta(TipoContaEnum.PRATICANTE);

		assertEquals("home", autenticacaoBO.cadastrarUsuario());

		// deletar usuario de teste
		praticanteService = new PraticanteService();
		praticante = praticanteService.buscarPorEmailSenha("teste@teste.com",
				"12");
		praticanteService.excluir(praticante);

	}

	@Test
	public void cadastrarUsuarioErroCamposNaoPreenchidos() {

		autenticacaoBO = new AutenticacaoBO();
		autenticacaoBO.iniciar();
		autenticacaoBO.setLogado(false);
		autenticacaoBO.setEmail("teste@teste.com");
		autenticacaoBO.setSenha("");
		autenticacaoBO.setNome("lincoln");
		autenticacaoBO.setDataNascimento(new Date());
		autenticacaoBO.setTipoConta(TipoContaEnum.PRATICANTE);

		assertEquals("", autenticacaoBO.cadastrarUsuario());

	}

	@Test
	public void cadastrarUsuarioErroEmailExistente() {

		// primeiro inserir o usuario para teste
		praticante = new Praticante("teste@teste.com", "12", "lincoln",
				new Date());
		praticanteService = new PraticanteService();
		praticanteService.salvar(praticante);

		autenticacaoBO = new AutenticacaoBO();
		autenticacaoBO.iniciar();
		autenticacaoBO.setLogado(false);
		autenticacaoBO.setEmail("teste@teste.com");
		autenticacaoBO.setSenha("");
		autenticacaoBO.setNome("lincoln");
		autenticacaoBO.setDataNascimento(new Date());
		autenticacaoBO.setTipoConta(TipoContaEnum.PRATICANTE);

		assertEquals("", autenticacaoBO.cadastrarUsuario());

		praticanteService.excluir(praticante);
		
	}
	
	@Test
	public void cadastrarUsuarioErroEmailForaDoPadrao() {

		autenticacaoBO = new AutenticacaoBO();
		autenticacaoBO.iniciar();
		autenticacaoBO.setLogado(false);
		autenticacaoBO.setEmail("teste@aa@aa@teste.com");
		autenticacaoBO.setSenha("");
		autenticacaoBO.setNome("lincoln");
		autenticacaoBO.setDataNascimento(new Date());
		autenticacaoBO.setTipoConta(TipoContaEnum.PRATICANTE);

		assertEquals("", autenticacaoBO.cadastrarUsuario());

		
		
	}
	
}
