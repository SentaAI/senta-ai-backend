package br.com.fiap.sentaai.bo;

import br.com.fiap.sentaai.dao.LeituraDAO;
import br.com.fiap.sentaai.dao.OnibusDAO;
import br.com.fiap.sentaai.dto.LeituraRequest;
import br.com.fiap.sentaai.exception.RecursoNaoEncontradoException;
import br.com.fiap.sentaai.exception.RegraNegocioException;
import br.com.fiap.sentaai.model.Leitura;
import br.com.fiap.sentaai.model.Onibus;
import br.com.fiap.sentaai.model.Ponto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeituraBO {

    private final LeituraDAO leituraDAO;
    private final OnibusDAO onibusDAO;
    private final OnibusBO onibusBO;
    private final PontoBO pontoBO;

    public LeituraBO(LeituraDAO leituraDAO, OnibusDAO onibusDAO, OnibusBO onibusBO, PontoBO pontoBO) {
        this.leituraDAO = leituraDAO;
        this.onibusDAO = onibusDAO;
        this.onibusBO = onibusBO;
        this.pontoBO = pontoBO;
    }

    public List<Leitura> listar() {
        return leituraDAO.findAll();
    }

    public List<Leitura> listarPorOnibus(Long onibusId) {
        onibusBO.buscarPorId(onibusId);
        return leituraDAO.findByOnibusIdOrderByDataHoraDesc(onibusId);
    }

    public Leitura buscarPorId(Long id) {
        return leituraDAO.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Leitura", id));
    }


    @Transactional
    public Leitura registrar(LeituraRequest req) {
        Onibus onibus = onibusBO.buscarPorId(req.onibusId());
        Ponto ponto = pontoBO.buscarPorId(req.pontoId());

        Leitura leitura = new Leitura(onibus, ponto, req.entradas(), req.saidas(), LocalDateTime.now());
        aplicarSaldo(onibus, leitura.getSaldo());

        return leituraDAO.save(leitura);
    }


    @Transactional
    public Leitura atualizar(Long id, LeituraRequest req) {
        Leitura leitura = buscarPorId(id);
        desfazerSaldo(leitura.getOnibus(), leitura.getSaldo());

        Onibus onibus = onibusBO.buscarPorId(req.onibusId());
        leitura.setOnibus(onibus);
        leitura.setPonto(pontoBO.buscarPorId(req.pontoId()));
        leitura.setEntradas(req.entradas());
        leitura.setSaidas(req.saidas());
        aplicarSaldo(onibus, leitura.getSaldo());

        return leituraDAO.save(leitura);
    }

    @Transactional
    public void excluir(Long id) {
        Leitura leitura = buscarPorId(id);
        desfazerSaldo(leitura.getOnibus(), leitura.getSaldo());
        leituraDAO.delete(leitura);
    }


    private void aplicarSaldo(Onibus onibus, int saldo) {
        int nova = onibus.getOcupacaoAtual() + saldo;
        if (nova < 0) {
            throw new RegraNegocioException("Saídas maiores que o número de passageiros a bordo ("
                    + onibus.getOcupacaoAtual() + ")");
        }
        onibus.setOcupacaoAtual(nova);
        onibusDAO.save(onibus);
    }

    private void desfazerSaldo(Onibus onibus, int saldo) {
        onibus.setOcupacaoAtual(Math.max(0, onibus.getOcupacaoAtual() - saldo));
        onibusDAO.save(onibus);
    }
}