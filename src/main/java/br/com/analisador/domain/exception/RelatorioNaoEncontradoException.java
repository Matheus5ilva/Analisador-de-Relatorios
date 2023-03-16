package br.com.analisador.domain.exception;

public class RelatorioNaoEncontradoException extends EntidadeNaoEncontradaException{
    private static final long serialVersionUID = 1L;

    public RelatorioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public RelatorioNaoEncontradoException(Long relatorioId) {
        this(String.format("Não existe um cadastro de relatório com código %d", relatorioId));
    }
}
