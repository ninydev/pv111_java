package com.itstep.spring_template.services.toyota;

import com.itstep.spring_template.models.toyota.CarModel;
import com.itstep.spring_template.repositories.toyota.CarModelRepository;
import com.itstep.spring_template.services.auth.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CarModelService
{
    private final CarModelRepository carModelRepository;
    private final UserService userService;

    public CarModelService(CarModelRepository carModelRepository, UserService userService) {

        this.carModelRepository = carModelRepository;
        this.userService = userService;
    }

    public CarModel save (CarModel model) {
        model.setAuthor(userService.getCurrentUser());
        return carModelRepository.save(model);
    }

    public Page<CarModel> findAll(Pageable pageable) {
        return carModelRepository.findAll(pageable);
    }

    public Optional<CarModel> findById(Long id) {
        return  carModelRepository.findById(id);
    }
}
