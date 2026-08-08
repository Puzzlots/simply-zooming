package io.github.spicylemon2623.SimplyZooming.mixins;

import com.badlogic.gdx.utils.Array;
import com.llamalad7.mixinextras.sugar.Local;
import finalforeach.cosmicreach.gamestates.KeybindsMenu;
import finalforeach.cosmicreach.settings.ControlSettings;
import finalforeach.cosmicreach.util.lang.Lang;
import io.github.spicylemon2623.SimplyZooming.SZoomControls;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeybindsMenu.class)
public abstract class KeybindingMenuMixin {
    @Shadow
    public void addKeybind(KeybindsMenu.KeybindEntry keybindEntry) {}

    @Inject(method = "<init>", at = @At(value = "TAIL"), remap = false)
    private void addZoomButton(CallbackInfo ci) {
        this.addKeybind(new KeybindsMenu.KeybindEntry("Zoom", SZoomControls.zoomKeybind));
    }
}
