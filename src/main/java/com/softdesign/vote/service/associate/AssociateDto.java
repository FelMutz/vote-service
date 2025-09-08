package com.softdesign.vote.v1.service.associate;

import com.softdesign.vote.controller.v1.associate.Associate;
import com.softdesign.vote.v1.entity.AssociateEntity;
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
public class AssociateDto {
    private Long id;
    private String name;
    private String document;
    private LocalDate createDate;

    public AssociateEntity toEntity() {
        var date = Optional.ofNullable(this.createDate).orElse(LocalDate.now());
        return new AssociateEntity(this.id, this.name, this.document, date, null);
    }

    public Associate toResponse() {
        return Associate.builder()
                .id(this.id)
                .name(this.name)
                .document(this.document)
                .createDate(this.createDate)
                .build();
    }

}
