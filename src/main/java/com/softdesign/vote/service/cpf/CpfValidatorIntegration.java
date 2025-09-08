package com.softdesign.vote.service.cpf;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
class CpfValidatorIntegration {

    private final RestClient restClient;

    @Value("${cpf.api.url}")
    private String cpfApiUrl;

    private static final String CPF_VALIDATOR_CB = "cpfValidatorCircuitBreaker";

    @CircuitBreaker(name = CPF_VALIDATOR_CB, fallbackMethod = "fallbackValidateCpf")
    @TimeLimiter(name = CPF_VALIDATOR_CB)
    public CompletableFuture<CpfValidatorResponse> validateCpf(String cpf) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return restClient.get()
                        .uri(cpfApiUrl + "/" + cpf)
                        .retrieve()
                        .body(CpfValidatorResponse.class);
            } catch (RestClientResponseException ex) {
                if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                    throw new CpfCannotVoteException(cpf);
                }
                throw ex;
            } catch (RestClientException ex) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao validar CPF", ex);
            }
        });
    }

    private CompletableFuture<CpfValidatorResponse> fallbackValidateCpf(String cpf, Throwable t) {
        return CompletableFuture.completedFuture(
                CpfValidatorResponse.builder()
                        .status("UNABLE_TO_VOTE")
                        .build()
        );
    }
}
