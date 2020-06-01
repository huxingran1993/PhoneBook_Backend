package com.phonebookbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonebookbackend.model.ContactPerson;
import com.phonebookbackend.repository.UserRepository;
import com.phonebookbackend.security.SecurityConfig;
import com.phonebookbackend.services.ContactPersonService;
import com.phonebookbackend.services.impl.ContactPersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(controllers = ContactPersonController.class, excludeAutoConfiguration = SecurityConfig.class,
        useDefaultFilters = false, includeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ContactPersonController.class)})
/**
 * ActiveProfiles is a class-level annotation that is used to declare which active bean definition
 * profiles should be used when loading an ApplicationContext for test classes.
 * */
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)

public class ContactPersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactPersonServiceImpl contactPersonService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<ContactPerson> contactPersonList;

    @BeforeEach
    void setUp(){
        this.contactPersonList = new ArrayList<>();
        this.contactPersonList.add(new ContactPerson("1","Xing","Hu","+32888"));
        this.contactPersonList.add(new ContactPerson("22","Xi","JinPing","+3288778"));
        this.contactPersonList.add(new ContactPerson("33","Obama","Hu","+32885618"));
        this.contactPersonList.add(new ContactPerson("44","Trump","Hu","+32884208"));

        objectMapper.registerModule(new ProblemModule());
        objectMapper.registerModule(new ConstraintViolationProblemModule());
    }

    @Test
    void shouldFetchAllContactPerson() throws Exception{
        given(contactPersonService.getAll()).willReturn(contactPersonList);
        this.mockMvc.perform(get("/api/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(contactPersonList.size())));
    }

    @Test
    void shouldFetchOneById() throws Exception{
        final String id = "1";
        final ContactPerson contactPerson = new ContactPerson("1","Xing","Hu","+32888");

        given(contactPersonService.getById(id)).willReturn(Optional.of(contactPerson));

        this.mockMvc.perform(get("/api/contacts/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(contactPerson.getName())))
                .andExpect(jsonPath("$.lastName", is(contactPerson.getLastName())))
                .andExpect(jsonPath("$.phoneNumber", is(contactPerson.getPhoneNumber())));
    }
}
