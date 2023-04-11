package br.com.analisador.domain.service;

import br.com.analisador.domain.model.Resultado;
import br.com.analisador.domain.repository.ResultadoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ResultadoService {

    @Autowired
    private ResultadoRepository resultadoRepository;

    private final Logger logger = LoggerFactory.getLogger(ResultadoService.class);

    @Transactional
    public Resultado salvar(Resultado resultado) {
        logger.info("Salvando resultado");
        Resultado resultadoSalvo = resultadoRepository.save(resultado);
        logger.info("Resultado salvo com sucesso: {}", resultadoSalvo);
        return resultadoSalvo;
    }
}
