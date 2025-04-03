package demo;

import java.text.DateFormat;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.Date;
import java.util.Locale;

public class Dates {

	public static void main(String[] args) {
		LocalDate date = LocalDate.of(2019, 1, 1);
		LocalTime time = LocalTime.of(0, 0);
		ZoneId india = ZoneId.of("Asia/Kolkata");
		ZonedDateTime zIndia = ZonedDateTime.of(date, time, india);
		ZoneId us = ZoneId.of("America/Los_Angeles");
		ZonedDateTime zUS =  zIndia.withZoneSameLocal(us);
		System.out.println(Duration.between(zIndia, zUS)); 
	}
	
	public static void testLocales() {
		DateFormat french = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRENCH);
		DateFormat us = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
		DateFormat def = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());

		Date date = new Date();

		System.out.println(french.format(date));
		System.out.println(us.format(date));
		System.out.println(def.format(date));
	}

	public static void demoUnits() {
		ChronoUnit s = ChronoUnit.SECONDS;
		ChronoUnit m = ChronoUnit.MINUTES;
		ChronoUnit h = ChronoUnit.HOURS;

		Duration oneSecond = s.getDuration();
		System.out.println(oneSecond);

		LocalTime midi = LocalTime.NOON;
		System.out.println(midi.plus(1, s));
		System.out.println(midi.plus(1, m));
		System.out.println(midi.plus(48, h)); //

	}

	public static void demoFormat() {
		LocalDate d = LocalDate.now();
		DateTimeFormatter f = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
		System.out.println(d.format(f));

		DateTimeFormatter f2 = DateTimeFormatter.ofPattern("YYYY YYYY YYYY");
		System.out.println(d.format(f2));

	}

	public static void demoParsing() {
		System.out.println(LocalDate.ofEpochDay(0));
		System.out.println(LocalDate.ofEpochDay(-20000000));

		LocalTime t = LocalTime.parse("12:00:00.000000004");
		System.out.println(t);

		LocalDate d = LocalDate.parse("2025-02-12");
		System.out.println(d);
	}

	public static void piegesAppels() {

		LocalDateTime todayAtNoon = LocalDateTime.of(LocalDate.now(), LocalTime.NOON);

		LocalDate today = LocalDate.now();
		LocalDate demain = today.plusDays(1);

		Period interval = Period.between(today, demain);
		System.out.println(interval.getYears()); // 0
		System.out.println(interval.getMonths()); // 0
		System.out.println(interval.getDays()); // 1

		Duration interval2 = Duration.between(today, demain);
		System.out.println(interval2.getSeconds() / 60 / 60); //

	}

	public static void conflits() {

		/*
		 * 
		 * Temporal LocalDate (Y/M/D) LocalTime (H:m:s)
		 * 
		 * TemporalAmount Period (Y/M/D) Duration (H:m:s)
		 * 
		 */

		TemporalAmount moins2mois = Period.of(0, -2, 0);
		LocalDate today = LocalDate.now();
		System.out.println(today.minus(moins2mois)); // 2025-06-03
		System.out.println(today.plus(moins2mois)); // 2025-02-03

		TemporalAmount deuxMinutes = Duration.ofMinutes(2);

		try {
			System.out.println(today.plus(deuxMinutes)); // ?
		} catch (DateTimeException e) {
			System.out.println(e.getMessage());
		}

		// LocalDate .plus(period) // OK
		// LocalDate .plus(duration) // KO

		try {
			LocalTime midi = LocalTime.NOON;
			System.out.println(midi.plus(moins2mois));
		} catch (DateTimeException e) {
			System.out.println(e.getMessage());
		}

		// LocalTime .plus(duration) // OK
		// LocalDate .plus(period) // KO

		LocalDateTime todayAtNoon = LocalDateTime.of(LocalDate.now(), LocalTime.NOON);
		System.out.println(todayAtNoon.plus(deuxMinutes));
		System.out.println(todayAtNoon.plus(moins2mois));

	}
}
