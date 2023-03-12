package br.com.analisador.domain.service;

import br.com.analisador.domain.model.Empresa;
import br.com.analisador.domain.repository.EmpresaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional
    public Empresa salvar(Empresa empresa) {
        return empresaRepository.save(empresa);

    }

    @Transactional
    public Empresa atualizar(Empresa empresa) {
        return empresaRepository.save(empresa);

    }

    @Transactional
    public void excluir(Long empresaId) {
        try {
            empresaRepository.deleteById(empresaId);
            empresaRepository.flush();

        } catch (RuntimeException e) {
            throw new RuntimeException(
                    String.format("Não existe um cadastro de empresa com codigo %d", empresaId));

        }
    }

    public Empresa buscarOuFalhar(Long empresaId) {
        return empresaRepository.findById(empresaId).orElseThrow(() -> new RuntimeException(
                String.format("Não existe um cadastro de empresa com codigo %d", empresaId)));
    }
}
