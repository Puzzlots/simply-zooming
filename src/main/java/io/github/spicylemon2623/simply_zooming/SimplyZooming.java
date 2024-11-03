package io.github.spicylemon2623.simply_zooming;

import dev.crmodders.cosmicquilt.api.entrypoint.ModInitializer;
import org.quiltmc.loader.api.ModContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimplyZooming implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Simply Zooming");

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Simply Zooming Initialized!");
	}
}

