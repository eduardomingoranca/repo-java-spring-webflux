package br.com.caesar.webfluxcourse.service;

import br.com.caesar.webfluxcourse.entity.User;
import br.com.caesar.webfluxcourse.mapper.UserMapper;
import br.com.caesar.webfluxcourse.model.request.UserRequest;
import br.com.caesar.webfluxcourse.repository.UserRepository;
import br.com.caesar.webfluxcourse.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.String.format;
import static reactor.core.publisher.Mono.error;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    // final => os atributos da classe nao podem ser modificados dentro do metodo
    public Mono<User> save(final UserRequest request) {
        User entity = mapper.toEntity(request);
        return repository.save(entity);
    }

    public Mono<User> findById(final String id) {
        String message = format("Object not found. Id: %s, Type: %s",
                id, User.class.getSimpleName());
        ObjectNotFoundException exception = new ObjectNotFoundException(message);

        return repository.findById(id)
                .switchIfEmpty(error(exception));
    }

    public Flux<User> findAll() {
        return repository.findAll();
    }

}
