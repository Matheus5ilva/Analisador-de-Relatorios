package br.com.analisador.domain.service;

import br.com.analisador.domain.model.Empresa;
import br.com.analisador.domain.model.Resultado;
import br.com.analisador.domain.model.dto.RelatorioDTO;
import br.com.analisador.domain.repository.ResultadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RelatorioService {

    @Autowired
    private ResultadoRepository resultadoRepository;

    @Autowired
    private EmpresaService empresaService;

    @Transactional
    public RelatorioDTO paginaInicial(Empresa empresa){

        RelatorioDTO relatorio = new RelatorioDTO();
        LocalDateTime hoje = LocalDateTime.now();
        LocalDateTime mesAnterior = hoje.minusDays(30);

        List<Resultado> listaResultados = resultadoRepository.relatorioInicial(empresa.getId(), mesAnterior, hoje);
        List<String> nomeRelatorio = new ArrayList<String>();
        for(Resultado relatorioAux : listaResultados){
            nomeRelatorio.add(relatorioAux.getNomeRelatorio());
        }
        relatorio.setNomeRelatorio(this.encontrarStringMaisRepetida(nomeRelatorio));
        relatorio.setNumeroAnalise((long) listaResultados.size());

        return relatorio;
    }

    private String encontrarStringMaisRepetida(List<String> lista) {
        HashMap<String, Integer> contador = new HashMap<String, Integer>();

        // contagem das ocorrências de cada string
        for (String s : lista) {
            if (contador.containsKey(s)) {
                contador.put(s, contador.get(s) + 1);
            } else {
                contador.put(s, 1);
            }
        }

        // encontrar a chave com maior valor
        String maisRepetida = null;
        int maxContagem = 0;
        for (Map.Entry<String, Integer> entrada : contador.entrySet()) {
            if (entrada.getValue() > maxContagem) {
                maisRepetida = entrada.getKey();
                maxContagem = entrada.getValue();
            }
        }

        return maisRepetida;
    }
}
