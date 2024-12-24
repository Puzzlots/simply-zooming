package io.github.spicylemon2623.SimplyZooming;

import org.quiltmc.config.api.ReflectiveConfig;
import org.quiltmc.config.api.annotations.Comment;
import org.quiltmc.config.api.values.TrackedValue;
import org.quiltmc.loader.api.config.v2.QuiltConfig;

public class SZConfig extends ReflectiveConfig {
    public static final SZConfig INSTANCE = QuiltConfig.create("", "simply_zooming", SZConfig.class);

    @Comment("The new fov set when you zoom. Lower numbers mean more zoom")
    public final TrackedValue<Float> zoomFov = this.value(60f);
}
