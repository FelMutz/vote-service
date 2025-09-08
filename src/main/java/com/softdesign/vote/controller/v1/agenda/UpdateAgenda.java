package com.softdesign.vote.controller.v1.agenda;

import com.softdesign.vote.dto.AgendaDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAgenda {
        @NonNull
        private Long id;
        @NonNull
        private String title;
        @NonNull
        private String describe;

        public AgendaDto toDTO() {
                return AgendaDto.builder()
                        .id(this.id)
                        .title(this.title)
                        .describe(this.describe)
                        .build();
        }
}
