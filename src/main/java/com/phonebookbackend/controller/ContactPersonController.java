package com.phonebookbackend.controller;

import com.phonebookbackend.model.ContactPerson;
import com.phonebookbackend.repository.ContactPersonRepository;
import com.phonebookbackend.services.ContactPersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api(tags = "ContactPersonController")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/contacts")
public class ContactPersonController {
    private final ContactPersonService contactPersonService;
    @Autowired
    ContactPersonRepository contactPersonRepository;

    //private static final Logger LOGGER = LogFactory.getLogger()
    public ContactPersonController(ContactPersonService contactPersonService) {
        this.contactPersonService = contactPersonService;
    }

    @ApiOperation("Get all contact info")
    @GetMapping()
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")

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

        @ApiOperation("Add contact info")
        @PostMapping()
        public ContactPerson addContact(@RequestBody ContactPerson contactPerson){
            contactPersonRepository.save(contactPerson);
            return contactPerson;
        }

        @ApiOperation("Get contact info by id")
        @GetMapping("/{id}")
        @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")

        public ResponseEntity getById(@PathVariable String id){
         try {
             return ResponseEntity.ok(contactPersonService.getById(id).get());
            //contactPersonRepository.findById(id).forEach(contacts::add);
         }catch (Exception e){
             return ResponseEntity.notFound().build();
         }
        }

        @ApiOperation("Delete specific contact info")
        @DeleteMapping("/{id}")
        public void delete(@PathVariable String id){
        contactPersonService.delete(id);
        }

        @ApiOperation("Update specific contact info")
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
