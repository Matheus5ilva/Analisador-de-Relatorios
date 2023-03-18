package br.com.analisador.api.controller;

import br.com.analisador.domain.model.Empresa;
import br.com.analisador.domain.model.dto.RelatorioDTO;
import br.com.analisador.domain.service.EmpresaService;
import br.com.analisador.domain.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/relatorio")
public class RelatorioController {


    @Autowired
    private RelatorioService relatorioService;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping(value = "/{empresaId}")
    public RelatorioDTO index(@PathVariable Long empresaId){
        Empresa empresa = empresaService.buscarOuFalhar(empresaId);
        return relatorioService.paginaInicial(empresa);
    }
}
