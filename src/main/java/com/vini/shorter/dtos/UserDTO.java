package com.vini.shorter.dtos;

import jakarta.validation.constraints.NotBlank;


public record UserDTO(
        @NotBlank(message = "O nome do usuário é obrigatório!") String name,
        @NotBlank(message = "O email do usuário é obrigatório!") String email,
        @NotBlank(message = "A senha do usuário é obrigatória!") String password
){}
