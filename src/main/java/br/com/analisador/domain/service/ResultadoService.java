package br.com.analisador.domain.service;

import br.com.analisador.domain.model.Resultado;
import br.com.analisador.domain.repository.ResultadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ResultadoService {

    @Autowired
    private ResultadoRepository resultadoRepository;

    @Transactional
    public Resultado salvar(Resultado resultado) {
        return resultadoRepository.save(resultado);
    }


}
