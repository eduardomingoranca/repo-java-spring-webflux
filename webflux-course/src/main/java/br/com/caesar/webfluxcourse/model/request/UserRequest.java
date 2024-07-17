package br.com.caesar.webfluxcourse.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// record => eh uma funcionalidade do java 14 para a criacao de classes de valor mais concisas.
public record UserRequest(
        @Size(min = 3, max = 50, message = "must be between 3 and 50 characters")
        @NotBlank(message = "must not be null or empty")
        String name,

        @Email(message = "invalid email")
        @NotBlank(message = "must not be null or empty")
        String email,

        @NotBlank(message = "must not be null or empty")
        @Size(min = 3, max = 20, message = "must be between 3 and 20 characters")
        String password
) { }
