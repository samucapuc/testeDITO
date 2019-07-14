package br.com.samuel.dito.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomDatum implements Serializable
{
	private final static long serialVersionUID = 6778064447453062628L;
    public String key;
    public String value;

}