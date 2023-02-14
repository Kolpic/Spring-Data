package com.softuni.workshop.models.dtos.project;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportProjectsDto {

    @XmlElement(name = "project")
    private List<ImportProjectDto> projects;

    public ImportProjectsDto() {
    }

    public List<ImportProjectDto> getProjects() {
        return projects;
    }

    public void setProjects(List<ImportProjectDto> projects) {
        this.projects = projects;
    }
}
