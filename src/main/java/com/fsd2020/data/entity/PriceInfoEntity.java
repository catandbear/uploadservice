package com.fsd2020.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PriceInfoEntity {

	private String code;
	private String stockExchange;
	private Double price;
	private String date;
	
}
