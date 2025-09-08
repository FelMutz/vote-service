package com.softdesign.vote.controller.v1.associate;

import com.softdesign.vote.dto.AssociateDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAssociate {
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String document;

    public AssociateDto toDTO() {
        return AssociateDto.builder()
                .id(this.id)
                .name(this.name)
                .document(this.document)
                .build();
    }
}
