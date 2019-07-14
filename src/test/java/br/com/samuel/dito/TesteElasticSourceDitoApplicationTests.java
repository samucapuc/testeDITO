package br.com.samuel.dito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.samuel.dito.domain.Event;
import br.com.samuel.dito.service.EventService;
import br.com.samuel.dito.utils.DateUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TesteDitoApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteElasticSourceDitoApplicationTests {

	@Autowired
	private EventService eventService;

	@Autowired
	private ElasticsearchTemplate esTemplate;

	Date now;

	// LocalDateTime now;

	@Test
	public void contextLoads() {
	}

	@Before
	public void before() {
		esTemplate.deleteIndex(Event.class);
		esTemplate.createIndex(Event.class);
		esTemplate.putMapping(Event.class);
		esTemplate.refresh(Event.class);
		//now = DateUtils.getDateNow(LocalDateTime.class);
		now = DateUtils.getDateNow(Date.class);
	}
	

	@Test
	public void testSave() {
		Event event = new Event(5L, "buy", now, null, 340D);
		Event testEvent = eventService.save(event);
		assertNotNull(testEvent.getId());
		assertEquals(testEvent.getEvent(), event.getEvent());
		assertEquals(testEvent.getTimestamp(), event.getTimestamp());
	}


	@Test
	public void testFindByAutoComplete() {
		fillListEventsAndSave();
		List<Event> eventsList = eventService.findByEventContaining("com");
		assertThat(eventsList.size(), is(3));
	}

	private <T> void fillListEventsAndSave() {
		List<Event> eventList = new ArrayList<>();
		eventList.add(new Event(1L, "comprou", now, null, 140D));
		eventList.add(new Event(2L, "tentou comprar", now, null, 195D));
		eventList.add(new Event(3L, "deixou pra comprar mais tarde", now, null, 180D));
		eventList.add(new Event(4L, "so visualizou", now, null, 167D));
		for (Event e : eventList) {
			eventService.save(e);
		}
	}

	@After
	public void testDeleteIndex() {
		esTemplate.deleteIndex(Event.class);
	}
}
