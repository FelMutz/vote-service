package com.softdesign.vote.service.agenda;

import com.softdesign.vote.dto.AgendaDto;
import com.softdesign.vote.entity.AgendaEntity;
import com.softdesign.vote.repository.AgendaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static com.softdesign.vote.stubs.AgendaDtoStub.getAgendaDto;
import static com.softdesign.vote.stubs.AgendaEntityStub.getAgendaEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AgendaServiceTest {

    @Mock
    private AgendaRepository agendaRepository;

    @InjectMocks
    private AgendaService agendaService;

    private AgendaDto agendaDto;
    private AgendaEntity agendaEntity;

    @BeforeEach
    void setUp() {
        agendaDto = getAgendaDto();

        agendaEntity = getAgendaEntity();
    }

    @Test
    void shouldCreateAgenda() {
        when(agendaRepository.save(any())).thenReturn(agendaEntity);

        AgendaDto result = agendaService.createAgenda(agendaDto);

        assertNotNull(result);
        assertEquals(agendaDto.getId(), result.getId());
        assertEquals(agendaDto.getTitle(), result.getTitle());
        verify(agendaRepository, times(1)).save(any());
    }

    @Test
    void shouldGetAllAgendas() {
        when(agendaRepository.findAll()).thenReturn(List.of(agendaEntity));

        List<AgendaDto> result = agendaService.getAllAgendas();

        assertEquals(1, result.size());
        assertEquals("Test Agenda", result.getFirst().getTitle());
        verify(agendaRepository, times(1)).findAll();
    }

    @Test
    void shouldGetAgendaById() {
        when(agendaRepository.findById(1L)).thenReturn(Optional.of(agendaEntity));

        AgendaDto result = agendaService.getAgendaById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(agendaRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenAgendaNotFoundById() {
        when(agendaRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> agendaService.getAgendaById(99L)
        );

        assertEquals("404 NOT_FOUND \"Pauta não encontrada\"", exception.getMessage());
        verify(agendaRepository, times(1)).findById(99L);
    }

    @Test
    void shouldUpdateAgenda() {
        when(agendaRepository.findById(1L)).thenReturn(Optional.of(agendaEntity));
        when(agendaRepository.save(any())).thenReturn(agendaEntity);

        AgendaDto result = agendaService.updateAgenda(agendaDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(agendaRepository, times(1)).findById(1L);
        verify(agendaRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonexistentAgenda() {
        when(agendaRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> agendaService.updateAgenda(agendaDto)
        );

        assertEquals("404 NOT_FOUND \"Pauta não encontrada\"", exception.getMessage());
        verify(agendaRepository, times(1)).findById(1L);
        verify(agendaRepository, never()).save(any());
    }

    @Test
    void shouldUpdateAgendaResult() {
        doNothing().when(agendaRepository).updateAgendaResult(1L, true);

        agendaService.updateAgendaResult(1L, true);

        verify(agendaRepository, times(1)).updateAgendaResult(1L, true);
    }
}