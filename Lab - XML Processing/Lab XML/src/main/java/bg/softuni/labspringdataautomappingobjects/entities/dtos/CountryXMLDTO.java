package bg.softuni.labspringdataautomappingobjects.entities.dtos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "countryXML")
@XmlAccessorType(XmlAccessType.FIELD)
public class CountryXMLDTO {

    @XmlElement
    private String country;

    public CountryXMLDTO(String country) {
        this.country = country;
    }

    public CountryXMLDTO() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
