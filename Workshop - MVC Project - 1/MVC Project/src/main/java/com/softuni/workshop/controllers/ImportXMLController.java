package com.softuni.workshop.controllers;

import com.softuni.workshop.service.CompanyService;
import com.softuni.workshop.service.EmployeeService;
import com.softuni.workshop.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class ImportXMLController {

    private final CompanyService companyService;
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    public ImportXMLController(CompanyService companyService, ProjectService projectService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/import/xml")
    public String index(Model model) {
        boolean companiesImported = companyService.areImported();
        boolean projectsImported = projectService.areImported();
        boolean employeeImported = employeeService.areImported();

        boolean[] importStatuses = { companiesImported, projectsImported, employeeImported };

        model.addAttribute("areImported", importStatuses);

        return "xml/import-xml";
    }

    @GetMapping("/import/companies")
    public String viewImportCompanies(Model model) throws IOException {
        String companiesXML = companyService.getXMLContent();

        model.addAttribute("companies", companiesXML);

        return "xml/import-companies";
    }

    @PostMapping("/import/companies")
    public String importCompanies() throws JAXBException, IOException {
        companyService.importCompanies();

        return "redirect:/import/xml";
    }

    @GetMapping("/import/projects")
    public String viwImportProjects(Model model) throws IOException {
        String projectsXML = projectService.getXMLContent();

        model.addAttribute("projects", projectsXML);

        return "xml/import-projects";
    }

    @PostMapping("/import/projects")
    public String importProjects() throws JAXBException, IOException {
        projectService.importProjects();

        return "redirect:/import/xml";
    }

    @GetMapping("/import/employees")
    public String viewEmployees(Model model) throws IOException {
        String employeesXML = employeeService.getXMLContent();

        model.addAttribute("employees", employeesXML);

        return "xml/import-employees";
    }

    @PostMapping("/import/employees")
    public String importEmployees() throws IOException, JAXBException {
        employeeService.importEmployees();

        return "redirect:/import/xml";
    }

}