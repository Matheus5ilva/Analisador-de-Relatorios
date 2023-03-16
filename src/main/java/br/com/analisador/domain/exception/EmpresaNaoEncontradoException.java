package br.com.analisador.domain.exception;

public class EmpresaNaoEncontradoException extends EntidadeNaoEncontradaException{
    private static final long serialVersionUID = 1L;

    public EmpresaNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public EmpresaNaoEncontradoException(Long empresaId) {
        this(String.format("Não existe um cadastro de empresa com código %d", empresaId));
    }
}
