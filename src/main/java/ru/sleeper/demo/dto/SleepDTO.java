package ru.sleeper.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.sleeper.demo.model.User;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SleepDTO {
    private Integer noise;

    private Integer quality;

    private Date went_sleep;

    private Date waked_up_at;

    private Integer fell_asleep_during;
}