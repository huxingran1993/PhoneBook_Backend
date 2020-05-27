package com.phonebookbackend.controller;

import com.phonebookbackend.model.ContactPerson;
import com.phonebookbackend.repository.ContactPersonRepository;
import com.phonebookbackend.services.ContactPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/contacts")
public class ContactPersonController {
    private final ContactPersonService contactPersonService;
    @Autowired
    ContactPersonRepository contactPersonRepository;

    public ContactPersonController(ContactPersonService contactPersonService) {
        this.contactPersonService = contactPersonService;
    }

    @GetMapping()
    public ResponseEntity<List<ContactPerson>> getAllContacts(@RequestParam(required = false) String name){
        try {
            List<ContactPerson> contacts = new ArrayList<ContactPerson>();
            if (name == null)
                contactPersonRepository.findAll().forEach(contacts::add);
            else
                contactPersonRepository.findByName(name).forEach(contacts::add);
            if (contacts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }

        @PostMapping()
        public ContactPerson addContact(@RequestBody ContactPerson contactPerson){
            contactPersonRepository.save(contactPerson);
            return contactPerson;
        }

        @GetMapping("/{id}")
        public ResponseEntity getById(@PathVariable String id){
         try {
             return ResponseEntity.ok(contactPersonService.getById(id).get());
            //contactPersonRepository.findById(id).forEach(contacts::add);
         }catch (Exception e){
             return ResponseEntity.notFound().build();
         }
        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable String id){
        contactPersonService.delete(id);
        }

        @PutMapping("/{id}")
        public ContactPerson update(@PathVariable String id, @RequestBody ContactPerson contactPerson){
            Optional<ContactPerson> optionalContactPerson = contactPersonRepository.findById(id);
            ContactPerson cp = optionalContactPerson.get();
            if (contactPerson.getName()!=null)
                cp.setName(contactPerson.getName());
            if (contactPerson.getLastName()!=null)
                cp.setLastName(contactPerson.getLastName());
            if (contactPerson.getPhoneNumber()!=null)
                cp.setPhoneNumber(contactPerson.getPhoneNumber());
            contactPersonRepository.save(cp);
            return cp;
        }
}
