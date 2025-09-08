package com.softdesign.vote.service.associate;

import com.softdesign.vote.dto.AssociateDto;
import com.softdesign.vote.entity.AssociateEntity;
import com.softdesign.vote.repository.AssociateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static com.softdesign.vote.stubs.AssociateDtoStub.getAssociateDto;
import static com.softdesign.vote.stubs.AssociateEntityStub.getAssociateEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssociateServiceTest {

    @Mock
    private AssociateRepository associateRepository;

    @InjectMocks
    private AssociateService associateService;

    @Test
    void shouldCreateAssociateSuccessfully() {
        AssociateDto dto = getAssociateDto();
        when(associateRepository.findByDocument(dto.getDocument())).thenReturn(Optional.empty());
        when(associateRepository.save(any(AssociateEntity.class))).thenReturn(getAssociateEntity());

        AssociateDto response = associateService.createAssociate(dto);

        assertThat(response.getName()).isEqualTo("John Doe");
        verify(associateRepository).save(any(AssociateEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenAssociateAlreadyExists() {
        AssociateDto dto = getAssociateDto();
        when(associateRepository.findByDocument(dto.getDocument())).thenReturn(Optional.of(getAssociateEntity()));

        assertThatThrownBy(() -> associateService.createAssociate(dto))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Associado já cadastrado");

        verify(associateRepository, never()).save(any());
    }

    @Test
    void shouldGetAssociateById() {
        when(associateRepository.findById(1L)).thenReturn(Optional.of(getAssociateEntity()));

        AssociateDto response = associateService.getAssociateById(1L);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("John Doe");
        verify(associateRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenGettingNonExistingAssociate() {
        when(associateRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> associateService.getAssociateById(1L))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Associado não encontrada");
    }

    @Test
    void shouldUpdateAssociateSuccessfully() {
        AssociateDto dto = getAssociateDto();
        when(associateRepository.findById(dto.getId())).thenReturn(Optional.of(getAssociateEntity()));
        when(associateRepository.save(any(AssociateEntity.class))).thenReturn(getAssociateEntity());

        AssociateDto response = associateService.updateAssociate(dto);

        assertThat(response.getId()).isEqualTo(1L);
        verify(associateRepository).save(any(AssociateEntity.class));
    }

    @Test
    void shouldDeleteAssociate() {
        doNothing().when(associateRepository).softDeleteById(1L);

        associateService.deleteAssociate(1L);

        verify(associateRepository).softDeleteById(1L);
    }

    @Test
    void shouldListAllAssociates() {
        when(associateRepository.findAll()).thenReturn(List.of(getAssociateEntity()));

        List<AssociateDto> response = associateService.getAllAssociates();

        assertThat(response).hasSize(1);
        assertThat(response.getFirst().getName()).isEqualTo("John Doe");
        verify(associateRepository).findAll();
    }
}