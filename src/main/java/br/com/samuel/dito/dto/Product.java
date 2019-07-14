package br.com.samuel.dito.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "price" })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	public String name;
	public Double price;

}