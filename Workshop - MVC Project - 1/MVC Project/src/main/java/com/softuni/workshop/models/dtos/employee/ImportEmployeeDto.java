package com.softuni.workshop.models.dtos.employee;

import com.softuni.workshop.models.Project;
import com.softuni.workshop.models.dtos.project.ImportProjectDto;
import com.softuni.workshop.models.dtos.project.ImportProjectsDto;
import jakarta.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportEmployeeDto {

    @XmlElement(name = "first-name")
    @NotNull
    private String firstName;

    @XmlElement(name = "last-name")
    @NotNull
    private String lastName;

    @XmlElement
    private int age;

    @XmlElement(name = "project")
    private List<ImportProjectDto> projects;

    public ImportEmployeeDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<ImportProjectDto> getProjects() {
        return projects;
    }

    public void setProjects(List<ImportProjectDto> projects) {
        this.projects = projects;
    }
}
