package io.github.spicylemon2623.SimplyZooming;

import com.github.puzzle.core.loader.launch.provider.mod.entrypoint.impls.ClientModInitializer;
import finalforeach.cosmicreach.gamestates.*;
import finalforeach.cosmicreach.ui.UI;

import static finalforeach.cosmicreach.gamestates.GameState.currentGameState;

public class SimplyZoomingClient implements ClientModInitializer {

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
    public void onInit() {
        Constants.LOGGER.info("Simply Zooming Initialized!");
    }

}
