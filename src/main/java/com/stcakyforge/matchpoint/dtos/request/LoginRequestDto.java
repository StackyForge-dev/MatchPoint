package com.stcakyforge.matchpoint.dtos.request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequestDto(
        @NotEmpty(message = "Email é obrigatório")
        String email,

        @NotEmpty(message = "Senha é obrigatória")
        String senha
) {
}
