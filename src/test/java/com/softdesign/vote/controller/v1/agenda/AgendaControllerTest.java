package com.softdesign.vote.controller.v1.agenda;

import com.softdesign.vote.service.agenda.AgendaFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.softdesign.vote.stubs.AgendaDtoStub.getAgendaDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgendaControllerTest {

    @Mock
    private AgendaFacade agendaFacade;

    @InjectMocks
    private AgendaController agendaController;

    @Test
    void shouldGetAgendaById() {
        when(agendaFacade.getAgendaById(1L)).thenReturn(getAgendaDto());

        Agenda response = agendaController.getAgendaById(1L);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getTitle()).isEqualTo("Test Agenda");
        verify(agendaFacade).getAgendaById(1L);
    }

    @Test
    void shouldCreateAgenda() {
        CreateAgenda createAgenda = new CreateAgenda("New Agenda", "New Description");
        when(agendaFacade.createAgenda(any())).thenReturn(getAgendaDto());

        Agenda response = agendaController.createAgenda(createAgenda);

        assertThat(response.getTitle()).isEqualTo("Test Agenda");
        verify(agendaFacade).createAgenda(any());
    }

    @Test
    void shouldListAllAgendas() {
        when(agendaFacade.getAllAgendas()).thenReturn(List.of(getAgendaDto()));

        List<Agenda> response = agendaController.getAllAgendas();

        assertThat(response).hasSize(1);
        assertThat(response.getFirst().getTitle()).isEqualTo("Test Agenda");
        verify(agendaFacade).getAllAgendas();
    }

    @Test
    void shouldUpdateAgenda() {
        UpdateAgenda updateAgenda = new UpdateAgenda(1L, "Updated Agenda", "Updated Description");
        when(agendaFacade.updateAgenda(any())).thenReturn(getAgendaDto());

        Agenda response = agendaController.updateAgenda(updateAgenda);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getTitle()).isEqualTo("Test Agenda");
        verify(agendaFacade).updateAgenda(any());
    }
}