package io.github.spicylemon2623.SimplyZooming.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import finalforeach.cosmicreach.gamestates.ChatMenu;
import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.settings.types.FloatSetting;
import io.github.spicylemon2623.SimplyZooming.SZConfig;
import io.github.spicylemon2623.SimplyZooming.SZoomControls;
import io.github.spicylemon2623.SimplyZooming.SimplyZooming;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static finalforeach.cosmicreach.gamestates.GameState.currentGameState;

@Mixin(InGame.class)
public class FovModifierMixin {
    @WrapOperation(method = "update(F)V", at = @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/settings/types/FloatSetting;getValue()F"))
    private float getFov(FloatSetting instance, Operation<Float> original) {
        if (SZoomControls.zoomKeybind.isPressed() && !(currentGameState instanceof ChatMenu)){
            return SimplyZooming.tempZoomFov;
        } else if (!SZoomControls.zoomKeybind.isPressed() && !(currentGameState instanceof ChatMenu)) {
            SimplyZooming.tempZoomFov = SZConfig.INSTANCE.zoomFov.getRealValue();
        }
        return original.call(instance);
    }
}
