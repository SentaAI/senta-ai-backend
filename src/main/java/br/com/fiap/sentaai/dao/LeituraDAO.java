package br.com.fiap.sentaai.dao;

import br.com.fiap.sentaai.model.Leitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//aqui é a regra das leituras o sensor envia para, o arudino nocaso
// aqui tambem é a ocupaçao do onibus onde sera atualizada.
@Repository
public interface LeituraDAO extends JpaRepository<Leitura, Long> {
    List<Leitura> findByOnibusIdOrderByDataHoraDesc(Long onibusId);
}