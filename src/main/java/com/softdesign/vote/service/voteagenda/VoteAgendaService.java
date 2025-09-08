package com.softdesign.vote.v1.service.voteagenda;

import com.softdesign.vote.v1.repository.VoteAgendaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
class VoteAgendaService {

    private final VoteAgendaRepository voteAgendaRepository;

    public void createVoteAgenda(VoteAgendaDto voteAgendaDTO) {
        voteAgendaRepository.findByAgendaIdAndAssociateId(voteAgendaDTO.getAgendaId(), voteAgendaDTO.getAssociateId())
                        .ifPresent(v ->{
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Associado já votou nessa pauta");
                        });
        voteAgendaRepository.save(voteAgendaDTO.toEntity());
    }

    public Boolean voteBalance(Long agendaId) {
        Integer balance = voteAgendaRepository.voteBalance(agendaId);
        if (balance == null || balance == 0) {
            return false;
        }
        return balance > 0;
    }
}
