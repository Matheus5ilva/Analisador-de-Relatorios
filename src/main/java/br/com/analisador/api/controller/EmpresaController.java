package br.com.analisador.api.controller;

import br.com.analisador.domain.model.Empresa;
import br.com.analisador.domain.repository.EmpresaRepository;
import br.com.analisador.domain.service.EmpresaService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public List<Empresa> listaEmpresa(){
        return empresaRepository.findAll();
    }

    @GetMapping(value = "/{empresaId}")
    public Empresa detalharEmpresa(@PathVariable Long empresaId){
        return empresaService.buscarOuFalhar(empresaId);
    }

    @PostMapping
    public Empresa criarEmpresa(@RequestBody Empresa empresa){
        return empresaService.salvar(empresa);
    }

    @PutMapping(value = "/editar/{empresaId}")
    public Empresa editarEmpresa(@PathVariable Long empresaId, @RequestBody Empresa empresa){
        Empresa empresaAtual = empresaService.buscarOuFalhar(empresaId);
        BeanUtils.copyProperties(empresa, empresaAtual, "id");
        return empresaService.atualizar(empresaAtual);
    }

    @DeleteMapping(value = "/deletar/{empresaId}")
    public void deletarEmpresa(@PathVariable Long empresaId){

        empresaService.excluir(empresaId);
    }
}
