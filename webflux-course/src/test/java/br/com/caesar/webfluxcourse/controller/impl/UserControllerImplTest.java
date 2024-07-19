package br.com.caesar.webfluxcourse.controller.impl;

import br.com.caesar.webfluxcourse.entity.User;
import br.com.caesar.webfluxcourse.mapper.UserMapper;
import br.com.caesar.webfluxcourse.model.request.UserRequest;
import br.com.caesar.webfluxcourse.model.request.UserResponse;
import br.com.caesar.webfluxcourse.service.UserService;
import com.mongodb.reactivestreams.client.MongoClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static reactor.core.publisher.Mono.just;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebTestClient
class UserControllerImplTest {

    public static final String ID = "12346";
    public static final String NAME = "Edward";
    public static final String EMAIL = "edward@mail.com";
    public static final String PASSWORD = "123";

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserService service;

    @MockBean
    private UserMapper mapper;

    @MockBean
    private MongoClient mongoClient;


    @Test
    @DisplayName("Test endpoint save with success")
    void testSaveWithSuccess() {
        final UserRequest request = new UserRequest(NAME, EMAIL, PASSWORD);
        final User user = User.builder().build();

        when(service.save(any(UserRequest.class))).thenReturn(just(user));

        webTestClient.post().uri("/users")
                .contentType(APPLICATION_JSON)
                .body(fromValue(request))
                .exchange()
                .expectStatus().isCreated();

        verify(service, times(1)).save(any(UserRequest.class));
    }

    @Test
    @DisplayName("Test endpoint save with bad request")
    void testSaveWithBadRequest() {
        final UserRequest request = new UserRequest(" Edward", EMAIL, PASSWORD);

        webTestClient.post().uri("/users")
                .contentType(APPLICATION_JSON)
                .body(fromValue(request))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.path").isEqualTo("/users")
                .jsonPath("$.status").isEqualTo(BAD_REQUEST.value())
                .jsonPath("$.error").isEqualTo("Validation error")
                .jsonPath("$.message").isEqualTo("Error on validation attributes")
                .jsonPath("$.errors[0].fieldName").isEqualTo("name")
                .jsonPath("$.errors[0].message").isEqualTo("field cannot have blank spaces at the beginning or at end");
    }

    @Test
    @DisplayName("Test find by id endpoint with success")
    void testFindByIdWithSuccess() {
        final UserResponse userResponse = new UserResponse(ID, NAME, EMAIL, PASSWORD);
        final User user = User.builder().build();

        when(service.findById(anyString())).thenReturn(just(user));
        when(mapper.toResponse(any(User.class))).thenReturn(userResponse);

        webTestClient.get().uri("/users/" + ID)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(ID)
                .jsonPath("$.name").isEqualTo(NAME)
                .jsonPath("$.email").isEqualTo(EMAIL)
                .jsonPath("$.password").isEqualTo(PASSWORD);

    }

    @Test
    @DisplayName("Test find all endpoint with success")
    void testFindAllWithSuccess() {
        final UserResponse userResponse = new UserResponse(ID, NAME, EMAIL, PASSWORD);
        final User user = User.builder().build();

        when(service.findAll()).thenReturn(Flux.just(user));
        when(mapper.toResponse(any(User.class))).thenReturn(userResponse);

        webTestClient.get().uri("/users")
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].id").isEqualTo(ID)
                .jsonPath("$.[0].name").isEqualTo(NAME)
                .jsonPath("$.[0].email").isEqualTo(EMAIL)
                .jsonPath("$.[0].password").isEqualTo(PASSWORD);

    }

    @Test
    void update() {
    }
}