package br.com.fiap.sentaai.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_SA_LINHA_PONTO",
        uniqueConstraints = @UniqueConstraint(columnNames = {"ID_LINHA", "ID_PONTO"}))
public class LinhaPonto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LINHA_PONTO")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_LINHA", nullable = false)
    private Linha linha;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_PONTO", nullable = false)
    private Ponto ponto;

    @Column(name = "NR_ORDEM", nullable = false)
    private Integer ordem;

    public LinhaPonto() {
    }

    public LinhaPonto(Linha linha, Ponto ponto, Integer ordem) {
        this.linha = linha;
        this.ponto = ponto;
        this.ordem = ordem;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Linha getLinha() { return linha; }
    public void setLinha(Linha linha) { this.linha = linha; }

    public Ponto getPonto() { return ponto; }
    public void setPonto(Ponto ponto) { this.ponto = ponto; }

    public Integer getOrdem() { return ordem; }
    public void setOrdem(Integer ordem) { this.ordem = ordem; }
}
