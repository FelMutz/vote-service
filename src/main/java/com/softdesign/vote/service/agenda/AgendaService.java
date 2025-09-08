package com.softdesign.vote.v1.service.agenda;

import com.softdesign.vote.v1.entity.AgendaEntity;
import com.softdesign.vote.v1.repository.AgendaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@Service
class AgendaService {

    public final AgendaRepository agendaRepository;

    public AgendaDto createAgenda(AgendaDto createAgenda) {
        return agendaRepository.save(createAgenda.toEntity()).toDTO();
    }

    public List<AgendaDto> getAllAgendas() {
        return agendaRepository.findAll().stream()
                .map(AgendaEntity::toDTO)
                .toList();
    }

    public AgendaDto getAgendaById(Long id) {
        return agendaRepository.findById(id)
                .map(AgendaEntity::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pauta não encontrada"));
    }

    public AgendaDto updateAgenda(AgendaDto agendaDto) {
        agendaRepository.findById(agendaDto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pauta não encontrada"));

        return agendaRepository.save(agendaDto.toEntity()).toDTO();
    }

    public void updateAgendaResult(Long agendaId, Boolean aproved) {
        agendaRepository.updateAgendaResult(agendaId,aproved);
    }
}
