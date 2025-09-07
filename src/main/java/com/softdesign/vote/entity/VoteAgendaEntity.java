package com.softdesign.vote.entity;

import com.softdesign.vote.service.voteagenda.VoteAgendaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "vote_agenda")
@AllArgsConstructor
@NoArgsConstructor
public class VoteAgendaEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Long agendaId;
        private Long associateId;
        private Boolean vote;
        @Column(updatable = false)
        private LocalDate createDate;

        public VoteAgendaDto toDTO() {
                return VoteAgendaDto.builder()
                        .id(this.id)
                        .agendaId(this.agendaId)
                        .associateId(this.associateId)
                        .vote(this.vote)
                        .createDate(this.createDate)
                        .build();
        }
}
