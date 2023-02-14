package com.softuni.exercisejson.domain.dtos.users.wrappers;

import com.softuni.exercisejson.domain.dtos.users.UserImportDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersImportWrapperDTO {

    @XmlElement(name = "user")
    private List<UserImportDTO> users;
}
