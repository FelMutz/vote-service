package com.softdesign.vote.entity;

import com.softdesign.vote.dto.SessionAgendaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "session_agenda")
@AllArgsConstructor
@NoArgsConstructor
public class SessionAgendaEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Long agendaId;
        private LocalDateTime startDate;
        private LocalDateTime finishDate;
        private Boolean open;

        public SessionAgendaDto toDTO() {
                return SessionAgendaDto.builder()
                        .id(this.id)
                        .agendaId(this.agendaId)
                        .startDate(this.startDate)
                        .finishDate(this.finishDate)
                        .open(this.open)
                        .build();
        }
}
