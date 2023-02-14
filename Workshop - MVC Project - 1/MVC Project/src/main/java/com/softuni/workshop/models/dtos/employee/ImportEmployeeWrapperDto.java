package com.softuni.workshop.models.dtos.employee;

import com.softuni.workshop.models.Employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportEmployeeWrapperDto {

    @XmlElement(name = "employee")
    private List<ImportEmployeeDto> employees;

    public ImportEmployeeWrapperDto() {
    }

    public List<ImportEmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<ImportEmployeeDto> employees) {
        this.employees = employees;
    }
}
