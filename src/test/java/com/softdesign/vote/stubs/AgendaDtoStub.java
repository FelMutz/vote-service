package com.softdesign.vote.stubs;

import com.softdesign.vote.dto.AgendaDto;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class AgendaDtoStub {
    public static AgendaDto getAgendaDto() {
        return AgendaDto.builder()
                .id(1L)
                .title("Test Agenda")
                .describe("Descrição da pauta")
                .createDate(LocalDate.now())
                .aproved(false)
                .build();
    }
}
