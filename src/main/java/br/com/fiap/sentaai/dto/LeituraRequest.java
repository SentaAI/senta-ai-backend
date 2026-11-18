package br.com.fiap.sentaai.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LeituraRequest(
        @NotNull(message = "O id do ônibus é obrigatório")
        Long onibusId,

        @NotNull(message = "O id do ponto é obrigatório")
        Long pontoId,

        @NotNull(message = "A quantidade de entradas é obrigatória")
        @Min(value = 0, message = "Entradas não pode ser negativo")
        Integer entradas,

        @NotNull(message = "A quantidade de saídas é obrigatória")
        @Min(value = 0, message = "Saídas não pode ser negativo")
        Integer saidas
) {}