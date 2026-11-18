package br.com.fiap.sentaai.bo;

import br.com.fiap.sentaai.dao.LinhaDAO;
import br.com.fiap.sentaai.dto.LinhaRequest;
import br.com.fiap.sentaai.exception.RecursoNaoEncontradoException;
import br.com.fiap.sentaai.exception.RegraNegocioException;
import br.com.fiap.sentaai.model.Linha;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LinhaBO {

    private final LinhaDAO linhaDAO;

    public LinhaBO(LinhaDAO linhaDAO) {
        this.linhaDAO = linhaDAO;
    }

    public List<Linha> listar() {
        return linhaDAO.findAll();
    }

    public Linha buscarPorId(Long id) {
        return linhaDAO.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Linha", id));
    }

    @Transactional
    public Linha criar(LinhaRequest req) {
        if (linhaDAO.existsByCodigo(req.codigo())) {
            throw new RegraNegocioException("Já existe uma linha com o código " + req.codigo());
        }
        return linhaDAO.save(new Linha(req.codigo(), req.nome()));
    }

    @Transactional
    public Linha atualizar(Long id, LinhaRequest req) {
        Linha linha = buscarPorId(id);
        if (!linha.getCodigo().equals(req.codigo()) && linhaDAO.existsByCodigo(req.codigo())) {
            throw new RegraNegocioException("Já existe uma linha com o código " + req.codigo());
        }
        linha.setCodigo(req.codigo());
        linha.setNome(req.nome());
        return linhaDAO.save(linha);
    }

    @Transactional
    public void excluir(Long id) {
        linhaDAO.delete(buscarPorId(id));
        linhaDAO.flush(); // se a linha tiver o onibus o erro de FK ele vai aparecer aqui.
    }
}