package com.softdesign.vote.repository;

import com.softdesign.vote.entity.AgendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AgendaRepository extends JpaRepository<AgendaEntity, Long> {

    @Modifying
    @Query("UPDATE AgendaEntity a SET a.aproved = :aproved WHERE a.id = :agendaId")
    void updateAgendaResult(Long agendaId, Boolean aproved);
}
