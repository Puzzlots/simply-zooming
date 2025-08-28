package io.github.spicylemon2623.SimplyZooming.mixins;

import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.settings.GraphicsSettings;
import io.github.spicylemon2623.SimplyZooming.SimplyZoomingClient;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.github.spicylemon2623.SimplyZooming.SimplyZooming.tempZoomFov;

@Mixin(InGame.class)
public class FovModifierMixin {
    @Shadow
    float targetFovOffset;

    @Inject(method = "render", at = @At(value = "FIELD", target = "Lfinalforeach/cosmicreach/gamestates/InGame;targetFovOffset:F", opcode = Opcodes.PUTFIELD, ordinal = 0, shift = At.Shift.AFTER))
    private void applyZoomFov(CallbackInfo ci) {
        if (SimplyZoomingClient.allowZoom()) {
            this.targetFovOffset = -(tempZoomFov);
        } else if (SimplyZoomingClient.disallowZoom()) {
            tempZoomFov = GraphicsSettings.fieldOfView.getValue()/2;
        }
    }
}
