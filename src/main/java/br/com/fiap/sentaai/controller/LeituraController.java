package br.com.fiap.sentaai.controller;

import br.com.fiap.sentaai.bo.LeituraBO;
import br.com.fiap.sentaai.dto.LeituraRequest;
import br.com.fiap.sentaai.model.Leitura;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leituras")
public class LeituraController {

    private final LeituraBO leituraBO;

    public LeituraController(LeituraBO leituraBO) {
        this.leituraBO = leituraBO;
    }

    @GetMapping
    public List<Leitura> listar() {
        return leituraBO.listar();
    }

    @GetMapping("/{id}")
    public Leitura buscar(@PathVariable Long id) {
        return leituraBO.buscarPorId(id);
    }

    @GetMapping("/onibus/{onibusId}")
    public List<Leitura> listarPorOnibus(@PathVariable Long onibusId) {
        return leituraBO.listarPorOnibus(onibusId);
    }


    @PostMapping
    public ResponseEntity<Leitura> registrar(@RequestBody @Valid LeituraRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(leituraBO.registrar(req));
    }

    @PutMapping("/{id}")
    public Leitura atualizar(@PathVariable Long id, @RequestBody @Valid LeituraRequest req) {
        return leituraBO.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        leituraBO.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
