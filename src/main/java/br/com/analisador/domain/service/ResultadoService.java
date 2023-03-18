package br.com.analisador.domain.service;

import br.com.analisador.domain.model.Resultado;
import br.com.analisador.domain.model.Usuario;
import br.com.analisador.domain.model.dto.ResultadoDTO;
import br.com.analisador.domain.repository.ResultadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultadoService {

    @Autowired
    private ResultadoRepository resultadoRepository;

    @Transactional
    public Resultado salvar(Resultado resultado) {
        return resultadoRepository.save(resultado);
    }


}
