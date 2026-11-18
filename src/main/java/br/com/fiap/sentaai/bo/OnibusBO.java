package br.com.fiap.sentaai.bo;

import br.com.fiap.sentaai.dao.OnibusDAO;
import br.com.fiap.sentaai.dto.OcupacaoResponse;
import br.com.fiap.sentaai.dto.OnibusRequest;
import br.com.fiap.sentaai.exception.RecursoNaoEncontradoException;
import br.com.fiap.sentaai.exception.RegraNegocioException;
import br.com.fiap.sentaai.model.Linha;
import br.com.fiap.sentaai.model.Onibus;
import br.com.fiap.sentaai.model.StatusOcupacao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OnibusBO {

    private final OnibusDAO onibusDAO;
    private final LinhaBO linhaBO;

    public OnibusBO(OnibusDAO onibusDAO, LinhaBO linhaBO) {
        this.onibusDAO = onibusDAO;
        this.linhaBO = linhaBO;
    }

    public List<Onibus> listar() {
        return onibusDAO.findAll();
    }

    public Onibus buscarPorId(Long id) {
        return onibusDAO.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Ônibus", id));
    }

    @Transactional
    public Onibus criar(OnibusRequest req) {
        if (onibusDAO.existsByPlaca(req.placa())) {
            throw new RegraNegocioException("Já existe um ônibus com a placa " + req.placa());
        }
        Linha linha = linhaBO.buscarPorId(req.linhaId());
        return onibusDAO.save(new Onibus(req.placa(), req.capacidade(), linha));
    }

    @Transactional
    public Onibus atualizar(Long id, OnibusRequest req) {
        Onibus onibus = buscarPorId(id);
        if (!onibus.getPlaca().equals(req.placa()) && onibusDAO.existsByPlaca(req.placa())) {
            throw new RegraNegocioException("Já existe um ônibus com a placa " + req.placa());
        }
        onibus.setPlaca(req.placa());
        onibus.setCapacidade(req.capacidade());
        onibus.setLinha(linhaBO.buscarPorId(req.linhaId()));


        return onibusDAO.save(onibus);
    }

    @Transactional
    public void excluir(Long id) {
        onibusDAO.delete(buscarPorId(id));
        onibusDAO.flush();
    }


    public OcupacaoResponse calcularOcupacao(Onibus onibus) {
        int capacidade = onibus.getCapacidade();
        int ocupacao = onibus.getOcupacaoAtual();
        double percentual = Math.round(ocupacao * 1000.0 / capacidade) / 10.0;
        int livres = Math.max(0, capacidade - ocupacao);
        StatusOcupacao status = StatusOcupacao.calcular(percentual);

        return new OcupacaoResponse(onibus.getId(), onibus.getPlaca(),
                onibus.getLinha().getCodigo(), capacidade, ocupacao, livres,
                percentual, status.getDescricao());
    }

    public OcupacaoResponse ocupacao(Long id) {
        return calcularOcupacao(buscarPorId(id));
    }

    public List<OcupacaoResponse> ocupacaoPorLinha(Long linhaId) {
        linhaBO.buscarPorId(linhaId);
        return onibusDAO.findByLinhaId(linhaId).stream()
                .map(this::calcularOcupacao)
                .sorted((a, b) -> Double.compare(a.percentual(), b.percentual()))
                .toList();
    }
}