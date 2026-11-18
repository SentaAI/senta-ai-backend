package br.com.fiap.sentaai.dao;

import br.com.fiap.sentaai.model.Onibus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnibusDAO extends JpaRepository<Onibus, Long> {
    List<Onibus> findByLinhaId(Long linhaId);
    boolean existsByPlaca(String placa);
}
