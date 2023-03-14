package br.com.analisador.domain.service;

import br.com.analisador.domain.model.Empresa;
import br.com.analisador.domain.model.Usuario;
import br.com.analisador.domain.model.dto.ResultadoDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class PesquisaService {

    @Autowired
    private ResultadoService resultadoService;
    @Autowired
    private EmpresaService empresaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private OpenAIGPTService openAIGPTService;

    @Transactional
    public ResultadoDTO pesquisar(InputStream arquivo, String nomeRelatorio, Long usuarioId) {
        try {

            Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
            Empresa empresa = empresaService.buscarOuFalhar(usuario.getEmpresa().getId());

            String csv = ExcelParaCsv.convertXlsxToCsv(arquivo);
            String chave = empresa.getChaveApiKey();
            String response = openAIGPTService.generateText(chave,csv);
            ResultadoDTO resultadoDTO = new ResultadoDTO();
            resultadoDTO.setAnalise(response);

            return resultadoDTO;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
