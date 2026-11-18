package br.com.fiap.sentaai.controller;

import br.com.fiap.sentaai.bo.PontoBO;
import br.com.fiap.sentaai.dto.PontoRequest;
import br.com.fiap.sentaai.model.Ponto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pontos")
public class PontoController {

    private final PontoBO pontoBO;

    public PontoController(PontoBO pontoBO) {
        this.pontoBO = pontoBO;
    }

    @GetMapping
    public List<Ponto> listar() {
        return pontoBO.listar();
    }

    @GetMapping("/{id}")
    public Ponto buscar(@PathVariable Long id) {
        return pontoBO.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Ponto> criar(@RequestBody @Valid PontoRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pontoBO.criar(req));
    }

    @PutMapping("/{id}")
    public Ponto atualizar(@PathVariable Long id, @RequestBody @Valid PontoRequest req) {
        return pontoBO.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        pontoBO.excluir(id);
        return ResponseEntity.noContent().build();
    }
}