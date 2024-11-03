package io.github.spicylemon2623.simply_zooming.mixins;

import finalforeach.cosmicreach.gamestates.GameState;
import finalforeach.cosmicreach.gamestates.KeybindsMenu;
import finalforeach.cosmicreach.settings.Keybind;
import io.github.spicylemon2623.simply_zooming.SZoomControls;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeybindsMenu.class)
public abstract class KeybindingMenuMixin {
    @Shadow protected abstract void addKeybindButton(String label, Keybind keybind);

    @Inject(method = "<init>(Lfinalforeach/cosmicreach/gamestates/GameState;)V", at = @At("TAIL"))
    private void addZoomButton(GameState previousState, CallbackInfo ci) {
        this.addKeybindButton("Zoom", SZoomControls.zoomKeybind);
    }
}
