package com.softuni.exercisejson.domain.dtos.users;

import javax.xml.bind.annotation.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserImportDTO {

    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlAttribute
    private Integer age;
}
