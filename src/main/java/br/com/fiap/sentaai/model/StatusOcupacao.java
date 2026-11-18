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

    public static StatusOcupacao calcular(double percentual){
        if(percentual < 60) return TEM_LUGAR;
        if(percentual <=90) return CHEIO;
        return LOTADO;
    }
}
