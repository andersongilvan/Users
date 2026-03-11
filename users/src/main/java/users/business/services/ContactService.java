package users.business.services;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import users.business.utils.NormalizeUtils;
import users.infra.entity.Contact;
import users.infra.entity.User;
import users.infra.exceptions.NotFoundException;
import users.infra.repository.ContactRepository;
import users.infra.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ContactService {

    private final UserRepository userRepository;

    private final ContactRepository contactRepository;


    @Transactional
    public Contact create(Contact contact, String username) {

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("User not found."));

        contact.setUser(user);
        contact.setPhone(NormalizeUtils.trim(contact.getPhone()));

        return contactRepository.save(contact);
    }

    public List<Contact> allByUser(String username) {

        return contactRepository.findAllByUser(username);

    }

    public Contact findOne(Long contactId, String username) {

        return contactRepository.findOne(contactId, username)
                .orElseThrow(() -> new NotFoundException("Contact not found."));
    }

    @Transactional
    public Contact update(Contact data, Long idContact, String username) {
        Contact contact = contactRepository.findOne(idContact, username)
                .orElseThrow(() -> new NotFoundException("Contact not found."));

        contact.setPhone(NormalizeUtils.trim(data.getPhone()));

        return contactRepository.save(contact);
    }

    @Transactional
    public void delete(Long contactId, String username) {

        contactRepository.findOne(contactId, username)
                .orElseThrow(() -> new NotFoundException("Contact not found."));

        contactRepository.delete(contactId, username);

    }

}
