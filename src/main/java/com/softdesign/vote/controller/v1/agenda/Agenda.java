package com.softdesign.vote.controller.v1.agenda;

import com.softdesign.vote.dto.AgendaDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Agenda {
        private Long id;
        private String title;
        private String describe;
        private LocalDate createDate;
        private Boolean aproved;

        public List<Agenda> from(List<AgendaDto> agendaDtos) {
                return agendaDtos.stream()
                        .map(AgendaDto::toResponse)
                        .toList();
        }
}
