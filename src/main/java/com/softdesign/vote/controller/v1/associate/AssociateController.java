package com.softdesign.vote.v1.controller.v1.associate;

import com.softdesign.vote.service.associate.AssociateFacade;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/associate")
@AllArgsConstructor
public class AssociateController {

    private final AssociateFacade associateFacade;

    @GetMapping("/associates")
    public List<Associate> getAssociates() {
        return Associate.from(associateFacade.getAllAssociates());
    }

    @GetMapping("/{id}")
    public Associate getAssociateById(@PathVariable Long id) {
        return associateFacade.getAssociateById(id).toResponse();
    }

    @PostMapping()
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    public Associate createAssociate(@RequestBody @Valid CreateAssociate createAssociate) {
        return associateFacade.createAssociate(createAssociate.toDTO()).toResponse();
    }

    @PutMapping()
    public Associate updateAssociate(@RequestBody @Valid UpdateAssociate associate) {
        return associateFacade.updateAssociate(associate.toDTO()).toResponse();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = org.springframework.http.HttpStatus.NO_CONTENT)
    public void deleteAssociate(@PathVariable Long id) {
        associateFacade.deleteAssociate(id);
    }
}
