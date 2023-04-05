package br.com.analisador.api.controller;

import br.com.analisador.domain.model.dto.ResultadoDTO;
import br.com.analisador.domain.service.PesquisaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/pesquisas")
public class PesquisaController {

    private static final Logger logger = LoggerFactory.getLogger(PesquisaController.class);

    @Autowired
    private PesquisaService pesquisaService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResultadoDTO realizarConsulta(@RequestPart MultipartFile arquivo, @RequestParam("nomeRelatorio") String nome,
                                         @RequestParam("usuarioId") Long usuarioId) {

        try {
            logger.info("Iniciando realização de consulta");
            ResultadoDTO resultado = pesquisaService.pesquisar((InputStream) arquivo.getInputStream(), nome, usuarioId);
            logger.info("Consulta realizada com sucesso");
            return resultado;
        } catch (IOException e) {
            logger.error("Erro ao realizar consulta", e);
            throw new RuntimeException(e);
        }
    }

}
