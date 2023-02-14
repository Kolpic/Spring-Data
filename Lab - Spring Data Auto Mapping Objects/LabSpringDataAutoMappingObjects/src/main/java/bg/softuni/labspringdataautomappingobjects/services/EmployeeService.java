package bg.softuni.labspringdataautomappingobjects.services;

import bg.softuni.labspringdataautomappingobjects.entities.Employee;
import bg.softuni.labspringdataautomappingobjects.entities.dto.CreateEmployeeDTO;
import bg.softuni.labspringdataautomappingobjects.entities.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    Employee create(CreateEmployeeDTO employeeDTO);

    List<EmployeeDTO> findAll();
}
