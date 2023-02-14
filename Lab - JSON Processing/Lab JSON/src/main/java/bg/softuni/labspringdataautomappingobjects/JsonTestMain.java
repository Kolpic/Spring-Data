package bg.softuni.labspringdataautomappingobjects;

import bg.softuni.labspringdataautomappingobjects.entities.dtos.addresses.CreateAddressDTO;
import bg.softuni.labspringdataautomappingobjects.entities.dtos.CompanyDTO;
import bg.softuni.labspringdataautomappingobjects.entities.dtos.CreateEmployeeDTO;
import com.google.gson.*;
import org.springframework.boot.CommandLineRunner;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

//@Component
public class JsonTestMain implements CommandLineRunner {

//    {
//        "firstName": "Pistaka",
//            "lastName": "Metanov",
//            "salary": 10,
//            "birthday": {},
//        "address": {
//        "country": "Bulgaria",
//                "city": "Plovdiv"
//    }
//    }
    class LocalDateAdapter implements JsonSerializer<LocalDate> {

        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
        }
}

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation() // za da gi vzeme katp gson obekt pred field-a trqbva da ima anotaciq @Expose
                .setDateFormat("YYYY-MM-DD") // za printene na godini na konzolata
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()) // printene na godini
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfT, context) -> null) // chetene na data ot konzolata
                .setPrettyPrinting() // za vzimane na obekta na nqkolko reda, po-lesno za chetene
                .create();

        CreateAddressDTO address1 = new CreateAddressDTO("Bulgaria", "Burgas");
        CreateEmployeeDTO employee1 = new CreateEmployeeDTO
                ("First","Last", BigDecimal.TEN, LocalDate.now(), address1);

        CreateAddressDTO address2 = new CreateAddressDTO("Bulgaria", "Plovdiv");
        CreateEmployeeDTO employee2 = new CreateEmployeeDTO
                ("Second","Last", BigDecimal.ZERO, LocalDate.now(), address2);

        CreateAddressDTO address3 = new CreateAddressDTO("Bulgaria", "Sofia");
        CreateEmployeeDTO employee3 = new CreateEmployeeDTO
                ("Third","Last", BigDecimal.ONE, LocalDate.now(), address3);

        CompanyDTO companyDTO = new CompanyDTO("Mega", List.of(employee1, employee2, employee3));

        System.out.println(gson.toJson(companyDTO));

    }
}
