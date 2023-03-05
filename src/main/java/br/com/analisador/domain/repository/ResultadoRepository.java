package br.com.analisador.domain.repository;

import br.com.analisador.domain.model.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultadoRepository extends JpaRepository<Resultado, Long> {
}
