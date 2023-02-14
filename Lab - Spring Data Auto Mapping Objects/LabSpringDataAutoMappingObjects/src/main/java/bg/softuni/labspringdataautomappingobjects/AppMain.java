package bg.softuni.labspringdataautomappingobjects;

import bg.softuni.labspringdataautomappingobjects.entities.Address;
import bg.softuni.labspringdataautomappingobjects.entities.Employee;
import bg.softuni.labspringdataautomappingobjects.entities.dto.AddressDTO;
import bg.softuni.labspringdataautomappingobjects.entities.dto.CreateEmployeeDTO;
import bg.softuni.labspringdataautomappingobjects.services.AddressService;
import bg.softuni.labspringdataautomappingobjects.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

@Component
public class AppMain implements CommandLineRunner {

    private AddressService addressService;
    private EmployeeService employeeService;

    @Autowired
    public AppMain(AddressService addressService, EmployeeService employeeService) {
        this.addressService = addressService;
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

//        createAddress(scanner);
//        createEmployee(scanner);
        printAllEmployees();
    }

    private void printAllEmployees() {
        this.employeeService.findAll().forEach(System.out::println);
    }

    private void createEmployee(Scanner scanner) {
        String firstName = scanner.nextLine();
        BigDecimal salary = new BigDecimal(scanner.nextLine());
        LocalDate birthday = LocalDate.parse(scanner.nextLine());

//        long addressId = Long.parseLong(scanner.nextLine());
        String country = scanner.nextLine();
        String city = scanner.nextLine();

        AddressDTO address = new AddressDTO(country, city);

        CreateEmployeeDTO employeeDTO = new CreateEmployeeDTO
                (firstName, null, salary, birthday, address);

        Employee employee = employeeService.create(employeeDTO);

        System.out.println(employee);
    }

    private void createAddress(Scanner scanner) {
        String country = scanner.nextLine();
        String city = scanner.nextLine();

        AddressDTO data = new AddressDTO(country, city);

        Address address = addressService.create(data);

        System.out.println(address);
    }
}
