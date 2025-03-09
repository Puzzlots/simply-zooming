package io.github.spicylemon2623.SimplyZooming.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.settings.types.FloatSetting;
import io.github.spicylemon2623.SimplyZooming.SimplyZooming;
import io.github.spicylemon2623.SimplyZooming.SimplyZoomingClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(InGame.class)
public class FovModifierMixin {
    @WrapOperation(method = "update(F)V", at = @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/settings/types/FloatSetting;getValue()F"))
    private float getFov(FloatSetting instance, Operation<Float> original) {
        if (SimplyZoomingClient.allowZoom()){
            return SimplyZooming.tempZoomFov;
        } else if (SimplyZoomingClient.disallowZoom()) {
            SimplyZooming.tempZoomFov = 40f;
        }
        return original.call(instance);
    }
}
