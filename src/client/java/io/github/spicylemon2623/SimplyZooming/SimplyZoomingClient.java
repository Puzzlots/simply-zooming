package io.github.spicylemon2623.SimplyZooming;

import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.client.ClientModInit;
import finalforeach.cosmicreach.gamestates.*;
import finalforeach.cosmicreach.ui.UI;

import static finalforeach.cosmicreach.gamestates.GameState.currentGameState;

public class SimplyZoomingClient  {
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

}
