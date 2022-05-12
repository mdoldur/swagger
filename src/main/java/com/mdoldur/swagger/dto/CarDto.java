package com.mdoldur.swagger.dto;

import com.mdoldur.swagger.model.CarEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
	
	private Integer carId;
	private String name;
	private Double price;
	private Integer modelYear;
	
	public static CarDto create(CarEntity carEntity) {
		CarDto carDto = new CarDto();
		carDto.setCarId(carEntity.getId());
		carDto.setName(carEntity.getName());
		carDto.setModelYear(carEntity.getModel());
		carDto.setPrice(carEntity.getPrice());
		return carDto;
	}
	
}
