package com.mdoldur.swagger.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CAR")
public class CarEntity {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;
	
	@Column(name="NAME")
	String name;
	
	@Column(name="PRICE")
	Double price;
	
	@Column(name="MODEL_YEAR")
	Integer model;
	
}
