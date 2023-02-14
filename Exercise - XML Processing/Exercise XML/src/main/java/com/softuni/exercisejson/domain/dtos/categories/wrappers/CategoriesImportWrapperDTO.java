package com.softuni.exercisejson.domain.dtos.categories.wrappers;

import javax.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesImportWrapperDTO {

    @XmlElement(name = "category")
    private List<CategoryImportDTO> categories;
}
