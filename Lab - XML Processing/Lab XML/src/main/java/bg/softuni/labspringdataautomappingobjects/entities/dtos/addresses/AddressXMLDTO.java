package bg.softuni.labspringdataautomappingobjects.entities.dtos.addresses;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "addressXML")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressXMLDTO {

    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "country")
    private String country;
    @XmlElement(name = "city")
    private String city;

    public AddressXMLDTO(int id, String country, String city) {
        this.id = id;
        this.country = country;
        this.city = city;
    }

    public AddressXMLDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "AddressXMLDTO{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
