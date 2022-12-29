package io.github.tlobato.mscreditappraiser.application.ex;

public class ClientsDataNotFoundException extends Exception{
    public ClientsDataNotFoundException() {
        super("Client data not found by cpf");
    }
}