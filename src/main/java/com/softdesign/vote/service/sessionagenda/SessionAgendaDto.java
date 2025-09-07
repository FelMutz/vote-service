package com.softdesign.vote.service.sessionagenda;

import com.softdesign.vote.entity.SessionAgendaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionAgendaDto {
    private Long id;
    private Long agendaId;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private Boolean open;

    public SessionAgendaEntity toEntity() {
        return new SessionAgendaEntity(
                this.id,
                this.agendaId,
                this.startDate,
                this.finishDate,
                this.open
        );
    }
}
