package com.softdesign.vote.service.sessionagenda;

import com.softdesign.vote.entity.SessionAgendaEntity;
import com.softdesign.vote.service.agenda.AgendaFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Component
public class SessionAgendaFacade {

    private final SessionAgendaService sessionAgendaService;
    private final AgendaFacade agendaFacade;

    public void createSessionAgenda(SessionAgendaDto sessionAgendaDto) {
        agendaFacade.getAgendaById(sessionAgendaDto.getAgendaId());
        sessionAgendaService.createSessionAgenda(sessionAgendaDto);
    }

    public void isSessionOpen(Long agendaId) {
        sessionAgendaService.isSessionOpen(agendaId);
    }

    public List<SessionAgendaDto> findByOpenIsTrueAndFinishDateBefore() {
        return sessionAgendaService.findByOpenIsTrueAndFinishDateBefore();
    }

    public void closeSession(List<Long> sessionIds) {
        sessionAgendaService.closeSession(sessionIds);
    }
}
