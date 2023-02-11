package ru.sleeper.demo.contoller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sleeper.demo.dto.SleepDTO;
import ru.sleeper.demo.model.Sleep;
import ru.sleeper.demo.model.User;
import ru.sleeper.demo.service.AppUserService;
import ru.sleeper.demo.service.SleepService;

import java.security.Principal;

@RestController
@RequestMapping(value = "/sleep")
@AllArgsConstructor
public class SleepController {
    private final SleepService sleepService;
    private final AppUserService userService;
    private final ModelMapper mapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SleepDTO> getSleepById(@PathVariable(name = "id") Long id) {
        Sleep sleep = sleepService.findById(id);

        if(sleep == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        
        return ResponseEntity.ok(convertToSleepDTO(sleep));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewSleep(@RequestBody SleepDTO sleepDTO, Principal principal) {
        String userName = principal.getName();

        if(userName == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        sleepService.addSleep(convertToSleep(sleepDTO), userName);

        return ResponseEntity.ok(userName);
    }

    @GetMapping("/temp")
    public ResponseEntity<String> temp(Principal principal) {
        return ResponseEntity.ok("its working");
    }

    private SleepDTO convertToSleepDTO(Sleep sleep) {
        return mapper.map(sleep, SleepDTO.class);
    }

    private Sleep convertToSleep(SleepDTO sleepDTO) {
        return mapper.map(sleepDTO, Sleep.class);
    }
}