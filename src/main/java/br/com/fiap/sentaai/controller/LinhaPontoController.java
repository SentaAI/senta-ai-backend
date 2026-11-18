package br.com.fiap.sentaai.controller;

import br.com.fiap.sentaai.bo.LinhaPontoBO;
import br.com.fiap.sentaai.dto.LinhaPontoRequest;
import br.com.fiap.sentaai.model.LinhaPonto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/linha-pontos")
public class LinhaPontoController {

    private final LinhaPontoBO linhaPontoBO;

    public LinhaPontoController(LinhaPontoBO linhaPontoBO) {
        this.linhaPontoBO = linhaPontoBO;
    }

    @GetMapping
    public List<LinhaPonto> listar() {
        return linhaPontoBO.listar();
    }

    @GetMapping("/{id}")
    public LinhaPonto buscar(@PathVariable Long id) {
        return linhaPontoBO.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<LinhaPonto> criar(@RequestBody @Valid LinhaPontoRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(linhaPontoBO.criar(req));
    }

    @PutMapping("/{id}")
    public LinhaPonto atualizar(@PathVariable Long id, @RequestBody @Valid LinhaPontoRequest req) {
        return linhaPontoBO.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        linhaPontoBO.excluir(id);
        return ResponseEntity.noContent().build();
    }
}