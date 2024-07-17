package br.com.caesar.webfluxcourse.controller.exceptions;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
// Serializable => para poder criar uma sequencia de bytes do objeto para que possa ser
// trafegado em rede e recuperado em memoria
public class StandardError implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    private LocalDateTime timestamp;
    private String path;
    private Integer status;
    private String error;
    private String message;

}
