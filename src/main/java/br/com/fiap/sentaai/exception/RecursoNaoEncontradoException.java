package br.com.fiap.sentaai.exception;

// a gente usa esta classe quando buscamos algo pelo id e não é achado.
public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String recurso, Long id) {
        super(recurso + " com id " + id + " não encontrado(a)");
    }
}
