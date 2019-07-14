package br.com.samuel.dito.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.samuel.dito.domain.CustomDatum;
import br.com.samuel.dito.domain.Event;
import br.com.samuel.dito.domain.Events;
import br.com.samuel.dito.dto.OutTimeLine;
import br.com.samuel.dito.dto.Product;
import br.com.samuel.dito.dto.Timeline;
import br.com.samuel.dito.utils.Constants;

@Service
public class TimeLineService {

	@Autowired
	private EventService eventService;
	
	public OutTimeLine findTimeLines() throws Exception {
		Events es = eventService.findEventsFromFileJson();
		OutTimeLine out = initOutTimeLine();
		AtomicReference<Timeline> tl = new AtomicReference<Timeline>(initTimeLineAndProductsList());
		AtomicReference<String> idTransactionAfter = new AtomicReference<>(StringUtils.EMPTY);
		AtomicInteger count = new AtomicInteger(0);
		es.getEvents().stream().forEach(e -> group(e, count, idTransactionAfter, tl, out));
		if (count.get() > 0 && (CollectionUtils.isEmpty(out.getTimeline()) || count.get() > out.getTimeline().size())) {
			out.getTimeline().add(tl.get());
		}
		out.setTimeline(out.getTimeline().stream()
				.sorted(Comparator.comparing(Timeline::getTimestamp).reversed())
				.collect(Collectors.toList()));
		return out;
	}
	private void group(Event e, AtomicInteger count, AtomicReference<String> idTransactionAfter,AtomicReference<Timeline> tl,
			OutTimeLine out) {
		final Product product = e.getEvent().equals(Constants.EVENT_BUY_PRODUCT) ? new Product() : null;
		e.getCustomData().stream().forEach(cd -> manipulateCustomDatum(cd, e, count, idTransactionAfter, product, tl, out));
		if (product != null) {
			tl.get().getProducts().add(product);
		}
	}
	
	private void manipulateCustomDatum(CustomDatum cd, Event e, AtomicInteger count, AtomicReference<String> idTransactionAfter, Product p, AtomicReference<Timeline> tl,
			OutTimeLine out) {
		if (cd.getKey().equals(Constants.INFO_EVENT.TRANSACTION_ID)) {
			if (StringUtils.isNotBlank(idTransactionAfter.get())
					&& !idTransactionAfter.get().equals(cd.getValue())) {
				out.getTimeline().add(tl.get());
				tl.set(initTimeLineAndProductsList());
				count.incrementAndGet();
			} else {
				tl.get().setTimestamp(e.getTimestamp());
				tl.get().setRevenue(e.getRevenue() != null ? e.getRevenue() : tl.get().getRevenue());
				count.addAndGet(StringUtils.isBlank(idTransactionAfter.get()) ? 1 : 0);
			}
			idTransactionAfter.set(cd.getValue());
			tl.get().setTransactionId(idTransactionAfter.get());
		} else if (cd.getKey().equals(Constants.INFO_EVENT.STORE_NAME)) {
			tl.get().setStoreName(cd.getValue());
		}
		createProductEventBuyProduct(p, e, cd);
	}
	private OutTimeLine initOutTimeLine() {
		OutTimeLine out = new OutTimeLine();
		out.setTimeline(new ArrayList<Timeline>());
		return out;
	}

	private Timeline initTimeLineAndProductsList() {
		Timeline tl = new Timeline();
		tl.setProducts(new ArrayList<>());
		return tl;
	}

	private void createProductEventBuyProduct(Product p, Event e, CustomDatum cd) {
		if (e.getEvent().equals(Constants.EVENT_BUY_PRODUCT)) {
			if (Constants.INFO_EVENT.PRODUCT_NAME.equals(cd.getKey())) {
				p.setName(cd.getValue());
			} else if (Constants.INFO_EVENT.PRODUCT_PRICE.equals(cd.getKey())) {
				p.setPrice(Double.valueOf(cd.getValue()));
			}
		}
	}

}
