package ru.sergeysemenov.simple_app.RandomNumService;

import org.springframework.stereotype.Service;

@Service
public class RandomNumService {

    public double getRandomNum() {
        return Math.random();
    }

    public double getRandomNumInRange(double min, double max) {
        return Math.random() * (max - min) + min;
    }
}
