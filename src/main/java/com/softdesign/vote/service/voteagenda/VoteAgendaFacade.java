package com.softdesign.vote.v1.service.voteagenda;

import com.softdesign.vote.v1.service.associate.AssociateFacade;
import com.softdesign.vote.v1.service.sessionagenda.SessionAgendaFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class VoteAgendaFacade {

    private final VoteAgendaService voteAgendaService;
    private final AssociateFacade associateFacade;
    private final SessionAgendaFacade sessionAgendaFacade;

    public void createVoteAgenda(VoteAgendaDto voteAgendaDTO) {
        associateFacade.getAssociateById(voteAgendaDTO.getAssociateId());
        sessionAgendaFacade.isSessionOpen(voteAgendaDTO.getAgendaId());
        voteAgendaService.createVoteAgenda(voteAgendaDTO);
    }

    public Boolean voteBalance(Long agendaId) {
        return voteAgendaService.voteBalance(agendaId);
    }

}
