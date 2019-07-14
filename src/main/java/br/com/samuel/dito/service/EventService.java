package br.com.samuel.dito.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.samuel.dito.domain.Event;
import br.com.samuel.dito.domain.Events;
import br.com.samuel.dito.repositories.EventRespository;

@Service
public class EventService {
	
	@Value("${events.json}")
	private String eventsJsonFile;

	public Events findEventsFromFileJson() throws Exception{
		ObjectMapper jsonMapper = new ObjectMapper();
		byte[] jsonData = Files.readAllBytes(Paths.get(this.getClass().getClassLoader().getResource(eventsJsonFile).toURI()));
		return jsonMapper.readValue(jsonData, Events.class);
	}
	
	@Autowired
	private EventRespository eventRespository;
	
	public Page<Event> findByEventContaining(String name, Pageable pageable){
		return eventRespository.findByEventContaining(name, pageable);
	}
	
	public List<Event> findByEventContaining(String name){
		return eventRespository.findByEventContaining(name);
	}

	public Event save(Event event) {
		return eventRespository.save(event);
	}
}
