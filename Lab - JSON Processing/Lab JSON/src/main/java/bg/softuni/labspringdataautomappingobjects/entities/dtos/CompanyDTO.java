package bg.softuni.labspringdataautomappingobjects.entities.dtos;

import com.google.gson.annotations.Expose;

import java.util.List;

public class CompanyDTO {
    @Expose
    private String name;
    @Expose
    private List<CreateEmployeeDTO> employees;

    public CompanyDTO(String name, List<CreateEmployeeDTO> employees) {
        this.name = name;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CreateEmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<CreateEmployeeDTO> employees) {
        this.employees = employees;
    }
}
