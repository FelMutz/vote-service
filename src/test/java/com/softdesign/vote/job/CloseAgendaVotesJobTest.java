package com.softdesign.vote.job;

import com.softdesign.vote.dto.SessionAgendaDto;
import com.softdesign.vote.service.agenda.AgendaFacade;
import com.softdesign.vote.service.sessionagenda.SessionAgendaFacade;
import com.softdesign.vote.service.voteagenda.VoteAgendaFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.lang.Boolean.TRUE;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CloseAgendaVotesJobTest {

    @Mock
    private SessionAgendaFacade sessionAgendaFacade;

    @Mock
    private VoteAgendaFacade voteAgendaFacade;

    @Mock
    private AgendaFacade agendaFacade;

    @InjectMocks
    private CloseAgendaVotesJob closeAgendaVotesJob;


    @Test
    void shouldCloseExpiredAgendasAndUpdateResults() {
        SessionAgendaDto agenda1 = SessionAgendaDto.builder().id(1L).agendaId(100L).build();
        SessionAgendaDto agenda2 = SessionAgendaDto.builder().id(2L).agendaId(200L).build();

        when(sessionAgendaFacade.findByOpenIsTrueAndFinishDateBefore())
                .thenReturn(List.of(agenda1, agenda2));

        when(voteAgendaFacade.voteBalance(100L)).thenReturn(TRUE);
        when(voteAgendaFacade.voteBalance(200L)).thenReturn(false);

        closeAgendaVotesJob.closeAgendaVotes();

        verify(sessionAgendaFacade).closeSession(List.of(1L, 2L));

        verify(agendaFacade).updateAgendaResult(100L, TRUE);
        verify(agendaFacade).updateAgendaResult(200L, false);
    }

    @Test
    void shouldDoNothingIfNoExpiredAgendas() {
        when(sessionAgendaFacade.findByOpenIsTrueAndFinishDateBefore()).thenReturn(List.of());

        closeAgendaVotesJob.closeAgendaVotes();

        verify(sessionAgendaFacade, never()).closeSession(anyList());
        verify(agendaFacade, never()).updateAgendaResult(anyLong(), anyBoolean());
        verify(voteAgendaFacade, never()).voteBalance(anyLong());
    }
}