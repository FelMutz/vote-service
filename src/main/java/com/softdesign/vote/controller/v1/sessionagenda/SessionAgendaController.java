package com.softdesign.vote.controller.v1.sessionagenda;

import com.softdesign.vote.service.sessionagenda.SessionAgendaFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/session-agenda")
@AllArgsConstructor
public class SessionAgendaController {

    private final SessionAgendaFacade sessionAgendaFacade;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createSessionAgenda(CreateSessionAgenda createSessionAgenda) {
        sessionAgendaFacade.createSessionAgenda(createSessionAgenda.toDTO());
    }
}
