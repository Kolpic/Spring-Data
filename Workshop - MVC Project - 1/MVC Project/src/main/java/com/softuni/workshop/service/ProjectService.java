package com.softuni.workshop.service;

import com.softuni.workshop.models.Project;
import com.softuni.workshop.models.dtos.project.ImportProjectsDto;
import com.softuni.workshop.repositories.CompanyRepository;
import com.softuni.workshop.repositories.ProjectRepository;
import com.softuni.workshop.utils.ValidationUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validationUtil;

    public ProjectService(ProjectRepository projectRepository,
                          CompanyRepository companyRepository,
                          ModelMapper modelMapper, ValidationUtilImpl validationUtil) {
        this.projectRepository = projectRepository;
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    public String getXMLContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files","xmls", "projects.xml");
        List<String> list = Files.readAllLines(path);

        return String.join("\n", list);
    }

    public void importProjects() throws JAXBException, IOException {
        String xmlContent = getXMLContent();
        ByteArrayInputStream stream = new ByteArrayInputStream(xmlContent.getBytes());

        JAXBContext jaxbContext = JAXBContext.newInstance(ImportProjectsDto.class);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ImportProjectsDto projectsDTO = (ImportProjectsDto) unmarshaller.unmarshal(stream);

        List<Project> projects = projectsDTO.getProjects()
                .stream()
                .filter(validationUtil::isValid)
                .map(importProjectDto -> modelMapper.map(importProjectDto, Project.class))
                .filter(project -> companyRepository.findByName(project.getCompany().getName()).isPresent())
                .map(project -> new Project(project.getName(),
                        project.getDescription(),
                        project.isFinished(),
                        project.getPayment(),
                        project.getStartDate(),
                        companyRepository.findByName(project.getCompany().getName()).get()))
                .toList();

        projectRepository.saveAll(projects);
    }

    public boolean areImported() {
        return projectRepository.count() > 0;
    }
}
