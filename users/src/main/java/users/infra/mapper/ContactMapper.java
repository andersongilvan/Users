package users.infra.mapper;

import org.springframework.stereotype.Component;
import users.http.dtos.ContactRequest;
import users.http.dtos.ContactResponse;
import users.infra.entity.Contact;

@Component
public class ContactMapper {

    public Contact toEntity(ContactRequest request) {
        return Contact
                .builder()
                .phone(request.phone())
                .build();
    }

    public ContactResponse toContactResponse(Contact contact) {
        return ContactResponse
                .builder()
                .id(contact.getId())
                .phone(contact.getPhone())
                .username(contact.getUser().getUsername())
                .build();
    }

}
