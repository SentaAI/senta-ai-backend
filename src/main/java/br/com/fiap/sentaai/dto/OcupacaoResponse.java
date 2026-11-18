package br.com.fiap.sentaai.dto;

public record OcupacaoResponse(
        Long onibusId,
        String placa,
        String linha,
        Integer capacidade,
        Integer ocupacaoAtual,
        Integer lugaresLivres,
        Double percentual,
        String status
) {}