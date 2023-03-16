package br.com.analisador.api.controller;

import br.com.analisador.domain.exception.EmpresaNaoEncontradoException;
import br.com.analisador.domain.exception.NegocioException;
import br.com.analisador.domain.model.Empresa;
import br.com.analisador.domain.repository.EmpresaRepository;
import br.com.analisador.domain.service.EmpresaService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Empresa criarEmpresa(@RequestBody Empresa empresa){
        try{
            return empresaService.salvar(empresa);
        }catch (EmpresaNaoEncontradoException empresaException){
            throw new NegocioException(empresaException.getMessage(), empresaException);
        }

    }

    @PutMapping(value = "/editar/{empresaId}")
    public Empresa editarEmpresa(@PathVariable Long empresaId, @RequestBody Empresa empresa){
        try{
            Empresa empresaAtual = empresaService.buscarOuFalhar(empresaId);
            BeanUtils.copyProperties(empresa, empresaAtual, "id");
            return empresaService.salvar(empresaAtual);
        }catch (EmpresaNaoEncontradoException empresaException) {
            throw new NegocioException(empresaException.getMessage(), empresaException);
        }
    }

    @DeleteMapping(value = "/deletar/{empresaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarEmpresa(@PathVariable Long empresaId){
        empresaService.excluir(empresaId);
    }
}
