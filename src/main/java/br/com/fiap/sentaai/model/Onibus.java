package br.com.fiap.sentaai.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_SA_ONIBUS")

public class Onibus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ONIBUS")
    private Long id;

    @Column(name = "DS_PLACA", nullable = false, unique = true, length = 10)
    private String placa;

    @Column(name = "NR_CAPACIDADE", nullable = false)
    private Integer capacidade;

    @Column(name = "NR_OCUPACAO_ATUAL", nullable = false)
    private Integer ocupacaoAtual = 0;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_LINHA", nullable = false)
    private Linha linha;

    public Onibus(){

    }

    public Onibus(String placa, Integer capacidade, Linha linha){
        this.placa = placa;
        this.capacidade = capacidade;
        this.linha = linha;
    }

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public String getPlaca(){return placa;}

    public void setPlaca(String placa) {this.placa = placa;}

    public Integer getCapacidade(){return capacidade;}

    public void setCapacidade(Integer capacidade){this.capacidade = capacidade;}

    public Integer getOcupacaoAtual() {return ocupacaoAtual;}

    public void setOcupacaoAtual(Integer ocupacaoAtual){this.ocupacaoAtual = ocupacaoAtual;}

    public Linha getLinha(){return linha;}
    public void setLinha(Linha linha){this.linha = linha;}
}
