package br.com.analisador.domain.service;

import br.com.analisador.domain.exception.EmpresaNaoEncontradoException;
import br.com.analisador.domain.exception.EntidadeEmUsoException;
import br.com.analisador.domain.model.Empresa;
import br.com.analisador.domain.repository.EmpresaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    private static final String MSG_EMPRESA_EM_USO = "Empresa de código %d não pode ser removida, pois está em uso";

    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional
    public Empresa salvar(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Transactional
    public void excluir(Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new EmpresaNaoEncontradoException(empresaId));

        try {
            empresaRepository.delete(empresa);
            empresaRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_EMPRESA_EM_USO, empresaId));
        }
    }

    public Empresa buscarOuFalhar(Long empresaId) {
        return empresaRepository.findById(empresaId)
                        .orElseThrow(() -> new EmpresaNaoEncontradoException(empresaId));
    }

    public boolean empresaExiste(Long empresaId) {
        return empresaRepository.existsById(empresaId);
    }
}
