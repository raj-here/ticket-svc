package io.arha.ticketsvc.util;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class Util {

	public String getUniqueCode() {
		UUID uuid = UUID.randomUUID();// Unique
		return uuid.toString() + new Date().getTime();// Unique
	}

	public Date addDays(int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}
}
