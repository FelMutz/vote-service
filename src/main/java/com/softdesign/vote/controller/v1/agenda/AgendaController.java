package com.softdesign.vote.v1.controller.v1.agenda;

import com.softdesign.vote.service.agenda.AgendaFacade;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/agenda")
@AllArgsConstructor
public class AgendaController {

    private final AgendaFacade agendaFacade;

    @GetMapping("/{id}")
    public Agenda getAgendaById(@PathVariable Long id) {
        return agendaFacade.getAgendaById(id).toResponse();
    }

    @PostMapping
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    public Agenda createAgenda(@RequestBody @Valid CreateAgenda createAgenda) {
        return agendaFacade.createAgenda(createAgenda.toDTO()).toResponse();
    }

    @GetMapping
    public List<Agenda> getAllAgendas() {
        return new Agenda().from(agendaFacade.getAllAgendas());
    }

    @PutMapping
    public Agenda updateAgenda(@RequestBody @Valid UpdateAgenda updateAgenda) {
        return agendaFacade.updateAgenda(updateAgenda.toDTO()).toResponse();
    }
}
