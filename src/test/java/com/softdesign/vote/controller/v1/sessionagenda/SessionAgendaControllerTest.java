package com.softdesign.vote.controller.v1.sessionagenda;

import com.softdesign.vote.dto.SessionAgendaDto;
import com.softdesign.vote.service.sessionagenda.SessionAgendaFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SessionAgendaControllerTest {

    @Mock
    private SessionAgendaFacade sessionAgendaFacade;

    @InjectMocks
    private SessionAgendaController sessionAgendaController;

    @Test
    void shouldCreateSessionAgendaWithoutFinishDate() {
        var createSessionAgenda = new CreateSessionAgenda(1L, null);

        sessionAgendaController.createSessionAgenda(createSessionAgenda);

        ArgumentCaptor<SessionAgendaDto> captor = ArgumentCaptor.forClass(SessionAgendaDto.class);
        verify(sessionAgendaFacade).createSessionAgenda(captor.capture());

        var capturedDto = captor.getValue();
        assertThat(capturedDto.getAgendaId()).isEqualTo(1L);
        assertThat(capturedDto.getStartDate()).isNotNull();
        assertThat(capturedDto.getFinishDate()).isEqualTo(capturedDto.getStartDate().plusMinutes(1));
        assertThat(capturedDto.getOpen()).isTrue();
    }

    @Test
    void shouldCreateSessionAgendaWithFinishDate() {
        var finishDate = LocalDateTime.of(2025, 9, 7, 21, 0);
        var createSessionAgenda =
                new CreateSessionAgenda(1L, finishDate.atOffset(ZoneOffset.UTC));

        sessionAgendaController.createSessionAgenda(createSessionAgenda);

        ArgumentCaptor<SessionAgendaDto> captor = ArgumentCaptor.forClass(SessionAgendaDto.class);
        verify(sessionAgendaFacade).createSessionAgenda(captor.capture());

        var capturedDto = captor.getValue();
        assertThat(capturedDto.getAgendaId()).isEqualTo(1L);
        assertThat(capturedDto.getStartDate()).isNotNull();
        assertThat(capturedDto.getFinishDate()).isEqualTo(finishDate);
        assertThat(capturedDto.getOpen()).isTrue();
    }
}