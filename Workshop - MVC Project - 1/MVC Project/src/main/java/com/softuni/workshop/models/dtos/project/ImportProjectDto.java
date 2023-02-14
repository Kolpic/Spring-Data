package com.softuni.workshop.models.dtos.project;

import com.softuni.workshop.models.dtos.company.ImportCompanyDto;
import jakarta.validation.constraints.NotNull;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportProjectDto {


    @XmlElement
    @NotNull
    private String name;

    @XmlElement
    @NotNull
    private String description;

    @XmlElement(name = "start-date")
    private String startDate;

    @XmlElement(name = "is-Finished")
    private boolean isFinished;

    @XmlElement
    @NotNull
    private BigDecimal payment;

    @NotNull
    @XmlElement(name = "company")
    private ImportCompanyDto company;

    public ImportProjectDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public ImportCompanyDto getCompany() {
        return company;
    }

    public void setCompany(ImportCompanyDto company) {
        this.company = company;
    }
}
