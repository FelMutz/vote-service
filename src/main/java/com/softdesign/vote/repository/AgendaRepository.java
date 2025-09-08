package com.softdesign.vote.v1.repository;

import com.softdesign.vote.v1.entity.AgendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AgendaRepository extends JpaRepository<AgendaEntity, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE AgendaEntity a SET a.aproved = :aproved WHERE a.id = :agendaId")
    void updateAgendaResult(Long agendaId, Boolean aproved);
}
