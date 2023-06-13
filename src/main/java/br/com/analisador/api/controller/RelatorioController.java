package br.com.analisador.api.controller;

import br.com.analisador.domain.model.Empresa;
import br.com.analisador.api.model.dto.RelatorioDTO;
import br.com.analisador.domain.service.EmpresaService;
import br.com.analisador.domain.service.RelatorioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/relatorio")
public class RelatorioController {

    private static final Logger logger = LoggerFactory.getLogger(RelatorioController.class);

    @Autowired
    private RelatorioService relatorioService;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping(value = "/{empresaId}")
    public RelatorioDTO index(@PathVariable @Valid Long empresaId){
        logger.info("Buscando relatório para a empresa com id: {}", empresaId);
        Empresa empresa = empresaService.buscarOuFalhar(empresaId);
        return relatorioService.paginaInicial(empresa);
    }
}
