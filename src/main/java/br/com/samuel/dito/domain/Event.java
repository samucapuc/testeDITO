package br.com.samuel.dito.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "dito", type = "events")//para criar no elasticsource
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
    private String event;
    
	private Date timestamp;
    
	@JsonProperty(value = "custom_data")
    private List<CustomDatum> customData;
    
    private Double revenue;
	
}
