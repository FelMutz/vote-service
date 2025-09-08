package com.softdesign.vote.controller.v1.associate;

import com.softdesign.vote.dto.AssociateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Associate {
    private Long id;
    private String name;
    private String document;
    private LocalDate createDate;

    public static List<Associate> from(List<AssociateDto> associateDtos) {
        return associateDtos.stream()
                .map(AssociateDto::toResponse)
                .toList();
    }
}
