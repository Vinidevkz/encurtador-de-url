package com.vini.shorter.dtos;

import jakarta.validation.constraints.NotBlank;

public record OriginalUrlDTO(
        @NotBlank(message = "a url original é obrigatória!.") String originalUrl,
        @NotBlank(message = "o id do usuário é obrigatório!.") Long userId
){}
