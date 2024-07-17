package br.com.caesar.webfluxcourse.service;

import br.com.caesar.webfluxcourse.entity.User;
import br.com.caesar.webfluxcourse.mapper.UserMapper;
import br.com.caesar.webfluxcourse.model.request.UserRequest;
import br.com.caesar.webfluxcourse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    // final => os atributos da classe nao podem ser modificados dentro do metodo
    public Mono<User> save(final UserRequest request) {
        return repository.save(mapper.toEntity(request));
    }

}
