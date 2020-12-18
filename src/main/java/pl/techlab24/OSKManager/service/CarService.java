package pl.techlab24.OSKManager.service;

import org.springframework.stereotype.Service;
import pl.techlab24.OSKManager.repository.CarRepository;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

}
