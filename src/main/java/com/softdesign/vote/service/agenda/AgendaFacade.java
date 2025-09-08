package com.softdesign.vote.service.agenda;

import com.softdesign.vote.dto.AgendaDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class AgendaFacade {

    private final AgendaService agendaService;

    public AgendaDto createAgenda(AgendaDto agendaDto) {
        return agendaService.createAgenda(agendaDto);
    }

    public List<AgendaDto> getAllAgendas() {
        return agendaService.getAllAgendas();
    }

    public AgendaDto getAgendaById(Long id) {
        return agendaService.getAgendaById(id);
    }

    public AgendaDto updateAgenda(AgendaDto agendaDto) {
        return agendaService.updateAgenda(agendaDto);
    }

    public void updateAgendaResult(Long agendaId, Boolean aproved) {
        agendaService.updateAgendaResult(agendaId, aproved);
    }
}
