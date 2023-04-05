package br.com.analisador.api.controller;

import br.com.analisador.domain.exception.EmpresaNaoEncontradoException;
import br.com.analisador.domain.exception.NegocioException;
import br.com.analisador.domain.model.Empresa;
import br.com.analisador.domain.model.dto.EmpresaDTO;
import br.com.analisador.domain.repository.EmpresaRepository;
import br.com.analisador.domain.service.EmpresaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/empresas")
public class EmpresaController {

    private static final Logger logger = LoggerFactory.getLogger(EmpresaController.class);

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public List<Empresa> listaEmpresa(){
        logger.info("Buscando todas as empresas cadastradas.");
        return empresaRepository.findAll();
    }

    @GetMapping(value = "/{empresaId}")
    public Empresa detalharEmpresa(@PathVariable Long empresaId){
        logger.info("Buscando empresa com o ID {}.", empresaId);
        return empresaService.buscarOuFalhar(empresaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empresa criarEmpresa(@RequestBody @Valid EmpresaDTO empresaDto){
        try{
            logger.info("Criando nova empresa com os dados: {}", empresaDto);
            return empresaService.salvar(empresaDto);
        }catch (EmpresaNaoEncontradoException empresaException){
            throw new NegocioException(empresaException.getMessage(), empresaException);
        }
    }

    @PutMapping(value = "/editar/{empresaId}")
    public Empresa editarEmpresa(@PathVariable Long empresaId, @RequestBody @Valid EmpresaDTO empresaDto){
        try{
            logger.info("Atualizando empresa com o ID {} com os dados: {}", empresaId, empresaDto);
            Empresa empresaAtual = empresaService.buscarOuFalhar(empresaId);
            BeanUtils.copyProperties(empresaDto, empresaAtual, "id");
            return empresaService.atualizar(empresaAtual);
        }catch (EmpresaNaoEncontradoException empresaException) {
            throw new NegocioException(empresaException.getMessage(), empresaException);
        }
    }

    @DeleteMapping(value = "/deletar/{empresaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarEmpresa(@PathVariable Long empresaId){
        logger.info("Excluindo empresa com o ID {}.", empresaId);
        empresaService.excluir(empresaId);
    }
}
