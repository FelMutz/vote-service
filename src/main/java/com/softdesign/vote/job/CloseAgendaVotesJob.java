package com.softdesign.vote.job;


import com.softdesign.vote.service.agenda.AgendaFacade;
import com.softdesign.vote.dto.SessionAgendaDto;
import com.softdesign.vote.service.sessionagenda.SessionAgendaFacade;
import com.softdesign.vote.service.voteagenda.VoteAgendaFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import static java.lang.Boolean.TRUE;

@Component
@Slf4j
@AllArgsConstructor
public class CloseAgendaVotesJob {

    private SessionAgendaFacade sessionAgendaFacade;
    private VoteAgendaFacade voteAgendaFacade;
    private AgendaFacade agendaFacade;

    @Scheduled(cron = "${jobs.session-agenda.cron}")
    @SchedulerLock(name = "myScheduledJob", lockAtLeastFor = "PT4M", lockAtMostFor = "PT10M")
    @Transactional
    public void closeAgendaVotes() {
        log.info("Fechando votações das pautas que atingiram o tempo limite...");
        var expiredAgendas = sessionAgendaFacade.findByOpenIsTrueAndFinishDateBefore();
        if(ObjectUtils.isEmpty(expiredAgendas)) {
            log.info("Nenhuma pauta expirou no momento.");
            return;
        }

        var ids = expiredAgendas.stream()
                        .map(SessionAgendaDto::getId)
                                .toList();
        sessionAgendaFacade.closeSession(ids);
        expiredAgendas.forEach(sessionAgendaDto -> {
            var result = voteAgendaFacade.voteBalance(sessionAgendaDto.getAgendaId());
            agendaFacade.updateAgendaResult(sessionAgendaDto.getAgendaId(), result);
            log.info("Pauta ID {} fechada com resultado: {}", sessionAgendaDto.getAgendaId(), TRUE.equals(result) ? "Aprovada" : "Rejeitada");
        });
        log.info("Processo de fechamento de votações concluído.");
    }
}
