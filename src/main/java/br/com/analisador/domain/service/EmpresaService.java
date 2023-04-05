package br.com.analisador.domain.service;

import br.com.analisador.domain.exception.EmpresaNaoEncontradoException;
import br.com.analisador.domain.exception.EntidadeEmUsoException;
import br.com.analisador.domain.exception.UsuarioNaoEncontradoException;
import br.com.analisador.domain.model.Empresa;
import br.com.analisador.domain.model.Usuario;
import br.com.analisador.domain.model.dto.EmpresaDTO;
import br.com.analisador.domain.repository.EmpresaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EmpresaService {

    private static final String MSG_EMPRESA_EM_USO = "Empresa de código %d não pode ser removida, pois está em uso";

    private final Logger logger = LoggerFactory.getLogger(EmpresaService.class);

    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional
    public Empresa salvar(EmpresaDTO empresaDto) {
        Empresa empresa = empresaDto.transformaEmObjeto();
        logger.debug("Salvando empresa: {}", empresa);
        return empresaRepository.save(empresa);
    }

    @Transactional
    public Empresa atualizar(Empresa empresa) {
        logger.debug("Atualizando empresa: {}", empresa);
        return empresaRepository.save(empresa);
    }

    @Transactional
    public void excluir(Long empresaId) {
        logger.debug("Excluindo empresa de ID: {}", empresaId);
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new EmpresaNaoEncontradoException(empresaId));

        try {
            empresaRepository.delete(empresa);
            empresaRepository.flush();
            logger.debug("Empresa de ID: {} excluída com sucesso", empresaId);
        } catch (DataIntegrityViolationException e) {
            String errorMsg = String.format(MSG_EMPRESA_EM_USO, empresaId);
            logger.error(errorMsg, e);
            throw new EntidadeEmUsoException(errorMsg);
        }
    }

    public Empresa buscarOuFalhar(Long empresaId) {
        logger.info("Buscando empresa de id: {}", empresaId);
        return empresaRepository.findById(empresaId)
                .orElseThrow(() -> {
                    logger.error("Empresa de id {} não encontrado", empresaId);
                    return new EmpresaNaoEncontradoException(empresaId);
                });
    }

    public boolean empresaExiste(Long empresaId) {
        logger.debug("Verificando existência de empresa de ID: {}", empresaId);
        return empresaRepository.existsById(empresaId);
    }
}
