package com.softdesign.vote.stubs;

import com.softdesign.vote.dto.SessionAgendaDto;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class SessionAgendaDtoStub {
    public static SessionAgendaDto getSessionAgendaDto(LocalDateTime finishDate) {
        return SessionAgendaDto.builder()
                .id(1L)
                .agendaId(1L)
                .finishDate(finishDate)
                .build();
    }
}
