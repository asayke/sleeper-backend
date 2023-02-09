package ru.sleeper.demo.contoller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sleeper.demo.dto.SleepDTO;
import ru.sleeper.demo.model.Sleep;
import ru.sleeper.demo.service.SleepService;

@RestController
@RequestMapping(value = "/sleep")
public class SleepController {
    private final SleepService sleepService;
    private final ModelMapper mapper;

    @Autowired
    public SleepController(SleepService sleepService, ModelMapper mapper) {
        this.sleepService = sleepService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SleepDTO> getSleepById(@PathVariable(name = "id") Long id) {
        Sleep sleep = sleepService.findById(id);

        if(sleep == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        
        return ResponseEntity.ok(convertToSleepDTO(sleep));
    }

    private SleepDTO convertToSleepDTO(Sleep sleep) {
        return mapper.map(sleep, SleepDTO.class);
    }
}