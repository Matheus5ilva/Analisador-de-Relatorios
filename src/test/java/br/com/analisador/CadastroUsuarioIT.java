package br.com.analisador;

import br.com.analisador.domain.model.Empresa;
import br.com.analisador.domain.model.Usuario;
import br.com.analisador.domain.model.enums.TipoPessoa;
import br.com.analisador.domain.model.enums.TipoUsuario;
import br.com.analisador.domain.repository.EmpresaRepository;
import br.com.analisador.domain.repository.UsuarioRepository;
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
class CadastroUsuarioIT {

	@LocalServerPort
	private int port;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EmpresaRepository empresaRepository;
	private Usuario novoUsuario;
	private int quantidadeUsuarioCadastrado;
	private String jsonCorretoUsuarioNovo;

	@BeforeEach
	public void setUp() {

		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/usuarios";

		jsonCorretoUsuarioNovo = ResourceUtils.getContentFromResource(
				"/json/usuario-nova.json");

		prepararDados();

	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarUsuarios() {
		RestAssured.given().accept(ContentType.JSON).when().get().then().statusCode(200);
	}


	@Test
	public void deveConter1Usuario_QuandoConsultarUsuario() {
		RestAssured.given().accept(ContentType.JSON).when().get().then().body("", Matchers.hasSize(quantidadeUsuarioCadastrado));
	}

	@Test
	public void deveRetornarStatus201_QuandoConsultarUsuarios() {
		RestAssured.given().body(jsonCorretoUsuarioNovo).contentType(ContentType.JSON).accept(ContentType.JSON)
				.when().post().then().statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deveRetornarEStatusCorreto_QuandoConsultarUsuarioExistente() {
		RestAssured.given().pathParam("usuarioId", novoUsuario.getId()).accept(ContentType.JSON).when().get("/{usuarioId}").then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveRetornarStatus404_QuandoConsultarUsuarioInexistente() {
		RestAssured.given().pathParam("usuarioId", 100).accept(ContentType.JSON).when().get("/{usuarioId}").then()
				.statusCode(HttpStatus.NOT_FOUND.value());

	}

	private void prepararDados() {

		Empresa novaEmpresa = new Empresa();
		novaEmpresa.setNome("Empresa Teste Unitario");
		novaEmpresa.setAtivo(true);
		novaEmpresa.setChaveApiKey("CHAVE_API_KEY");
		novaEmpresa.setTipoPessoa(TipoPessoa.EMPRESA);

		novaEmpresa = empresaRepository.save(novaEmpresa);


		novoUsuario = new Usuario();
		novoUsuario.setNome("Usuario Teste Unitario");
		novoUsuario.setTipoPessoa(TipoPessoa.USUARIO);
		novoUsuario.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
		novoUsuario.setEmpresa(novaEmpresa);
		novoUsuario.setEmail("email@email.com");
		novoUsuario.setAtivo(true);
		novoUsuario.setSenha("123");

		usuarioRepository.save(novoUsuario);

		quantidadeUsuarioCadastrado = (int) usuarioRepository.count();
	}
}
