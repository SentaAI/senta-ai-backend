package br.com.fiap.sentaai.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_SA_PONTO")

public class Ponto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PONTO")
    private Long id;

    @Column(name = "NM_PONTO", nullable = false, length = 100)
    private String nome;

    @Column(name = "DS_ENDERECO", nullable = false, length = 200)
    private String endereco;

    public Ponto(){

    }

    public Ponto(String nome, String endereco){
        this.nome = nome;
        this.endereco = endereco;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}

