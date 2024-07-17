package br.com.caesar.webfluxcourse.model.request;

public record UserResponse(
        String id,
        String name,
        String email,
        String password
) { }
