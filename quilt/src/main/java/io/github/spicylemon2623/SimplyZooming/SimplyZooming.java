package io.github.spicylemon2623.SimplyZooming;

import dev.crmodders.cosmicquilt.api.entrypoint.ModInitializer;
import finalforeach.cosmicreach.gamestates.*;
import finalforeach.cosmicreach.ui.UI;
import org.quiltmc.loader.api.ModContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static finalforeach.cosmicreach.gamestates.GameState.currentGameState;

public class SimplyZooming implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Simply Zooming");

	public static float tempZoomFov = SZConfig.INSTANCE.defMultiplier.getRealValue();

	public static boolean allowZoom() {
		return (SZoomControls.zoomKeybind.isPressed() && !(currentGameState instanceof ChatMenu) && !(UI.isInventoryOpen()));
	}

	public static boolean disallowZoom() {
		return (!SZoomControls.zoomKeybind.isPressed() && !(currentGameState instanceof ChatMenu) && !(UI.isInventoryOpen()));
	}

	public static boolean changeZoomLevel() {
		return (SZoomControls.zoomKeybind.isPressed() && !(currentGameState instanceof ChatMenu) && !(UI.isInventoryOpen()));
	}

	public static boolean drawZoomText() {
		return (!(currentGameState instanceof ChatMenu) && !(currentGameState instanceof YouDiedMenu) && !(currentGameState instanceof PauseMenu) && !(UI.isInventoryOpen()) && UI.renderUI);
}

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Simply Zooming Initialized!");
	}
}