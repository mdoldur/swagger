package com.mdoldur.swagger.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mdoldur.swagger.dto.CarDto;
import com.mdoldur.swagger.model.CarEntity;
import com.mdoldur.swagger.repository.CarRepository;
import com.mdoldur.swagger.service.Car;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CarImpl implements Car {
	private final CarRepository carRepository;
	private final ModelMapper modelMapper;
	
	@Override
	public ResponseEntity<CarDto> create(CarDto newCar) {
		// 1. way
		/*CarEntity carEntity = new CarEntity();
		carEntity.setName(newCar.getName());
		carEntity.setModel(newCar.getModelYear());
		carEntity.setPrice(newCar.getPrice());*/
		// 2. way
		CarEntity carEntity = modelMapper.map(newCar, CarEntity.class);
		carEntity = carRepository.save(carEntity);
		
		CarDto newCarDto = CarDto.create(carEntity);
		return new ResponseEntity<CarDto>(newCarDto, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> update(CarDto updateCar) {
		if (updateCar.getCarId() != null && updateCar.getCarId().intValue() > 0) {
			Optional<CarEntity> foundCar = carRepository.findById(updateCar.getCarId());
			if (foundCar.isPresent()) {
				CarEntity car = foundCar.get();
				car.setName(updateCar.getName());
				car.setModel(updateCar.getModelYear());
				car.setPrice(updateCar.getPrice());
				carRepository.save(car);
				return new ResponseEntity<String>("Car Updated", HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("Car Not Found", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<String> delete(Integer id) {
		if (id != null && id.intValue() > 0) {
			Optional<CarEntity> foundCar = carRepository.findById(id);
			if (foundCar.isPresent()) {
				// 1. way
				//carRepository.deleteById(id);
				// 2. way
				carRepository.delete(foundCar.get());
				return new ResponseEntity<>("Car deleted", HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("Car Not Found", HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<CarDto> getCar(Integer id) {
		if (id != null && id.intValue() > 0) {
			Optional<CarEntity> foundCar = carRepository.findById(id);
			if (foundCar.isPresent()) {
				return new ResponseEntity<CarDto>(CarDto.create(foundCar.get()), HttpStatus.OK);
			}
		}
		return new ResponseEntity<CarDto>(HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<List<CarDto>> getAll(Integer pageNumber, Integer pageSize) {
		List<CarEntity> all = null;
		
		if (pageNumber != null && pageSize != null && pageSize.longValue() > 0) {
		    Pageable pageable = PageRequest.of(pageNumber, pageSize);
		    Page<CarEntity> cars = carRepository.findAll(pageable);
		    if (cars.hasContent()) {
		    	all = cars.getContent();
		    } else {
		    	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		    }
			
		} else {
			all = carRepository.findAll();
			if (all.size() == 0) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		}
		
		List<CarDto> result = all
				.stream()
				.map(CarDto::create)
				.collect(Collectors.toList());
		
		return new ResponseEntity<List<CarDto>>(result, HttpStatus.OK);
	}
	
}
