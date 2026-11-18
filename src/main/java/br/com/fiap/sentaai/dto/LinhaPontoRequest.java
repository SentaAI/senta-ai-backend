package br.com.fiap.sentaai.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LinhaPontoRequest(
        @NotNull(message = "O id da linha é obrigatório")
        Long linhaId,

        @NotNull(message = "O id do ponto é obrigatório")
        Long pontoId,

        @NotNull(message = "A ordem é obrigatória")
        @Min(value = 1, message = "A ordem deve ser maior ou igual a 1")
        Integer ordem
) {}