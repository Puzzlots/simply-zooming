package io.github.spicylemon2623.SimplyZooming.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.settings.GraphicsSettings;
import finalforeach.cosmicreach.settings.types.FloatSetting;
import io.github.spicylemon2623.SimplyZooming.SZConfig;
import io.github.spicylemon2623.SimplyZooming.SimplyZooming;
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
        if (SimplyZooming.allowZoom()) {
            this.targetFovOffset = -(tempZoomFov);
        } else if (SimplyZooming.disallowZoom()) {
            tempZoomFov = GraphicsSettings.fieldOfView.getValue()/2 * SZConfig.INSTANCE.defMultiplier.getRealValue();
        }
    }
}
