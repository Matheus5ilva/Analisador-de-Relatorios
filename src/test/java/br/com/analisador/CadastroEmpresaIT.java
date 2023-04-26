package br.com.analisador;

import br.com.analisador.domain.model.Empresa;
import br.com.analisador.domain.model.enums.TipoPessoa;
import br.com.analisador.domain.repository.EmpresaRepository;
import br.com.analisador.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CadastroEmpresaIT {

	@LocalServerPort
	private int port;

	@Autowired
	private EmpresaRepository empresaRepository;

	private Empresa novaEmpresa;
	private int quantidadeEmpresaCadastrada;
	private String jsonCorretoEmpresaNova;

	@BeforeEach
	public void setUp() {

		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/empresas";

		jsonCorretoEmpresaNova = ResourceUtils.getContentFromResource(
				"/json/empresa-nova.json");

		prepararDados();

	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarEmpresas() {
		RestAssured.given().accept(ContentType.JSON).when().get().then().statusCode(200);
	}


	@Test
	public void deveConter1Empresa_QuandoConsultarEmpresas() {
		RestAssured.given().accept(ContentType.JSON).when().get().then().body("", Matchers.hasSize(quantidadeEmpresaCadastrada));
	}

	@Test
	public void deveRetornarStatus201_QuandoConsultarEmpresas() {
		RestAssured.given().body(jsonCorretoEmpresaNova).contentType(ContentType.JSON).accept(ContentType.JSON)
				.when().post().then().statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deveRetornarEStatusCorreto_QuandoConsultarEmpresaExistente() {
		RestAssured.given().pathParam("empresaId", novaEmpresa.getId()).accept(ContentType.JSON).when().get("/{empresaId}").then()
				.statusCode(HttpStatus.OK.value()).body("nome", equalTo("Empresa Teste Unitario"));
	}

	@Test
	public void deveRetornarStatus404_QuandoConsultarEmpresaInexistente() {
		RestAssured.given().pathParam("empresaId", 100).accept(ContentType.JSON).when().get("/{empresaId}").then()
				.statusCode(HttpStatus.NOT_FOUND.value());

	}

	private void prepararDados() {
		novaEmpresa = new Empresa();
		novaEmpresa.setNome("Empresa Teste Unitario");
		novaEmpresa.setAtivo(true);
		novaEmpresa.setChaveApiKey("CHAVE_API_KEY");
		novaEmpresa.setTipoPessoa(TipoPessoa.EMPRESA);

		empresaRepository.save(novaEmpresa);

		quantidadeEmpresaCadastrada = (int) empresaRepository.count();
	}
}
