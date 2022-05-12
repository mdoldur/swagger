package com.mdoldur.swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdoldur.swagger.model.CarEntity;

public interface CarRepository extends JpaRepository<CarEntity, Integer> {

	/*@Query("SELECT c FROM CAR c WHERE c.NAME LIKE %:name%")
	List<CarEntity> findCarByName(@Param("name") String name);*/
	
}
