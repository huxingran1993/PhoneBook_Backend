package com.phonebookbackend.service;

import com.phonebookbackend.model.ContactPerson;
import com.phonebookbackend.repository.ContactPersonRepository;
import com.phonebookbackend.services.impl.ContactPersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ContactPersonServiceTest {
    @Mock
    private ContactPersonRepository contactPersonRepository;

    @InjectMocks
    private ContactPersonServiceImpl contactPersonService;

    @Test
    void shouldReturnFindAll(){
        List<ContactPerson> datas = new ArrayList<>();
        datas.add(new ContactPerson("1","Xing","Hu","+32888"));
        datas.add(new ContactPerson("22","Xi","JinPing","+3288778"));
        datas.add(new ContactPerson("33","Obama","Hu","+32885618"));
        datas.add(new ContactPerson("44","Trump","Hu","+32884208"));
        given(contactPersonRepository.findAll()).willReturn(datas);
        List<ContactPerson> expected = contactPersonService.getAll();
        assertEquals(expected, datas);

    }

    @Test
    void findContactPersonById(){
        final String id = "1";
        final ContactPerson contactPerson = new ContactPerson("1","Xing","Hu","+32888");
        given(contactPersonRepository.findById(id)).willReturn(Optional.of(contactPerson));
        final Optional<ContactPerson> expected = contactPersonService.getById(id);
        assertNotNull(expected);
    }
}
