package com.example.crud.domain.request;

import jakarta.validation.constraints.NotBlank;

public record RequestUser(@NotBlank String name, @NotBlank String email, @NotBlank String password) {
}
