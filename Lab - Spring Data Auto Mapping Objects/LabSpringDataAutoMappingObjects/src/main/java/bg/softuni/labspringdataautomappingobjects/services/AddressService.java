package bg.softuni.labspringdataautomappingobjects.services;

import bg.softuni.labspringdataautomappingobjects.entities.Address;
import bg.softuni.labspringdataautomappingobjects.entities.dto.AddressDTO;

public interface AddressService {

    Address create(AddressDTO data);
}
