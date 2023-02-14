package bg.softuni.labspringdataautomappingobjects.entities.dtos.addresses;

import com.google.gson.annotations.Expose;

public class AddressDTO extends CreateAddressDTO {

    @Expose
    private Long id;

    public AddressDTO(String country, String city) {
        super(country, city);
    }

    public AddressDTO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
