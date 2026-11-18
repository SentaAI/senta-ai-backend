package br.com.fiap.sentaai.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record OnibusRequest(
        @NotBlank(message = "A placa é obrigatória")
        @Size(max = 10, message = "A placa deve ter no máximo 10 caracteres")
        String placa,

        @NotNull(message = "A capacidade é obrigatória")
        @Min(value = 1, message = "A capacidade deve ser maior que zero")
        Integer capacidade,

        @NotNull(message = "O id da linha é obrigatório")
        Long linhaId
) {}