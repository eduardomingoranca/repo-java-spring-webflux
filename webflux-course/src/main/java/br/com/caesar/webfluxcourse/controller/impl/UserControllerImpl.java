package br.com.caesar.webfluxcourse.controller.impl;

import br.com.caesar.webfluxcourse.controller.UserController;
import br.com.caesar.webfluxcourse.mapper.UserMapper;
import br.com.caesar.webfluxcourse.model.request.UserRequest;
import br.com.caesar.webfluxcourse.model.request.UserResponse;
import br.com.caesar.webfluxcourse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserControllerImpl implements UserController {

    private final UserService service;
    private final UserMapper mapper;

    @Override
    public ResponseEntity<Mono<Void>> save(final UserRequest request) {
        Mono<Void> then = service.save(request).then();
        return status(CREATED).body(then);
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> findById(String id) {
        Mono<UserResponse> map = service.findById(id).map(mapper::toResponse);
        return ok().body(map);
    }

    @Override
    public ResponseEntity<Flux<UserResponse>> findAll() {
        Flux<UserResponse> map = service.findAll().map(mapper::toResponse);
        return ok().body(map);
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> update(String id, UserRequest request) {
        return null;
    }

}
