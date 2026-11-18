package br.com.fiap.sentaai.dao;

import br.com.fiap.sentaai.model.Linha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinhaDAO extends JpaRepository<Linha, Long> {
    boolean existsByCodigo(String codigo);
}