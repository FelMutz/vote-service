package com.softdesign.vote.stubs;

import com.softdesign.vote.dto.VoteAgendaDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class VoteAgendaDtoStub {
    public static VoteAgendaDto getVoteAgendaDto() {
        return VoteAgendaDto.builder()
                .agendaId(1L)
                .associateId(2L)
                .vote(true)
                .build();
    }
}
