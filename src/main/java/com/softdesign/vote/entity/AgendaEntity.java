package com.softdesign.vote.entity;

import com.softdesign.vote.dto.AgendaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "agenda")
@AllArgsConstructor
@NoArgsConstructor
public class AgendaEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String title;
        private String describe;
        @Column(updatable = false)
        private LocalDate createDate;
        private Boolean aproved;

        public AgendaDto toDTO() {
                return AgendaDto.builder()
                        .id(this.id)
                        .title(this.title)
                        .describe(this.describe)
                        .createDate(this.createDate)
                        .aproved(this.aproved)
                        .build();
        }
}
