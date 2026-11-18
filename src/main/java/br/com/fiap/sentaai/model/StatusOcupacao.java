package br.com.fiap.sentaai.model;

public enum StatusOcupacao {
    TEM_LUGAR("Tem lugar"),
    CHEIO("Cheio"),
    LOTADO("Lotado");

    private final String descricao;

    StatusOcupacao(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }

    //aqui sao faixas que definimos para o status que seria 60% tem lugar valido
    // de 60% a 90% esta cheio e quando esta acima disso nao tem lugar.
    public static StatusOcupacao calcular(double percentual){
        if(percentual < 60) return TEM_LUGAR;
        if(percentual <=90) return CHEIO;
        return LOTADO;
    }
}
