package br.com.caesar.webfluxcourse.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = { TrimStringValidator.class }) // indica que a anotacao eh uma restricao
@Target(FIELD) // indica o tipo de validacao
@Retention(RUNTIME) // indica que a anotacao deve ser mantida em tempo de execucao
public @interface TrimString {

    String message() default "field cannot have blank spaces at the beginning or at end";

    // groups() => lista de classes que representa uma lista de validacao que
    // permite dividir a anotacao em grupos e uma mesma anotacao seja utilizada
    // em diferentes contextos
    Class<?>[] groups() default {};

    // payload() => lista de classes que podem ser utilizadas para adicionar informacoes
    // complementares na anotacao
    Class<? extends Payload>[] payload() default {};
}
