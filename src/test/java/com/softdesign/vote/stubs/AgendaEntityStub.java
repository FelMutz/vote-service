package com.softdesign.vote.stubs;

import com.softdesign.vote.entity.AgendaEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AgendaEntityStub {
    public static AgendaEntity getAgendaEntity() {
        return AgendaEntity.builder()
                .id(1L)
                .title("Test Agenda")
                .build();
    }
}
