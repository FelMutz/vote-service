package com.softdesign.vote.service.cpf;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CpfValidatorService {

    private final CpfValidatorIntegration cpfValidatorIntegration;

    public void isCpfValid(String cpf) {
        var response = cpfValidatorIntegration.validateCpf(cpf);
        if ("UNABLE_TO_VOTE".equals(response.getStatus()))
            throw new CpfCannotVoteException(cpf);
    }
}
