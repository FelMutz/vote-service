package com.softdesign.vote.service.associate;

import com.softdesign.vote.service.cpf.CpfValidatorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class AssociateFacade {
    private final AssociateService associateService;
    private final CpfValidatorService cpfValidatorService;

    public AssociateDto createAssociate(AssociateDto associateDto) {
        cpfValidatorService.isCpfValid(associateDto.getDocument());
        return associateService.createAssociate(associateDto);
    }

    public List<AssociateDto> getAllAssociates() {
        return associateService.getAllAssociates();
    }

    public AssociateDto updateAssociate(AssociateDto associateDto) {
        return associateService.updateAssociate(associateDto);
    }

    public AssociateDto getAssociateById(Long id) {
        return associateService.getAssociateById(id);
    }

    public void deleteAssociate(Long id) {
        associateService.deleteAssociate(id);
    }
}
