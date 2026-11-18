package br.com.fiap.sentaai.exception;


// aqui a gente usa esta classe quando a regra do nosso projeto é quebrada
// um exemplo: sair mais gente do que tem no onibus vira 400 erro.
public class RegraNegocioException extends RuntimeException {
    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }
}
