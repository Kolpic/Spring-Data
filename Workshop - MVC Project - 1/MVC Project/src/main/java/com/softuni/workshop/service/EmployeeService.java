package com.softuni.workshop.service;

import com.softuni.workshop.models.Employee;
import com.softuni.workshop.models.dtos.employee.ImportEmployeeWrapperDto;
import com.softuni.workshop.repositories.EmployeeRepository;
import com.softuni.workshop.repositories.ProjectRepository;
import com.softuni.workshop.utils.ValidationUtil;
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
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ProjectRepository projectRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    public String getXMLContent() throws IOException {
        Path path = Path.of("src", "main", "resources","files" ,"xmls", "employees.xml");
        List<String> list = Files.readAllLines(path);

        return String.join("\n", list);
    }

    public boolean areImported() {
        return employeeRepository.count() > 0;
    }

    public void importEmployees() throws IOException, JAXBException {
        String employeeXML = getXMLContent();
        ByteArrayInputStream stream = new ByteArrayInputStream(employeeXML.getBytes());
        JAXBContext jaxbContext = JAXBContext.newInstance(ImportEmployeeWrapperDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        ImportEmployeeWrapperDto importProjectDto = (ImportEmployeeWrapperDto) unmarshaller.unmarshal(stream);

        List<Employee> employees = importProjectDto.getEmployees().stream()
                .filter(validationUtil::isValid)
                .map(dto -> modelMapper.map(ImportEmployeeWrapperDto.class, Employee.class))
                .toList();
        employeeRepository.saveAll(employees);
    }
}
