package teste.controle.bo;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import modelo.entidade.usuario.Praticante;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import controle.service.usuario.PraticanteService;

public class AutenticacaoBOSelenium {

	private Praticante praticante;
	private PraticanteService praticanteService;

	@Test
	public void entrarSucesso() {
		praticante = new Praticante("teste@teste.com", "12", "lincoln",
				new Date());
		praticanteService = new PraticanteService();
		praticanteService.salvar(praticante);

		WebDriver driver = new FirefoxDriver();
		driver.get("http://localhost:8080/FitTraining/pages/usuario/loginUsuario.jsf");
		WebElement campoTextoEmail = driver.findElement(By
				.id("form:formulario:email"));
		WebElement campoTextoSenha = driver.findElement(By
				.id("form:formulario:senha"));
		WebElement botaoLogar = driver.findElement(By
				.id("form:formulario:botaoLogar"));
		campoTextoEmail.sendKeys("teste@teste.com");
		campoTextoSenha.sendKeys("12");
		botaoLogar.click();

		assertEquals("http://localhost:8080/FitTraining/pages/home/home.jsf",
				driver.getCurrentUrl());

		driver.quit();
		praticanteService.excluir(praticante);

	}

	@Test
	public void entrarErroDeEmailSenha() {
		praticante = new Praticante("teste@teste.com", "12", "lincoln",
				new Date());
		praticanteService = new PraticanteService();
		praticanteService.salvar(praticante);

		WebDriver driver = new FirefoxDriver();
		driver.get("http://localhost:8080/FitTraining/pages/usuario/loginUsuario.jsf");
		WebElement campoTextoEmail = driver.findElement(By
				.id("form:formulario:email"));
		WebElement campoTextoSenha = driver.findElement(By
				.id("form:formulario:senha"));
		WebElement botaoLogar = driver.findElement(By
				.id("form:formulario:botaoLogar"));
		campoTextoEmail.sendKeys("teste@teste.com");
		campoTextoSenha.sendKeys("13");
		botaoLogar.click();

		assertEquals(
				"http://localhost:8080/FitTraining/pages/usuario/loginUsuario.jsf",
				driver.getCurrentUrl());

		driver.quit();
		praticanteService.excluir(praticante);

	}

	@Test
	public void cadastrarUsuarioSucesso() {

		WebDriver driver = new FirefoxDriver();
		driver.get("http://localhost:8080/FitTraining/pages/usuario/loginUsuario.jsf");

		WebElement botaoCadastrar = driver.findElement(By
				.id("form:formulario:botaoCadastrar"));
		botaoCadastrar.click();

		WebElement campoTextoNome = driver.findElement(By.id("form:nome"));
		WebElement campoTextoEmail = driver.findElement(By.id("form:email"));
		WebElement campoTextoSenha = driver.findElement(By.id("form:senha"));
		WebElement campoData = driver.findElement(By.id("form:dataDayCell32"));
		WebElement botaoData = driver
				.findElement(By.id("form:dataPopupButton"));

		campoTextoEmail.sendKeys("teste@teste.com");
		campoTextoSenha.sendKeys("12");
		campoTextoNome.sendKeys("Lincoln");
		botaoData.click();
		campoData.click();

		WebElement botaoCadastrarUsuario = driver.findElement(By
				.id("form:btnCadastrar"));
		botaoCadastrarUsuario.click();

		assertEquals("http://localhost:8080/FitTraining/pages/home/home.jsf",
				driver.getCurrentUrl());

		driver.quit();

		praticanteService = new PraticanteService();
		praticante = praticanteService.buscarPorEmailSenha("teste@teste.com",
				"12");
		praticanteService.excluir(praticante);

	}

	@Test
	public void cadastrarUsuarioErroCamposNaoPreenchidos() {

		WebDriver driver = new FirefoxDriver();
		driver.get("http://localhost:8080/FitTraining/pages/usuario/loginUsuario.jsf");

		WebElement botaoCadastrar = driver.findElement(By
				.id("form:formulario:botaoCadastrar"));
		botaoCadastrar.click();

		WebElement campoTextoNome = driver.findElement(By.id("form:nome"));
		WebElement campoTextoEmail = driver.findElement(By.id("form:email"));
		WebElement campoTextoSenha = driver.findElement(By.id("form:senha"));

		campoTextoEmail.sendKeys("teste@teste.com");
		campoTextoSenha.sendKeys("12");
		campoTextoNome.sendKeys("Lincoln");

		WebElement botaoCadastrarUsuario = driver.findElement(By
				.id("form:btnCadastrar"));
		botaoCadastrarUsuario.click();

		assertEquals(
				"http://localhost:8080/FitTraining/pages/usuario/formUsuario.jsf",
				driver.getCurrentUrl());

		driver.quit();

	}

	@Test
	public void cadastrarUsuarioErroEmailExistente() {

		praticante = new Praticante("teste@teste.com", "12", "lincoln",
				new Date());
		praticanteService = new PraticanteService();
		praticanteService.salvar(praticante);

		WebDriver driver = new FirefoxDriver();
		driver.get("http://localhost:8080/FitTraining/pages/usuario/loginUsuario.jsf");

		WebElement botaoCadastrar = driver.findElement(By
				.id("form:formulario:botaoCadastrar"));
		botaoCadastrar.click();

		WebElement campoTextoNome = driver.findElement(By.id("form:nome"));
		WebElement campoTextoEmail = driver.findElement(By.id("form:email"));
		WebElement campoTextoSenha = driver.findElement(By.id("form:senha"));
		WebElement campoData = driver.findElement(By.id("form:dataDayCell32"));
		WebElement botaoData = driver
				.findElement(By.id("form:dataPopupButton"));

		campoTextoEmail.sendKeys("teste@teste.com");
		campoTextoSenha.sendKeys("12");
		campoTextoNome.sendKeys("Lincoln");
		botaoData.click();
		campoData.click();

		WebElement botaoCadastrarUsuario = driver.findElement(By
				.id("form:btnCadastrar"));
		botaoCadastrarUsuario.click();

		assertEquals(
				"http://localhost:8080/FitTraining/pages/usuario/formUsuario.jsf",
				driver.getCurrentUrl());

		driver.quit();

		praticanteService.excluir(praticante);

	}
}
