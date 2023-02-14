package bg.softuni.labspringdataautomappingobjects.services;

import bg.softuni.labspringdataautomappingobjects.entities.Address;
import bg.softuni.labspringdataautomappingobjects.entities.dtos.addresses.AddressDTO;
import bg.softuni.labspringdataautomappingobjects.entities.dtos.addresses.CreateAddressDTO;
import bg.softuni.labspringdataautomappingobjects.repositories.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService{

    private AddressRepository addressRepository;
    private ModelMapper mapper;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper mapper) {
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    @Override
    public AddressDTO create(CreateAddressDTO data) {
        Address address = mapper.map(data, Address.class);

        Address saved = this.addressRepository.save(address);

        return mapper.map(saved, AddressDTO.class);
    }
}
