package com.devotedmc.ExilePearl.core;

import com.devotedmc.ExilePearl.ExilePearlApi;
import com.devotedmc.ExilePearl.config.PearlConfig;

/**
 * Interval task that deducts strength from existing prison pearls
 * @author Gordon
 */
final class PearlDecayTask extends ExilePearlTask {

	private long interval = 20L * 60L;

	/**
	 * Creates a new FactoryWorker instance
	 */
	public PearlDecayTask(final ExilePearlApi pearlApi) {
		super(pearlApi);
	}

	@Override
	public void start() {

		super.start();
		if (enabled) {
			pearlApi.log("Pearl decay will run every %d minutes.", interval);
		}
	}


	@Override
	public String getTaskName() {
		return "Pearl Decay";
	}


	@Override
	public long getTickInterval() {
		return interval;
	}


	@Override
	public void run() {
		if (!enabled) {
			return;
		}

		long start = System.currentTimeMillis();
		pearlApi.getPearlManager().decayPearls();
		long duration = System.currentTimeMillis() - start;
		pearlApi.log("Pearl decay task took %dms", duration);
	}

	@Override
	public void loadConfig(PearlConfig config) {
		long newInterval = pearlApi.getPearlConfig().getPearlHealthDecayIntervalInTicks();

		if (newInterval != interval) {
			this.interval = newInterval;

			// Reschedule the task if the interval changed
			if (enabled) {
				pearlApi.log("Rescheduling the pearl decay task because the interval changed.");
				restart();
			}
		}
	}
}
