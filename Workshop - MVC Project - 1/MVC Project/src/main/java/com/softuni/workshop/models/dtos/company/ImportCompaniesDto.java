package com.softuni.workshop.models.dtos.company;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportCompaniesDto {

    @XmlElement(name = "company")
    private List<ImportCompanyDto> companies;

    public ImportCompaniesDto() {
    }

    public ImportCompaniesDto(List<ImportCompanyDto> companies) {
        this.companies = companies;
    }

    public List<ImportCompanyDto> getCompanies() {
        return companies;
    }

    public void setCompanies(List<ImportCompanyDto> companies) {
        this.companies = companies;
    }
}
