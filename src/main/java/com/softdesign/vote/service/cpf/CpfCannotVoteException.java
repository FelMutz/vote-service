package com.softdesign.vote.service.cpf;

public class CpfCannotVoteException extends RuntimeException {
    public CpfCannotVoteException(String cpf) {
        super("O CPF " + cpf + " não está apto a votar.");
    }
}
