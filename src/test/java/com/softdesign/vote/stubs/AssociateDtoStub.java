package com.softdesign.vote.stubs;

import com.softdesign.vote.dto.AssociateDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AssociateDtoStub {
    public static AssociateDto getAssociateDto() {
        return AssociateDto.builder()
                .id(1L)
                .name("Test Associate")
                .document("12345678900")
                .build();
    }
}
