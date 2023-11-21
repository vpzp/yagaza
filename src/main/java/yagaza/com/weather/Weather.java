package yagaza.com.weather;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Weather {
    @Id
    private LocalDateTime dateTime;

    private long temperature;

    private String region;

    private boolean isRainny;
}
