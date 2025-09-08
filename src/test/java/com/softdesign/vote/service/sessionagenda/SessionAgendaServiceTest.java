package com.softdesign.vote.service.sessionagenda;

import com.softdesign.vote.dto.SessionAgendaDto;
import com.softdesign.vote.entity.SessionAgendaEntity;
import com.softdesign.vote.repository.SessionAgendaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static com.softdesign.vote.stubs.SessionAgendaDtoStub.getSessionAgendaDto;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessionAgendaServiceTest {

    @Mock
    private SessionAgendaRepository sessionAgendaRepository;

    @InjectMocks
    private SessionAgendaService sessionAgendaService;

    @Test
    void shouldCreateSessionAgendaWhenNoOpenSessionExists() {
        SessionAgendaDto dto = getSessionAgendaDto(LocalDateTime.now().plusMinutes(1));

        when(sessionAgendaRepository.existsByAgendaIdAndOpenIsTrue(dto.getAgendaId())).thenReturn(false);
        when(sessionAgendaRepository.save(any(SessionAgendaEntity.class)))
                .thenReturn(dto.toEntity());

        sessionAgendaService.createSessionAgenda(dto);

        verify(sessionAgendaRepository).save(any(SessionAgendaEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenOpenSessionExistsOnCreate() {
        SessionAgendaDto dto = getSessionAgendaDto(LocalDateTime.now().plusMinutes(1));

        when(sessionAgendaRepository.existsByAgendaIdAndOpenIsTrue(dto.getAgendaId())).thenReturn(true);

        assertThatThrownBy(() -> sessionAgendaService.createSessionAgenda(dto))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Já existe uma sessão aberta para essa pauta");

        verify(sessionAgendaRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenNoOpenSessionExistsOnCheck() {
        Long agendaId = 1L;
        when(sessionAgendaRepository.existsByAgendaIdAndOpenIsTrue(agendaId)).thenReturn(false);

        assertThatThrownBy(() -> sessionAgendaService.isSessionOpen(agendaId))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Não existe uma sessão aberta para essa pauta");
    }

    @Test
    void shouldReturnOpenSessionsFinishedBeforeNow() {
        SessionAgendaEntity entity = getSessionAgendaDto(LocalDateTime.now().minusMinutes(1)).toEntity();

        when(sessionAgendaRepository.findByOpenIsTrueAndFinishDateBefore(any(LocalDateTime.class)))
                .thenReturn(List.of(entity));

        var result = sessionAgendaService.findByOpenIsTrueAndFinishDateBefore();

        verify(sessionAgendaRepository).findByOpenIsTrueAndFinishDateBefore(any(LocalDateTime.class));
        assert(result.size() == 1);
    }

    @Test
    void shouldCloseSessionsByIds() {
        List<Long> ids = List.of(1L, 2L);

        sessionAgendaService.closeSession(ids);

        verify(sessionAgendaRepository).closeSessionsByIds(ids);
    }
}