package com.softdesign.vote.service.sessionagenda;

import com.softdesign.vote.dto.SessionAgendaDto;
import com.softdesign.vote.entity.SessionAgendaEntity;
import com.softdesign.vote.repository.SessionAgendaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static java.lang.Boolean.TRUE;

@AllArgsConstructor
@Service
class SessionAgendaService {

    private final SessionAgendaRepository sessionAgendaRepository;

    public void createSessionAgenda(SessionAgendaDto sessionAgendaDTO) {
        if (TRUE.equals(sessionAgendaRepository.existsByAgendaIdAndOpenIsTrue(sessionAgendaDTO.getAgendaId())))
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Já existe uma sessão aberta para essa pauta");

        sessionAgendaRepository.save(sessionAgendaDTO.toEntity());
    }

    public void isSessionOpen(Long agendaId) {
        if (!TRUE.equals(sessionAgendaRepository.existsByAgendaIdAndOpenIsTrue(agendaId)))
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Não existe uma sessão aberta para essa pauta");
    }

    public List<SessionAgendaDto> findByOpenIsTrueAndFinishDateBefore(){
        return sessionAgendaRepository.findByOpenIsTrueAndFinishDateBefore(LocalDateTime.now()).stream()
                .map(SessionAgendaEntity::toDTO)
                .toList();
    }

    public void closeSession(List<Long> sessionIds) {
        sessionAgendaRepository.closeSessionsByIds(sessionIds);
    }
}
