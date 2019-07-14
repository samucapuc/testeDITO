package br.com.samuel.dito.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import br.com.samuel.dito.domain.Event;

@Repository
public interface EventRespository extends ElasticsearchRepository<Event, Long> {

	public Page<Event> findByEventContaining(String name, Pageable pageable);
	public List<Event> findByEventContaining(String name);
}
