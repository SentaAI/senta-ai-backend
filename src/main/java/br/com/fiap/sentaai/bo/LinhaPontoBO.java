package br.com.fiap.sentaai.bo;

import br.com.fiap.sentaai.dao.LinhaPontoDAO;
import br.com.fiap.sentaai.dto.LinhaPontoRequest;
import br.com.fiap.sentaai.exception.RecursoNaoEncontradoException;
import br.com.fiap.sentaai.exception.RegraNegocioException;
import br.com.fiap.sentaai.model.Linha;
import br.com.fiap.sentaai.model.LinhaPonto;
import br.com.fiap.sentaai.model.Ponto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LinhaPontoBO {

    private final LinhaPontoDAO linhaPontoDAO;
    private final LinhaBO linhaBO;
    private final PontoBO pontoBO;

    public LinhaPontoBO(LinhaPontoDAO linhaPontoDAO, LinhaBO linhaBO, PontoBO pontoBO) {
        this.linhaPontoDAO = linhaPontoDAO;
        this.linhaBO = linhaBO;
        this.pontoBO = pontoBO;
    }

    public List<LinhaPonto> listar() {
        return linhaPontoDAO.findAll();
    }

    // Trajeto da linha: pontos em ordem
    public List<LinhaPonto> listarPorLinha(Long linhaId) {
        linhaBO.buscarPorId(linhaId);
        return linhaPontoDAO.findByLinhaIdOrderByOrdemAsc(linhaId);
    }

    public LinhaPonto buscarPorId(Long id) {
        return linhaPontoDAO.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Linha-Ponto", id));
    }

    @Transactional
    public LinhaPonto criar(LinhaPontoRequest req) {
        Linha linha = linhaBO.buscarPorId(req.linhaId());
        Ponto ponto = pontoBO.buscarPorId(req.pontoId());
        if (linhaPontoDAO.existsByLinhaIdAndPontoId(req.linhaId(), req.pontoId())) {
            throw new RegraNegocioException("Este ponto já faz parte do trajeto desta linha");
        }
        return linhaPontoDAO.save(new LinhaPonto(linha, ponto, req.ordem()));
    }

    @Transactional
    public LinhaPonto atualizar(Long id, LinhaPontoRequest req) {
        LinhaPonto lp = buscarPorId(id);
        Linha linha = linhaBO.buscarPorId(req.linhaId());
        Ponto ponto = pontoBO.buscarPorId(req.pontoId());
        boolean mudouPar = !lp.getLinha().getId().equals(req.linhaId())
                || !lp.getPonto().getId().equals(req.pontoId());
        if (mudouPar && linhaPontoDAO.existsByLinhaIdAndPontoId(req.linhaId(), req.pontoId())) {
            throw new RegraNegocioException("Este ponto já faz parte do trajeto desta linha");
        }
        lp.setLinha(linha);
        lp.setPonto(ponto);
        lp.setOrdem(req.ordem());
        return linhaPontoDAO.save(lp);
    }

    @Transactional
    public void excluir(Long id) {
        linhaPontoDAO.delete(buscarPorId(id));
    }


}
