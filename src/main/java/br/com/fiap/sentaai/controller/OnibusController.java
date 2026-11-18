package br.com.fiap.sentaai.controller;

import br.com.fiap.sentaai.bo.OnibusBO;
import br.com.fiap.sentaai.dto.OcupacaoResponse;
import br.com.fiap.sentaai.dto.OnibusRequest;
import br.com.fiap.sentaai.model.Onibus;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/onibus")
public class OnibusController {

    private final OnibusBO onibusBO;

    public OnibusController(OnibusBO onibusBO) {
        this.onibusBO = onibusBO;
    }

    @GetMapping
    public List<Onibus> listar() {
        return onibusBO.listar();
    }

    @GetMapping("/{id}")
    public Onibus buscar(@PathVariable Long id) {
        return onibusBO.buscarPorId(id);
    }


    @GetMapping("/{id}/ocupacao")
    public OcupacaoResponse ocupacao(@PathVariable Long id) {
        return onibusBO.ocupacao(id);
    }

    @PostMapping
    public ResponseEntity<Onibus> criar(@RequestBody @Valid OnibusRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(onibusBO.criar(req));
    }

    @PutMapping("/{id}")
    public Onibus atualizar(@PathVariable Long id, @RequestBody @Valid OnibusRequest req) {
        return onibusBO.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        onibusBO.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
