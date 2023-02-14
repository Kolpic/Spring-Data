package softuni.exam.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.models.entity.enums.DayOfWeek;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Time;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "forecasts")
public class Forecast extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek dayOfWeek;

    @Min(value = -20)
    @Max(value = 60)
    @Column(nullable = false)
    private Double maxTemperature;

    @Min(value = -50)
    @Max(value = 40)
    @Column(nullable = false)
    private Double minTemperature;

    @Column(nullable = false)
    private LocalTime sunRice;

    @Column(nullable = false)
    private LocalTime sunSet;

    @ManyToOne
    private City city;

    @Override
    public String toString() {
        return String.format("City: %s:%n\t-min temperature: %.2f%n\t--max temperature: %.2f%n\t" +
                        "---sunrise: %s%n\t----sunset: %s%n"
                , city.getCityName(), minTemperature, maxTemperature, sunRice, sunSet);
    }
}
