package br.com.samuel.dito.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import br.com.samuel.dito.domain.Event;
import br.com.samuel.dito.service.EventService;
import br.com.samuel.dito.utils.DateUtils;
@Configuration
@Profile(value = { "dev","test" })
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private EventService eventService;

    @Autowired
    private ElasticsearchTemplate esTemplate;
	
	@Override
	public void run(String... args) throws Exception {
        deleteAndCreateIndex();
        fillListEventsAndSave();
	}

	private void deleteAndCreateIndex() {
		esTemplate.deleteIndex(Event.class);
        esTemplate.createIndex(Event.class);
        esTemplate.putMapping(Event.class);
        esTemplate.refresh(Event.class);
	}
	
	private void fillListEventsAndSave() {
		List<Event> eventList = new ArrayList<>();
		//LocalDateTime now = DateUtils.getDateNow(LocalDateTime.class);
		Date now = DateUtils.getDateNow(Date.class);
    	eventList.add(new Event(1L,"comprou",now,null,140D));
    	eventList.add(new Event(null,"tentou comprar", now,null,195D));
    	eventList.add(new Event(null,"deixou pra comprar mais tarde", now,null,180D));
    	eventList.add(new Event(null,"so visualizou", now,null,167D));
    	
    	for (Event e : eventList) {
    		eventService.save(e);
    	}
	}

}
