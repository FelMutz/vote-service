package com.softdesign.vote.controller.v1.voteagenda;

import com.softdesign.vote.service.voteagenda.VoteAgendaFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/vote-agenda")
@AllArgsConstructor
public class VoteAgendaController {

    private final VoteAgendaFacade voteAgendaFacade;

    @PostMapping
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    public void createVoteAgenda(CreateVoteAgenda createVoteAgenda) {
        voteAgendaFacade.createVoteAgenda(createVoteAgenda.toDTO());
    }
}
