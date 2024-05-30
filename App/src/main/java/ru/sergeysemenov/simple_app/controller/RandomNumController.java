package ru.sergeysemenov.simple_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sergeysemenov.simple_app.RandomNumService.RandomNumService;

@RestController
@RequestMapping("/api/v1/random-num")
public class RandomNumController {

    @Autowired
    RandomNumService randomNumService;

    @GetMapping("/")
    public Double getRandomNum() {
        return randomNumService.getRandomNum();
    }

    @GetMapping("/range")
    public Double getRandomNumInRange(@RequestParam(name = "min", defaultValue = "0.0" ) Double min,
                                      @RequestParam(name = "max", defaultValue = "10.0") Double max) {
        return randomNumService.getRandomNumInRange(min, max);
    }

}
