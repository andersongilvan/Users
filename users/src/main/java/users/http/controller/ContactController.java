package users.http.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import users.business.services.ContactService;
import users.http.dtos.ContactRequest;
import users.http.dtos.ContactResponse;
import users.http.dtos.RequestId;
import users.infra.entity.Contact;
import users.infra.mapper.ContactMapper;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;
    private final ContactMapper contactMapper;

    @PostMapping
    public ResponseEntity<ContactResponse> create(
            @Valid
            @RequestBody ContactRequest request,
            @AuthenticationPrincipal(expression = "username") String email
    ) {
        Contact contact = contactMapper.toEntity(request);

        Contact result = contactService.create(contact, email);

        return ResponseEntity.status(HttpStatus.CREATED).body(contactMapper.toContactResponse(result));
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<ContactResponse> show(
            @Valid @PathVariable RequestId requestId,
            @AuthenticationPrincipal(expression = "username") String email
    ) {

        var result = contactService.findOne(requestId.id(), email);

        return ResponseEntity.ok(contactMapper.toContactResponse(result));

    }

    @PutMapping("/{contactId}")
    public ResponseEntity<ContactResponse> update(
            @Valid @PathVariable RequestId requestId,
            @Valid @RequestBody ContactRequest request,
            @AuthenticationPrincipal(expression = "username") String email
    ) {
        var contact = contactMapper.toEntity(request);

        var result = contactService.update(contact, requestId.id(), email);

        return ResponseEntity.ok(contactMapper.toContactResponse(result));
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<Void> delete(
            @Valid @PathVariable RequestId requestId,
            @AuthenticationPrincipal(expression = "username") String email
    ) {

        contactService.delete(requestId.id(), email);

        return ResponseEntity.noContent().build();
    }

}
