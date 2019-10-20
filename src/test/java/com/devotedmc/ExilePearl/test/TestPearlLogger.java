package com.devotedmc.ExilePearl.test;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.devotedmc.ExilePearl.PearlLogger;

public class TestPearlLogger implements PearlLogger {

	private final Logger logger;

	public TestPearlLogger(final Logger logger) {
		this.logger = logger;
	}

	public Logger getLogger() {
		return logger;
	}

	@Override
	public void log(Level level, String msg, Object... args) {
		logger.log(level, String.format(msg, args));
	}

	@Override
	public void log(String msg, Object... args) {
		logger.log(Level.INFO, String.format(msg, args));
	}

	@Override
	public Logger getPluginLogger() {
		return logger;
	}

}
