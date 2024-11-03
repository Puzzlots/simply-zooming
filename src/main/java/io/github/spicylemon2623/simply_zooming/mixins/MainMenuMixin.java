package io.github.spicylemon2623.simply_zooming.mixins;

import io.github.spicylemon2623.simply_zooming.SimplyZooming;
import finalforeach.cosmicreach.gamestates.MainMenu;
import finalforeach.cosmicreach.lwjgl3.Lwjgl3Launcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MainMenu.class)
public class MainMenuMixin {
    @Inject(method = "create", at = @At("HEAD"))
    private void injected(CallbackInfo ci) {
        SimplyZooming.LOGGER.info("Example mixin logged!");

        // Usually, Lwjgl3Launcher.startTime will be a private variable
        //  but due to the "simply_zooming.accesswidener", it can now be accessed
        SimplyZooming.LOGGER.info("Access for game start time widened, and giving " + Lwjgl3Launcher.startTime);
    }
}
