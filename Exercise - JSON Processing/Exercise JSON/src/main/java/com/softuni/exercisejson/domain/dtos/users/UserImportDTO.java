package com.softuni.exercisejson.domain.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserImportDTO {

    private String firstName;
    private String lastName;
    private Integer age;
}
