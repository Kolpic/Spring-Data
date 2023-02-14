package bg.softuni.labspringdataautomappingobjects;

import bg.softuni.labspringdataautomappingobjects.entities.dtos.CountryXMLDTO;
import bg.softuni.labspringdataautomappingobjects.entities.dtos.addresses.AddressXMLDTO;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class XmlTestMain implements CommandLineRunner {

    private final JAXBContext addressContext;

    private final JAXBContext countryContext;

    public XmlTestMain(JAXBContext addressContext, JAXBContext countryContext) {
        this.addressContext = addressContext;
        this.countryContext = countryContext;
    }

    @Override
    public void run(String... args) throws Exception {

        CountryXMLDTO countryXMLDTO = new CountryXMLDTO("Bulgaria");
        AddressXMLDTO addressXMLDTO = new AddressXMLDTO(5, "Bulgaria", "Plovdiv");

//        JAXBContext context = JAXBContext.newInstance(AddressXMLDTO.class);
        Marshaller countryMarshal = countryContext.createMarshaller();
        countryMarshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        countryMarshal.marshal(countryXMLDTO, System.out);

        Marshaller addressMarshal = addressContext.createMarshaller();
        addressMarshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        addressMarshal.marshal(addressXMLDTO, System.out);
        
        
//        <?xml version="1.0" encoding="UTF-8" standalone="yes"?><addressXML><id>5</id><country>Bulgaria</country><city>Plovdiv</city></addressXML>


//        Unmarshaller unmarshaller = context.createUnmarshaller();
//        AddressXMLDTO unmarshal = (AddressXMLDTO) unmarshaller.unmarshal(System.in);
//        System.out.println(unmarshal);
    }


}
