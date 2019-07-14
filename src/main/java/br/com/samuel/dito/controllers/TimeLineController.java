package br.com.samuel.dito.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.samuel.dito.dto.OutTimeLine;
import br.com.samuel.dito.service.TimeLineService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/timelines")
public class TimeLineController {
	@Autowired
	private TimeLineService timeLineService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OutTimeLine> findTimeLines() throws Exception {
		return ResponseEntity.ok(timeLineService.findTimeLines());
	}
}
