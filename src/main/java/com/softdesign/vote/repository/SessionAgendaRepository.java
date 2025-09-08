package com.softdesign.vote.v1.repository;

import com.softdesign.vote.v1.entity.SessionAgendaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface SessionAgendaRepository extends JpaRepository<SessionAgendaEntity, Long> {
    Boolean existsByAgendaIdAndOpenIsTrue(long agendaId);
    List<SessionAgendaEntity> findByOpenIsTrueAndFinishDateBefore(LocalDateTime now);

    @Transactional
    @Modifying
    @Query("UPDATE SessionAgendaEntity s SET s.open = false WHERE s.id IN :ids")
    int closeSessionsByIds(List<Long> ids);
}
