package br.com.fiap.sentaai.dao;

import br.com.fiap.sentaai.model.Ponto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PontoDAO extends JpaRepository<Ponto, Long> {
}
