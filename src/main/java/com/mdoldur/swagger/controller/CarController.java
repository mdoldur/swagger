package com.mdoldur.swagger.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdoldur.swagger.dto.CarDto;
import com.mdoldur.swagger.service.Car;

import lombok.AllArgsConstructor;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/car")
@AllArgsConstructor
@Api(value="Car Controller API")
public class CarController {
	private Car car;
	
	@PostMapping
	public ResponseEntity<CarDto> create(@RequestBody CarDto newCar) {
		return car.create(newCar);
	}
	
	@PutMapping
	public ResponseEntity<String> update(@RequestBody CarDto updateCar) {
		return car.update(updateCar);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable(name = "id") Integer id) {
		return car.delete(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CarDto> getCar(@PathVariable(name = "id") Integer id) {
		return car.getCar(id);
	}
	
	@GetMapping
	public ResponseEntity<List<CarDto>> getAll(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
											   @RequestParam(value = "pageSize", defaultValue = "1", required = false) Integer pageSize) {
		return car.getAll(pageNumber, pageSize);
	}
	
}
