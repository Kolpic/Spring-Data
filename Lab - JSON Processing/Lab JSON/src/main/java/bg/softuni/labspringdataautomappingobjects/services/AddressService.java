package bg.softuni.labspringdataautomappingobjects.services;

import bg.softuni.labspringdataautomappingobjects.entities.dtos.addresses.AddressDTO;
import bg.softuni.labspringdataautomappingobjects.entities.dtos.addresses.CreateAddressDTO;

public interface AddressService {

    AddressDTO create(CreateAddressDTO data);
}
