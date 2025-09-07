package com.softdesign.vote.service.associate;

import com.softdesign.vote.entity.AssociateEntity;
import com.softdesign.vote.repository.AssociateRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@Service
class AssociateService {

    private final AssociateRepository associateRepository;

    public AssociateDto createAssociate(AssociateDto associateDTO) {
        associateRepository.findByDocument(associateDTO.getDocument())
                        .ifPresent(a -> {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Associado já cadastrado");
                        });
        return associateRepository.save(associateDTO.toEntity()).toDTO();
    }

    public AssociateDto getAssociateById(Long id) {
        return associateRepository.findById(id)
                .map(AssociateEntity::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Associado não encontrada"));
    }

    public AssociateDto updateAssociate(AssociateDto associateDTO) {
        associateRepository.findById(associateDTO.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Associado não encontrada"));

        return associateRepository.save(associateDTO.toEntity()).toDTO();
    }

    public void deleteAssociate(Long id) {
        associateRepository.softDeleteById(id);
    }

    public List<AssociateDto> getAllAssociates() {
    return associateRepository.findAll().stream()
            .map(AssociateEntity::toDTO)
            .toList();
    }
}
