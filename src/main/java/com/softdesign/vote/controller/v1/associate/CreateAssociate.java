package com.softdesign.vote.v1.controller.v1.associate;

import com.softdesign.vote.service.associate.AssociateDto;
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
