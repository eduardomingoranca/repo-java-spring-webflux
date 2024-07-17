package br.com.caesar.webfluxcourse.model.request;

// record => eh uma funcionalidade do java 14 para a criacao de classes de valor mais concisas.
public record UserRequest(
        String name,
        String email,
        String password
) { }
