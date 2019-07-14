package br.com.samuel.dito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.samuel.dito.dto.OutTimeLine;
import br.com.samuel.dito.service.TimeLineService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TesteDitoApplication.class)
public class TesteDitoApplicationTests {
	@Autowired
	private TimeLineService timeLineService;
	

	@Test
	public void contextLoads() {
	}

	@Test
	public void testTimeLine() throws Exception{
		OutTimeLine o = timeLineService.findTimeLines();
		assertNotNull(o);
		assertNotNull(o.getTimeline());
		assertThat(o.getTimeline().size(), is(2));
		assertEquals(o.getTimeline().stream().findFirst().get().getTransactionId(), "3409340");
	}
}
