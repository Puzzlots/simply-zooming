package io.github.spicylemon2623.SimplyZooming.mixins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.Viewport;
import finalforeach.cosmicreach.entities.player.Gamemode;
import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.settings.GraphicsSettings;
import finalforeach.cosmicreach.ui.*;
import io.github.spicylemon2623.SimplyZooming.SZoomControls;
import io.github.spicylemon2623.SimplyZooming.SimplyZooming;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(UI.class)
public class UIMixin {
    @Shadow
    private Viewport uiViewport;

    @Inject(method = "scrolled", at = @At("HEAD"), cancellable = true)
    public void scrolled(float amountX, float amountY, CallbackInfoReturnable<Boolean> cir){
        if (SimplyZooming.changeZoomLevel()); {
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

    @Inject(method = "render", at = @At("HEAD"))
    private void drawZoomLevel(CallbackInfo ci) {
        if (!SZoomControls.zoomKeybind.isPressed()) {}
        else if (SimplyZooming.drawZoomText()) {
                float zoomLevel = SimplyZooming.tempZoomFov;
                String zoomText = String.format("Fov: %.1f", zoomLevel);

                int padding = -45;
                if (InGame.getLocalPlayer().gamemode == Gamemode.get("s")) {padding -= 10;}

                UI.batch.setProjectionMatrix(this.uiViewport.getCamera().combined);
                UI.batch.begin();

                Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
                Gdx.gl.glBindTexture(GL20.GL_TEXTURE_2D, 0);
                FontRenderer.drawText(UI.batch, this.uiViewport, zoomText, 0, padding, HorizontalAnchor.CENTERED, VerticalAnchor.BOTTOM_ALIGNED);

                UI.batch.end();
            }
    }
}
