package com.softdesign.vote.repository;

import com.softdesign.vote.entity.VoteAgendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VoteAgendaRepository extends JpaRepository<VoteAgendaEntity, Long> {
    Optional<VoteAgendaEntity> findByAgendaIdAndAssociateId(Long agendaId, Long associateId);
    List<VoteAgendaEntity> findByAgendaId(Long agendaId);

    @Query("SELECT SUM(CASE WHEN v.vote = true THEN 1 ELSE -1 END) FROM VoteAgendaEntity v WHERE v.agendaId = :agendaId")
    Integer voteBalance(@Param("agendaId") Long agendaId);
}
