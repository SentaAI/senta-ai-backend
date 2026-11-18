package br.com.fiap.sentaai.model;

import jakarta.persistence.*;
@Entity
@Table(name = "TB_SA_LINHA")

public class Linha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LINHA")
    private Long id;

    @Column(name = "CD_LINHA", nullable = false, unique = true, length = 20)
    private String codigo;

    @Column(name = "NM_LINHA", nullable = false, length = 100)
    private String nome;

    public Linha(){

    }

    public Linha(String codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
    }

    public Long getId() {return id;}
    public void setId(Long id){this.id = id;}


    public String getCodigo() {return codigo;}
    public void setCodigo(String codigo){this.codigo = codigo;}

    public String getNome(){return nome;}
    public void setNome(String nome){this.nome = nome;}
}

