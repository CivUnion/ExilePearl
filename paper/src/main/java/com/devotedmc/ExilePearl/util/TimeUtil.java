package com.devotedmc.ExilePearl.util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This entire class should really be transferred to CivModCore
 * I can't be bothered messing with dependencies rn so fuck that for now
 */
public class TimeUtil {

	private record TimeFactor(String prettyName, long ticks) {
	}

	private static final Map<String, TimeFactor> factors = new HashMap<>() {{
		put("y", new TimeFactor("year", 20L * 60 * 60 * 24 * 365)); // years
		put("M", new TimeFactor("month", 20L * 60 * 60 * 24 * 30)); // months (approximate)
		put("w", new TimeFactor("week",20L * 60 * 60 * 24 * 7)); // weeks
		put("d", new TimeFactor("day",20L * 60 * 60 * 24)); // days
		put("h", new TimeFactor("hour",20L * 60 * 60)); // hours
		put("m", new TimeFactor("minute",20L * 60)); // minutes
		put("s", new TimeFactor("second",20L)); // seconds
	}};

	private static final List<TimeFactor> orderedTimeFactors = factors.values().stream()
			.sorted(Comparator.comparingLong(TimeFactor::ticks).reversed())
			.toList();

	public static long parseTicks(String val) {
		Pattern pattern = Pattern.compile("(\\d+)([yMwdhms])");
		Matcher matcher = pattern.matcher(val);
		long totalTicks = 0;
		while (matcher.find()) {
			String value = matcher.group(1);
			String unit = matcher.group(2);
			totalTicks += Long.parseLong(value) * factors.get(unit).ticks;
		}

		return totalTicks;
	}

	public static String formatTicks(long ticks){
		StringBuilder builder = new StringBuilder();
		boolean isFirst = true;

		long ticksLeft = ticks;

		for(TimeFactor factor : orderedTimeFactors){
			long count = ticksLeft / factor.ticks;

			if(count <= 0L){
				continue;
			}

			ticksLeft -= factor.ticks * count;

			if(!isFirst){
				builder.append(", ");
			}else{
				isFirst = false;
			}

			builder.append(count).append(" ").append(" ").append(factor.prettyName);
			if(count > 1){
				builder.append("s");
			}
		}

		return builder.toString();
	}

	public static long ticksToMillis(long ticks){
		return (ticks / 20L) * 1000L;
	}
}
