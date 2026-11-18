package br.com.fiap.sentaai.controller;

import br.com.fiap.sentaai.bo.LinhaBO;
import br.com.fiap.sentaai.bo.LinhaPontoBO;
import br.com.fiap.sentaai.bo.OnibusBO;
import br.com.fiap.sentaai.dto.LinhaRequest;
import br.com.fiap.sentaai.dto.OcupacaoResponse;
import br.com.fiap.sentaai.model.Linha;
import br.com.fiap.sentaai.model.LinhaPonto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/linhas")
public class LinhaController {

    private final LinhaBO linhaBO;
    private final OnibusBO onibusBO;
    private final LinhaPontoBO linhaPontoBO;

    public LinhaController(LinhaBO linhaBO, OnibusBO onibusBO, LinhaPontoBO linhaPontoBO) {
        this.linhaBO = linhaBO;
        this.onibusBO = onibusBO;
        this.linhaPontoBO = linhaPontoBO;
    }

    @GetMapping
    public List<Linha> listar() {
        return linhaBO.listar();
    }

    @GetMapping("/{id}")
    public Linha buscar(@PathVariable Long id) {
        return linhaBO.buscarPorId(id);
    }

    // aqui e referente ao APP a lotaçao de todos os onibus
    @GetMapping("/{id}/ocupacao")
    public List<OcupacaoResponse> ocupacao(@PathVariable Long id) {
        return onibusBO.ocupacaoPorLinha(id);
    }

    // Trajeto da linha
    @GetMapping("/{id}/pontos")
    public List<LinhaPonto> pontos(@PathVariable Long id) {
        return linhaPontoBO.listarPorLinha(id);
    }

    @PostMapping
    public ResponseEntity<Linha> criar(@RequestBody @Valid LinhaRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(linhaBO.criar(req));
    }

    @PutMapping("/{id}")
    public Linha atualizar(@PathVariable Long id, @RequestBody @Valid LinhaRequest req) {
        return linhaBO.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        linhaBO.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
