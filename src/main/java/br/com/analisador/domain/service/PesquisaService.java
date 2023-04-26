package br.com.analisador.domain.service;

import br.com.analisador.domain.model.Empresa;
import br.com.analisador.domain.model.Resultado;
import br.com.analisador.domain.model.Usuario;
import br.com.analisador.api.model.dto.ResultadoDTO;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;

@Service
public class PesquisaService {

    private static final Logger logger = LoggerFactory.getLogger(PesquisaService.class);

    @Autowired
    private ResultadoService resultadoService;
    @Autowired
    private EmpresaService empresaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private OpenAIGPTService openAIGPTService;

    @Transactional
    public Resultado pesquisar(InputStream arquivo, String nomeRelatorio, Long usuarioId) {
        try {
            Resultado resultado = new Resultado();

            Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
            Empresa empresa = empresaService.buscarOuFalhar(usuario.getEmpresa().getId());

            String csv = ExcelParaCsv.convertXlsxToCsv(arquivo);
            String chave = empresa.getChaveApiKey();

            String response = openAIGPTService.generateText(chave,csv);

            resultado.setNomeRelatorio(nomeRelatorio);
            resultado.setEmpresa(empresa);
            resultado.setUsuario(usuario);

            resultado.setAnalise(this.gerarResposta(response, true));
            resultado.setNumeroToken(Integer.parseInt(this.gerarResposta(response, false)));


            return resultadoService.salvar(resultado);
        } catch (IOException e) {
            logger.error("Erro na pesquisa", e);
            throw new RuntimeException(e);
        }

    }

    private String gerarResposta(String response, @NotNull Boolean ehAnalise){
        JSONObject jsonObject = new JSONObject(response);
        if(ehAnalise){
            return jsonObject.getJSONArray("choices").getJSONObject(0).getString("text");
        }
        return String.valueOf(jsonObject.getJSONObject("usage").getInt("total_tokens"));
    }

}
