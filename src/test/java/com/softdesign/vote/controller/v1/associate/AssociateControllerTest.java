package com.softdesign.vote.controller.v1.associate;

import com.softdesign.vote.service.associate.AssociateFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.softdesign.vote.stubs.AssociateDtoStub.getAssociateDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssociateControllerTest {

    @Mock
    private AssociateFacade associateFacade;

    @InjectMocks
    private AssociateController associateController;

    @Test
    void shouldGetAllAssociates() {
        when(associateFacade.getAllAssociates()).thenReturn(List.of(getAssociateDto()));

        List<Associate> response = associateController.getAssociates();

        assertThat(response).hasSize(1);
        assertThat(response.getFirst().getName()).isEqualTo("Test Associate");
        verify(associateFacade).getAllAssociates();
    }

    @Test
    void shouldGetAssociateById() {
        when(associateFacade.getAssociateById(1L)).thenReturn(getAssociateDto());

        Associate response = associateController.getAssociateById(1L);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("Test Associate");
        verify(associateFacade).getAssociateById(1L);
    }

    @Test
    void shouldCreateAssociate() {
        CreateAssociate createAssociate = new CreateAssociate("New Associate", "12345678900");
        when(associateFacade.createAssociate(any())).thenReturn(getAssociateDto());

        Associate response = associateController.createAssociate(createAssociate);

        assertThat(response.getName()).isEqualTo("Test Associate");
        verify(associateFacade).createAssociate(any());
    }

    @Test
    void shouldUpdateAssociate() {
        UpdateAssociate updateAssociate = new UpdateAssociate(1L, "Updated Associate", "98765432100");
        when(associateFacade.updateAssociate(any())).thenReturn(getAssociateDto());

        Associate response = associateController.updateAssociate(updateAssociate);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("Test Associate");
        verify(associateFacade).updateAssociate(any());
    }

    @Test
    void shouldDeleteAssociate() {
        doNothing().when(associateFacade).deleteAssociate(1L);

        associateController.deleteAssociate(1L);

        verify(associateFacade).deleteAssociate(1L);
    }
}