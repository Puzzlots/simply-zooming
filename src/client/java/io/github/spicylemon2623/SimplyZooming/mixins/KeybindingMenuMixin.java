package io.github.spicylemon2623.SimplyZooming.mixins;

import com.badlogic.gdx.utils.Array;
import com.llamalad7.mixinextras.sugar.Local;
import finalforeach.cosmicreach.gamestates.KeybindsMenu;
import finalforeach.cosmicreach.lang.Lang;
import io.github.spicylemon2623.SimplyZooming.SZoomControls;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(KeybindsMenu.class)
public abstract class KeybindingMenuMixin {
    @Inject(method = "create", at = @At(value = "INVOKE", target = "Lcom/badlogic/gdx/utils/Array;iterator()Lcom/badlogic/gdx/utils/Array$ArrayIterator;"), locals = LocalCapture.CAPTURE_FAILHARD, remap = false)
    private void addZoomButton(CallbackInfo ci, @Local(name = "keybindEntries") Array<KeybindsMenu.KeybindEntry> keybindEntries) {
        keybindEntries.add(new KeybindsMenu.KeybindEntry(Lang.get("Zoom"), SZoomControls.zoomKeybind));
    }
}
