package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ForecastImportDto;
import softuni.exam.models.dto.ForecastsWrapperDto;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.entity.enums.DayOfWeek;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.ForecastService;
import softuni.exam.util.ValidationUtils;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static softuni.exam.constants.Messages.INVALID_FORECAST;
import static softuni.exam.constants.Messages.VALID_CITY_FORECAST;
import static softuni.exam.constants.Paths.FORECAST_PATH;
import static softuni.exam.models.entity.enums.DayOfWeek.FRIDAY;

@Service
public class ForecastServiceImpl implements ForecastService {

    private final ForecastRepository forecastRepository;
    private final CityRepository cityRepository;
    private final Gson gson;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    @Autowired
    public ForecastServiceImpl(ForecastRepository forecastRepository, CityRepository cityRepository, Gson gson, ValidationUtils validationUtils, ModelMapper modelMapper, XmlParser xmlParser) {
        this.forecastRepository = forecastRepository;
        this.cityRepository = cityRepository;
        this.gson = gson;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(FORECAST_PATH.toPath());
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        ForecastsWrapperDto forecastsWrapperDto = xmlParser.fromFile(FORECAST_PATH, ForecastsWrapperDto.class);

        List<ForecastImportDto> forecasts = forecastsWrapperDto.getForecast();

        for (ForecastImportDto forecast : forecasts) {
            boolean isValid = validationUtils.isValid(forecast);

            if (isValid) {
                if (cityRepository.findFirstById(forecast.getCity()).isPresent()) {
                    City refCity = cityRepository.findFirstById(forecast.getCity()).get();

                    Forecast forecastToSave = modelMapper.map(forecast, Forecast.class);
                    forecastToSave.setCity(refCity);
                    forecastToSave.setSunRice(LocalTime.parse(forecast.getSunRice(),
                            DateTimeFormatter.ofPattern("HH:mm:ss")));
                    forecastToSave.setSunSet(LocalTime.parse(forecast.getSunSet(),
                            DateTimeFormatter.ofPattern("HH:mm:ss")));

                    forecastRepository.saveAndFlush(forecastToSave);

                    stringBuilder.append(String.format(VALID_CITY_FORECAST,
                            forecastToSave.getDayOfWeek(), forecastToSave.getMaxTemperature()));
                }
                continue;
            } else {
                stringBuilder.append(INVALID_FORECAST);
            }
        }
        return null;
    }

    @Override
    public String exportForecasts() {
        StringBuilder stringBuilder = new StringBuilder();
        Set<Forecast> forecasts = forecastRepository
                .findAllByDayOfWeekAndCity_PopulationLessThanOrderByMaxTemperatureDescIdAsc(DayOfWeek.SUNDAY, 150000L)
                .orElseThrow(NoSuchElementException::new);

       forecasts.forEach(forecast ->  stringBuilder.append(forecast.toString()));

       return stringBuilder.toString();
    }
}
