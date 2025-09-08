package com.softdesign.vote.controller.v1.voteagenda;

import com.softdesign.vote.service.voteagenda.VoteAgendaFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.softdesign.vote.stubs.VoteAgendaDtoStub.getVoteAgendaDto;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VoteAgendaControllerTest {

    @Mock
    private VoteAgendaFacade voteAgendaFacade;

    @InjectMocks
    private VoteAgendaController voteAgendaController;

    @Test
    void shouldCreateVoteAgenda() {
        CreateVoteAgenda createVoteAgenda = new CreateVoteAgenda(1L, 2L, true);

        voteAgendaController.createVoteAgenda(createVoteAgenda);

        verify(voteAgendaFacade).createVoteAgenda(getVoteAgendaDto());
    }
}