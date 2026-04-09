package com.stcakyforge.matchpoint.dtos.request;

import jakarta.validation.constraints.NotEmpty;

public record RegisterUserRequestDto(

        @NotEmpty(message = "O nome é obrigatório")
        String username,

        @NotEmpty(message = "O email é obrigatório")
        String email,

        @NotEmpty(message = "A senha é obrigatória")
        String senha
) {
}
