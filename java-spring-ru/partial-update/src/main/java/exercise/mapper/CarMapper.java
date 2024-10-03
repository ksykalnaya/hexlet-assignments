package exercise.mapper;

import exercise.controller.CarsController;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import exercise.dto.CarCreateDTO;
import exercise.dto.CarUpdateDTO;
import exercise.dto.CarDTO;
import exercise.model.Car;
import org.springframework.beans.factory.annotation.Autowired;

// BEGIN
@Mapper(
        uses = { JsonNullableMapper.class },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public class CarMapper {
    @Autowired
    private JsonNullableMapper jsonNullableMapper;

    public CarDTO map(Car car){
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setModel(car.getModel());
        carDTO.setManufacturer(car.getManufacturer());
        carDTO.setEnginePower(car.getEnginePower());
        carDTO.setCreatedAt(car.getCreatedAt());
        carDTO.setUpdatedAt(car.getUpdatedAt());
        return carDTO;
    }
    public Car map(CarCreateDTO carDTO){
        Car car = new Car();
        car.setModel(carDTO.getModel());
        car.setManufacturer(carDTO.getManufacturer());
        car.setEnginePower(carDTO.getEnginePower());
        return car;
    }
    public void update(CarUpdateDTO carUpdateDTO, @MappingTarget Car car){
        if (carUpdateDTO == null){
            return;
        }
        if (jsonNullableMapper.isPresent(carUpdateDTO.getModel())){
            car.setModel(jsonNullableMapper.unwrap(carUpdateDTO.getModel()));
        }
        if (jsonNullableMapper.isPresent(carUpdateDTO.getManufacturer())){
            car.setManufacturer(jsonNullableMapper.unwrap(carUpdateDTO.getManufacturer()));
        }
        if (jsonNullableMapper.isPresent(carUpdateDTO.getEnginePower())){
            car.setEnginePower(jsonNullableMapper.unwrap(carUpdateDTO.getEnginePower()));
        }
    }
}
// END
