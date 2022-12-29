package io.github.tlobato.mscreditappraiser.application.ex;

import lombok.Getter;

public class ErrorComunicationMicroservicesException extends Exception{

    @Getter
    private Integer status;

    public ErrorComunicationMicroservicesException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}