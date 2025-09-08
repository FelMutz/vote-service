package com.softdesign.vote.entity;

import com.softdesign.vote.dto.AssociateDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;

@Entity
@Table(name = "associate")
@AllArgsConstructor
@NoArgsConstructor
@SQLRestriction("delete_date IS NULL")
public class AssociateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String document;
    @Column(updatable = false)
    private LocalDate createDate;
    private LocalDate deleteDate;

    public AssociateDto toDTO() {
        return AssociateDto.builder()
                .id(this.id)
                .name(this.name)
                .document(this.document)
                .createDate(this.createDate)
                .build();
    }
}
