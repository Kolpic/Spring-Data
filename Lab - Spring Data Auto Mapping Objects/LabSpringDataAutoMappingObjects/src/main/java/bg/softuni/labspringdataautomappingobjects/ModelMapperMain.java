package bg.softuni.labspringdataautomappingobjects;

import bg.softuni.labspringdataautomappingobjects.entities.Address;
import bg.softuni.labspringdataautomappingobjects.entities.Employee;
import bg.softuni.labspringdataautomappingobjects.entities.dto.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;

//@Component
public class ModelMapperMain implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ModelMapper mapper = new ModelMapper();

//        PropertyMap<Employee, EmployeeDTO> propertyMap = new PropertyMap<Employee, EmployeeDTO>() {
//            @Override
//            protected void configure() {
//                map().setCity(source.getAddress().getCity());
//            }
//        };
//
//        mapper.addMappings(propertyMap);

        TypeMap<Employee, EmployeeDTO> typeMap = mapper.createTypeMap(Employee.class, EmployeeDTO.class);
        typeMap.addMappings(mapping -> mapping.map(source -> source.getAddress().getCity(),
                (destination, value) -> destination.setAddressCity(String.valueOf(value))));
        typeMap.validate();

        Address address = new Address("Bulgaria", "Sofia");
        Employee employee = new Employee("Galin", BigDecimal.TEN, address);

        EmployeeDTO employeeDTO = mapper.map(employee, EmployeeDTO.class);

        System.out.println(employeeDTO);
    }
}
