package br.com.analisador.domain.repository;

import br.com.analisador.domain.model.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Long>, JpaSpecificationExecutor<Resultado> {
    @Query("from Resultado where empresa.id = :empresa and dataHoraAnalise between :dataInicio and :dataFim")
    List<Resultado> relatorioInicial(@Param("empresa") Long empresa, @Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);
}