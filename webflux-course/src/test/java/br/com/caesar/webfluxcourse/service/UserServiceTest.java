package br.com.caesar.webfluxcourse.service;

import br.com.caesar.webfluxcourse.entity.User;
import br.com.caesar.webfluxcourse.mapper.UserMapper;
import br.com.caesar.webfluxcourse.model.request.UserRequest;
import br.com.caesar.webfluxcourse.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static reactor.core.publisher.Mono.just;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserService service;


    @Test
    void save() {
        UserRequest request = new UserRequest("edward", "edward@mail.com", "123");
        User entity = User.builder().build();

        when(mapper.toEntity(any(UserRequest.class))).thenReturn(entity);
        when(repository.save(any(User.class))).thenReturn(just(entity));

        Mono<User> result = service.save(request);

        // verificando o comportamento do publisher
        StepVerifier.create(result)
                .expectNextMatches(Objects::nonNull)
                .expectComplete()
                .verify();

        verify(repository, times(1)).save(any(User.class));
    }

}