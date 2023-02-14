package bg.softuni.labspringdataautomappingobjects.services;

import bg.softuni.labspringdataautomappingobjects.entities.Address;
import bg.softuni.labspringdataautomappingobjects.entities.Employee;
import bg.softuni.labspringdataautomappingobjects.entities.dtos.CreateEmployeeDTO;
import bg.softuni.labspringdataautomappingobjects.entities.dtos.EmployeeDTO;
import bg.softuni.labspringdataautomappingobjects.repositories.AddressRepository;
import bg.softuni.labspringdataautomappingobjects.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private AddressRepository addressRepository;
    private EmployeeRepository employeeRepository;
    private ModelMapper mapper;

    public EmployeeServiceImpl(AddressRepository addressRepository, EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Employee create(CreateEmployeeDTO employeeDTO) {
//        ModelMapper modelMapper = new ModelMapper();

        Employee employee = mapper.map(employeeDTO, Employee.class);

        Optional<Address> address = addressRepository.findByCountryAndCity(
                employeeDTO.getAddress().getCountry(),
                employeeDTO.getAddress().getCity());

        address.ifPresent(employee::setAddress);

        return employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeDTO> findAll() {

        return employeeRepository.findAll()
                .stream()
                .map(e -> mapper.map(e, EmployeeDTO.class))
                .collect(Collectors.toList());
    }
}
