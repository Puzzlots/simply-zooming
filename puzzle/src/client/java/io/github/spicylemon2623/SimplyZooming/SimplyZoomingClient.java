package io.github.spicylemon2623.SimplyZooming;

import com.github.puzzle.core.loader.launch.provider.mod.entrypoint.impls.ClientModInitializer;

public class SimplyZoomingClient implements ClientModInitializer {

    @Override
    public void onInit() {
        Constants.LOGGER.info("Hello From INIT");
    }

}
