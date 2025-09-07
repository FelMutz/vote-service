package com.softdesign.vote.repository;

import com.softdesign.vote.entity.AssociateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AssociateRepository extends JpaRepository<AssociateEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE AssociateEntity a SET a.deleteDate = CURRENT_DATE WHERE a.id = :id")
    void softDeleteById(@Param("id") Long id);

    Optional<AssociateEntity> findByDocument(String document);

}
