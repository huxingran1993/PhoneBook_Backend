package com.phonebookbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.lang.annotation.Inherited;

@Document("info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactPerson {
    @Id
    String id;
    @Field("name")
    String name;
    @Field("lastName")
    String lastName;
    @Field("phoneNumber")
    String phoneNumber;

}
