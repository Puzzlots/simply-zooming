package io.github.spicylemon2623.simply_zooming.mixins;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import finalforeach.cosmicreach.gamestates.ChatMenu;
import finalforeach.cosmicreach.settings.GraphicsSettings;
import finalforeach.cosmicreach.ui.HorizontalAnchor;
import finalforeach.cosmicreach.ui.UI;
import finalforeach.cosmicreach.ui.FontRenderer;
import finalforeach.cosmicreach.ui.UIElement;
import io.github.spicylemon2623.simply_zooming.SZoomControls;
import io.github.spicylemon2623.simply_zooming.SimplyZooming;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static finalforeach.cosmicreach.gamestates.GameState.batch;
import static finalforeach.cosmicreach.gamestates.GameState.currentGameState;

@Mixin(UI.class)
public class UIMixin {
    @Shadow
    private Viewport uiViewport;

    @Unique
    private final Vector2 tmpVec = new Vector2();

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

    @Inject(method = "render", at = @At("HEAD"))
    private void drawZoomLevel(CallbackInfo ci) {
        if (!SZoomControls.zoomKeybind.isPressed()) {
            return;
        }

        float zoomLevel = SimplyZooming.tempZoomFov;
        String zoomText = String.format("Fov: %.1f", zoomLevel);

        FontRenderer.getTextDimensions(this.uiViewport, zoomText, this.tmpVec);
        final var textW = this.tmpVec.x;
        final var textH = this.tmpVec.y;

        UIElement elem = new UIElement(-textW/2, 230, textW, textH) { };
        elem.hAnchor = HorizontalAnchor.CENTERED;


        UI.batch.setProjectionMatrix(this.uiViewport.getCamera().combined);
        elem.drawText(this.uiViewport, UI.batch);

        UI.batch.begin();

        FontRenderer.drawText(UI.batch, this.uiViewport, zoomText, -textW/2, 230, false);

        UI.batch.end();
    }
}
