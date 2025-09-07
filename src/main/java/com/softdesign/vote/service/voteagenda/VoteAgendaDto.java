package com.softdesign.vote.service.voteagenda;

import com.softdesign.vote.entity.VoteAgendaEntity;
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
public class VoteAgendaDto {
    private Long id;
    private Long agendaId;
    private Long associateId;
    private Boolean vote;
    private LocalDate createDate;

    public VoteAgendaEntity toEntity() {
        var date = Optional.ofNullable(this.createDate).orElse(LocalDate.now());
        return new VoteAgendaEntity(this.id, this.agendaId, this.associateId, vote, date);
    }
}
