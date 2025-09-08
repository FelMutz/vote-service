package com.softdesign.vote.service.voteagenda;

import com.softdesign.vote.dto.VoteAgendaDto;
import com.softdesign.vote.entity.VoteAgendaEntity;
import com.softdesign.vote.repository.VoteAgendaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static com.softdesign.vote.stubs.VoteAgendaDtoStub.getVoteAgendaDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VoteAgendaServiceTest {

    @Mock
    private VoteAgendaRepository voteAgendaRepository;

    @InjectMocks
    private VoteAgendaService voteAgendaService;

    private VoteAgendaDto voteDto;

    @BeforeEach
    void setUp() {
        voteDto = getVoteAgendaDto();
    }

    @Test
    void shouldCreateVoteAgendaWhenNotVoted() {
        when(voteAgendaRepository.findByAgendaIdAndAssociateId(1L, 2L))
                .thenReturn(Optional.empty());
        when(voteAgendaRepository.save(any(VoteAgendaEntity.class)))
                .thenReturn(voteDto.toEntity());

        voteAgendaService.createVoteAgenda(voteDto);

        verify(voteAgendaRepository).save(any(VoteAgendaEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenAssociateAlreadyVoted() {
        when(voteAgendaRepository.findByAgendaIdAndAssociateId(1L, 2L))
                .thenReturn(Optional.of(voteDto.toEntity()));

        assertThatThrownBy(() -> voteAgendaService.createVoteAgenda(voteDto))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Associado já votou nessa pauta");

        verify(voteAgendaRepository, never()).save(any());
    }

    @Test
    void shouldReturnTrueWhenBalancePositive() {
        when(voteAgendaRepository.voteBalance(1L)).thenReturn(3);

        Boolean result = voteAgendaService.voteBalance(1L);

        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnFalseWhenBalanceZeroOrNull() {
        when(voteAgendaRepository.voteBalance(1L)).thenReturn(0);
        assertThat(voteAgendaService.voteBalance(1L)).isFalse();

        when(voteAgendaRepository.voteBalance(1L)).thenReturn(null);
        assertThat(voteAgendaService.voteBalance(1L)).isFalse();
    }

    @Test
    void shouldReturnFalseWhenBalanceNegative() {
        when(voteAgendaRepository.voteBalance(1L)).thenReturn(-2);
        Boolean result = voteAgendaService.voteBalance(1L);
        assertThat(result).isFalse();
    }
}