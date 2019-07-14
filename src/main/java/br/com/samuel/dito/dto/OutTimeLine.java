package br.com.samuel.dito.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "timeline" })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutTimeLine {

	public List<Timeline> timeline;

}