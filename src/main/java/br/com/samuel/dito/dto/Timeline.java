package br.com.samuel.dito.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "timestamp", "revenue", "transaction_id", "store_name", "products" })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Timeline {

	public Date timestamp;
	
	public Double revenue;
	
	@JsonProperty("transaction_id")
	public String transactionId;
	
	@JsonProperty("store_name")
	public String storeName;
	
	public List<Product> products = null;

}