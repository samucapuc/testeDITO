package br.com.samuel.dito.utils;

import java.time.LocalDateTime;
import java.util.Date;

public class DateUtils {
	@SuppressWarnings("unchecked")
	public static <T> T getDateNow(Class<T> cl) {
		return (T) (cl.getTypeName().equals(Date.class.getTypeName()) ? new Date() : LocalDateTime.now());
	}
}
