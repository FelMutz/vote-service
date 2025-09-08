package com.softdesign.vote.stubs;

import com.softdesign.vote.entity.AssociateEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AssociateEntityStub {

    public static AssociateEntity getAssociateEntity() {
        return AssociateEntity.builder()
                .id(1L)
                .name("John Doe")
                .document("12345678900")
                .createDate(LocalDate.now())
                .build();
    }
}