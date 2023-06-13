package br.com.analisador.api.controller;

import br.com.analisador.api.model.assembler.EmpresaDTOAssemblerDisassembler;
import br.com.analisador.api.model.dto.inputs.EmpresaInputDTO;
import br.com.analisador.domain.exception.EmpresaNaoEncontradoException;
import br.com.analisador.domain.exception.NegocioException;
import br.com.analisador.domain.model.Empresa;
import br.com.analisador.api.model.dto.EmpresaDTO;
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
@RequestMapping(value = "/api/empresas")
public class EmpresaController {

    private static final Logger logger = LoggerFactory.getLogger(EmpresaController.class);

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private EmpresaDTOAssemblerDisassembler empresaDTOAssemblerDisassembler;

    @GetMapping
    public List<EmpresaDTO> listaEmpresa(){
        logger.info("Buscando todas as empresas cadastradas.");
        return empresaDTOAssemblerDisassembler.toCollectionDTO(empresaRepository.findAll());
    }

    @GetMapping(value = "/{empresaId}")
    public EmpresaDTO detalharEmpresa(@PathVariable Long empresaId){
        logger.info("Buscando empresa com o ID {}.", empresaId);
        Empresa empresa = empresaService.buscarOuFalhar(empresaId);
        return empresaDTOAssemblerDisassembler.toDTO(empresa);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmpresaDTO criarEmpresa(@RequestBody @Valid EmpresaInputDTO empresaDto){
        try{
            logger.info("Criando nova empresa com os dados: {}", empresaDto);
            Empresa empresa = empresaDTOAssemblerDisassembler.toDomainObject(empresaDto);

            return empresaDTOAssemblerDisassembler.toDTO(empresaService.salvar(empresa));
        }catch (EmpresaNaoEncontradoException empresaException){
            throw new NegocioException(empresaException.getMessage(), empresaException);
        }
    }

    @PutMapping(value = "/editar/{empresaId}")
    public EmpresaDTO editarEmpresa(@PathVariable Long empresaId, @RequestBody @Valid EmpresaInputDTO empresaDto){
        try{
            logger.info("Atualizando empresa com o ID {} com os dados: {}", empresaId, empresaDto);
            Empresa empresa = empresaDTOAssemblerDisassembler.toDomainObject(empresaDto);
            Empresa empresaAtual = empresaService.buscarOuFalhar(empresaId);
            BeanUtils.copyProperties(empresa, empresaAtual, "id", "dataCadastro");
            return empresaDTOAssemblerDisassembler.toDTO(empresaService.salvar(empresaAtual));
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
