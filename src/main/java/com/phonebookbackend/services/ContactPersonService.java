package com.phonebookbackend.services;

import com.phonebookbackend.model.ContactPerson;

import java.util.List;
import java.util.Optional;

public interface ContactPersonService {
    List<ContactPerson> getAll();
    Optional<ContactPerson> getById(String id);
    ContactPerson createContactPerson(ContactPerson contactPerson);
    ContactPerson update(ContactPerson contactPerson);
    void delete(final String id);

}
