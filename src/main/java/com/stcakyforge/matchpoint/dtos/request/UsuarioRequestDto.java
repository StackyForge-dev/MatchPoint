package com.stcakyforge.matchpoint.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDto(
        @NotEmpty(message = "O usuário não pode ser nulo")
        String username,

        @Email
        @NotEmpty(message = "O email não pode ser nulo")
        String email,

        @NotEmpty(message = "A senha não pode ser nula")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caractres")
        String senha
) {}