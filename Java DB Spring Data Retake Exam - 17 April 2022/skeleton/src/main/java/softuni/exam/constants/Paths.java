package softuni.exam.constants;

import java.io.File;
import java.nio.file.Path;

public enum Paths {
    ;
    public final static String COUNTRIES_PATH = "src/main/resources/files/json/countries.json";
    public final static String CITIES_PATH = "src/main/resources/files/json/cities.json";
    public final static File FORECAST_PATH = Path.of("src/main/resources/files/xml/forecasts.xml").toFile();
}
