package com.stcakyforge.matchpoint.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record EmailRequestDto(

        @NotBlank(message = "O email não pode ser nulo")
        String novoEmail
) {}
