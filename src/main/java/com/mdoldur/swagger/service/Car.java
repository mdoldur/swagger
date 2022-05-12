package com.mdoldur.swagger.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mdoldur.swagger.dto.CarDto;

public interface Car {
	
	ResponseEntity<CarDto> create(CarDto newCar);
	
	ResponseEntity<String> update(CarDto updateCar);

	ResponseEntity<String> delete(Integer id);

	ResponseEntity<CarDto> getCar(Integer id);
	
	ResponseEntity<List<CarDto>> getAll(Integer pageNumber, Integer pageSize);
	
}
