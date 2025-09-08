package com.softdesign.vote.controller.v1.sessionagenda;

import com.softdesign.vote.dto.SessionAgendaDto;
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
