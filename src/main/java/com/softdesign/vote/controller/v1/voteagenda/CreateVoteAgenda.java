package com.softdesign.vote.v1.controller.v1.voteagenda;

import com.softdesign.vote.service.voteagenda.VoteAgendaDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateVoteAgenda {
    @NonNull
    private Long agendaID;
    @NonNull
    private Long associateId;
    @NonNull
    private Boolean vote;

    public VoteAgendaDto toDTO() {
        return VoteAgendaDto.builder()
                .agendaId(this.agendaID)
                .associateId(this.associateId)
                .vote(this.vote)
                .build();
    }
}
