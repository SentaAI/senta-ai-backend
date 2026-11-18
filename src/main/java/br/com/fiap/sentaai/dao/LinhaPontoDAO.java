package br.com.fiap.sentaai.dao;

import br.com.fiap.sentaai.model.LinhaPonto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinhaPontoDAO extends JpaRepository<LinhaPonto, Long> {
    List<LinhaPonto> findByLinhaIdOrderByOrdemAsc(Long linhaId);
    boolean existsByLinhaIdAndPontoId(Long linhaId, Long pontoId);
}