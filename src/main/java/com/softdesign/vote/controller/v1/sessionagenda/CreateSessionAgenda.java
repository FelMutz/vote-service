package com.softdesign.vote.v1.controller.v1.sessionagenda;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.softdesign.vote.service.sessionagenda.SessionAgendaDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSessionAgenda {
        private Long agendaId;
        private OffsetDateTime finishDate;

        public SessionAgendaDto toDTO() {
                var date = LocalDateTime.now();
                return SessionAgendaDto.builder()
                    .agendaId(this.agendaId)
                    .startDate(date)
                    .finishDate(Optional.ofNullable(this.finishDate)
                            .map(OffsetDateTime::toLocalDateTime)
                            .orElse(date.plusMinutes(1)))
                    .open(true)
                    .build();
        }
}
