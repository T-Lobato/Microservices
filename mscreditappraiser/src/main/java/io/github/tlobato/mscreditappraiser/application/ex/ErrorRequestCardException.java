package io.github.tlobato.mscreditappraiser.application.ex;

public class ErrorRequestCardException extends RuntimeException{
    public ErrorRequestCardException(String message) {
        super(message);
    }
}