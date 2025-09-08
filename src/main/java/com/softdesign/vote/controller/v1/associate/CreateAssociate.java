package com.softdesign.vote.controller.v1.associate;

import com.softdesign.vote.dto.AssociateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAssociate {
    private String name;
    private String document;

    public AssociateDto toDTO() {
        return AssociateDto.builder()
                .name(this.name)
                .document(this.document)
                .build();
    }
}
