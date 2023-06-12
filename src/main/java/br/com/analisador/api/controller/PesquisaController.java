package br.com.analisador.api.controller;

import br.com.analisador.api.model.assembler.ResultadoDTOAssemblerDisassembler;
import br.com.analisador.api.model.dto.ResultadoDTO;
import br.com.analisador.core.utils.GeradorPDFUtil;
import br.com.analisador.domain.model.Resultado;
import br.com.analisador.domain.service.PesquisaService;
import br.com.analisador.domain.service.ResultadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/pesquisas")
public class PesquisaController {

    private static final Logger logger = LoggerFactory.getLogger(PesquisaController.class);

    @Autowired
    private PesquisaService pesquisaService;

    @Autowired
    private ResultadoService resultadoService;

    @Autowired
    private ResultadoDTOAssemblerDisassembler resultadoDTOAssemblerDisassembler;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResultadoDTO realizarConsulta(@RequestPart MultipartFile arquivo, @RequestParam("nomeRelatorio") String nome,
                                         @RequestParam("usuarioId") Long usuarioId) {

        try {
            logger.info("Iniciando realização de consulta");
            Resultado resultado = pesquisaService.pesquisar((InputStream) arquivo.getInputStream(), nome, usuarioId);
            logger.info("Consulta realizada com sucesso");
            return resultadoDTOAssemblerDisassembler.toDTO(resultado);
        } catch (IOException e) {
            logger.error("Erro ao realizar consulta", e);
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/gerar-pdf/{pesquisaId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> gerarPdf(@PathVariable Long pesquisaId) throws IOException {

        String caminhoDoArquivo = pesquisaService.gerarPdf(pesquisaId);

        Path path = Path.of(caminhoDoArquivo);
        Resource resource = new UrlResource(path.toUri());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "Relatorio.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

}
