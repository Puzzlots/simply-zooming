package io.github.spicylemon2623.SimplyZooming.mixins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.Viewport;
import finalforeach.cosmicreach.entities.player.Gamemode;
import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.settings.GraphicsSettings;
import finalforeach.cosmicreach.ui.FontRenderer;
import finalforeach.cosmicreach.ui.HorizontalAnchor;
import finalforeach.cosmicreach.ui.UI;
import finalforeach.cosmicreach.ui.VerticalAnchor;
import io.github.spicylemon2623.SimplyZooming.SZoomControls;
import io.github.spicylemon2623.SimplyZooming.SimplyZooming;
import io.github.spicylemon2623.SimplyZooming.SimplyZoomingClient;
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
        if (SimplyZoomingClient.changeZoomLevel()){
            if (amountY != 0 && SimplyZoomingClient.changeZoomLevel()) {
                float baseFov = SimplyZooming.tempZoomFov;
                float delta = amountY * 100f;
                float slowZoomFov = GraphicsSettings.fieldOfView.getValue() - 30;

                if (baseFov >= slowZoomFov) {
                    float over = baseFov - slowZoomFov;
                    float damping = (float) Math.exp(-over / 10f); // Shrinks fast as `over` increases
                    delta *= damping;
                }

                float newFov = baseFov + delta;
                newFov = Math.min(GraphicsSettings.fieldOfView.getValue(), Math.max(0, newFov)); // Clamp between 0 and maxZoom
                SimplyZooming.tempZoomFov = newFov;
                cir.cancel();
            }
        }
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void drawZoomLevel(CallbackInfo ci) {
        if (!SZoomControls.zoomKeybind.isPressed()) {}
        else if (SimplyZoomingClient.drawZoomText()) {
            float zoomLevel = SimplyZooming.tempZoomFov;
            String zoomText = String.format("Zoom: %.1f", zoomLevel);

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
