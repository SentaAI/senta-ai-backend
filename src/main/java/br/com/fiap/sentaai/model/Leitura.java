package br.com.fiap.sentaai.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_SA_LEITURA")

public class Leitura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LEITURA")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_ONIBUS", nullable = false)
    private Onibus onibus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_PONTO", nullable = false)
    private Ponto ponto;

    @Column(name = "QT_ENTRADAS", nullable = false)
    private Integer entradas;

    @Column(name = "QT_SAIDAS", nullable = false)
    private Integer saidas;

    @Column(name = "TD_HORA", nullable = false)
    private LocalDateTime dataHora;

    public Leitura(){

    }

    public Leitura(Onibus onibus, Ponto ponto, Integer entradas, Integer saidas, LocalDateTime dataHora){
        this.onibus = onibus;
        this.ponto = ponto;
        this.entradas = entradas;
        this.saidas = saidas;
        this.dataHora = dataHora;
    }

    public int getSaldo(){
        return entradas - saidas;
    }

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public Onibus getOnibus(){return onibus;}
    public void setOnibus(Onibus onibus){this.onibus = onibus;}

    public Ponto getPonto(){return ponto;}
    public void setPonto(Ponto ponto){this.ponto = ponto;}

    public Integer getEntradas() {return entradas;}
    public void setEntradas(Integer entradas) {this.entradas = entradas;}

    public Integer getSaidas() {return saidas;}
    public void setSaidas(Integer saidas){this.saidas = saidas;}

    public LocalDateTime getDataHora(){return dataHora;}
    public void setDataHora(LocalDateTime dataHora){this.dataHora = dataHora;}


}
