package com.softdesign.vote.service.agenda;

import com.softdesign.vote.controller.v1.agenda.Agenda;
import com.softdesign.vote.entity.AgendaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgendaDto {
        private Long id;
        private String title;
        private String describe;
        private LocalDate createDate;
        private Boolean aproved;

        public AgendaEntity toEntity() {
                var date = Optional.ofNullable(this.createDate).orElse(LocalDate.now());
                return new AgendaEntity(this.id, this.title, this.describe, date, aproved);
        }

        public Agenda toResponse() {
                return Agenda.builder()
                        .id(this.id)
                        .title(this.title)
                        .describe(this.describe)
                        .createDate(createDate)
                        .aproved(this.aproved)
                        .build();
        }


}
