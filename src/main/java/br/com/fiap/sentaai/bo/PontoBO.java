package br.com.fiap.sentaai.bo;

import br.com.fiap.sentaai.dao.PontoDAO;
import br.com.fiap.sentaai.dto.PontoRequest;
import br.com.fiap.sentaai.exception.RecursoNaoEncontradoException;
import br.com.fiap.sentaai.model.Ponto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PontoBO {

    private final PontoDAO pontoDAO;

    public PontoBO(PontoDAO pontoDAO) {
        this.pontoDAO = pontoDAO;
    }

    public List<Ponto> listar() {
        return pontoDAO.findAll();
    }

    public Ponto buscarPorId(Long id) {
        return pontoDAO.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Ponto", id));
    }

    @Transactional
    public Ponto criar(PontoRequest req) {
        return pontoDAO.save(new Ponto(req.nome(), req.endereco()));
    }

    @Transactional
    public Ponto atualizar(Long id, PontoRequest req) {
        Ponto ponto = buscarPorId(id);
        ponto.setNome(req.nome());
        ponto.setEndereco(req.endereco());
        return pontoDAO.save(ponto);
    }

    @Transactional
    public void excluir(Long id) {
        pontoDAO.delete(buscarPorId(id));
        pontoDAO.flush();
    }
}