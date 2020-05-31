package com.phonebookbackend.model;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "First name")
    @Field("name")
    String name;

    @ApiModelProperty(value = "Last name")
    @Field("lastName")
    String lastName;

    @ApiModelProperty(value = "Phone Number")
    @Field("phoneNumber")
    String phoneNumber;

}
