package io.github.spicylemon2623.simply_zooming.mixins;

import finalforeach.cosmicreach.gamestates.ChatMenu;
import finalforeach.cosmicreach.settings.GraphicsSettings;
import finalforeach.cosmicreach.ui.UI;
import io.github.spicylemon2623.simply_zooming.SZoomControls;
import io.github.spicylemon2623.simply_zooming.SimplyZooming;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static finalforeach.cosmicreach.gamestates.GameState.currentGameState;

@Mixin(UI.class)
public class UIMixin {
    @Inject(method = "scrolled", at = @At("HEAD"), cancellable = true)
    public void scrolled(float amountX, float amountY, CallbackInfoReturnable<Boolean> cir){
        if (SZoomControls.zoomKeybind.isPressed() && !(currentGameState instanceof ChatMenu)){
            if (amountY > 0) {
                float oldZoomFov = SimplyZooming.tempZoomFov;
                float newZoomFov = oldZoomFov + 1f;
                newZoomFov = Math.max(1, Math.min(GraphicsSettings.fieldOfView.getValue(), newZoomFov));
                SimplyZooming.tempZoomFov = newZoomFov;
                cir.cancel();
            } else if (amountY < 0) {
                float oldZoomFov = SimplyZooming.tempZoomFov;
                float newZoomFov = oldZoomFov - 1f;
                newZoomFov = Math.max(1, Math.min(GraphicsSettings.fieldOfView.getValue(), newZoomFov));
                SimplyZooming.tempZoomFov = newZoomFov;
                cir.cancel();
            }
        }
    }
}
