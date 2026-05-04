package io.github.jatar.teamMG;

import net.kyori.adventure.text.Component;
import org.jspecify.annotations.NonNull;

public class RichMessagePrefixes {
    public static @NonNull Component error(Component s) {
        return Component.translatable("richPrefixes.error").append(s);
    }

    public static @NonNull Component warn(Component s) {
        return Component.translatable("richPrefixes.warn").append(s);
    }

    public static @NonNull Component info(Component s) {
        return Component.translatable("richPrefixes.info").append(s);
    }

    public static @NonNull Component done(Component s) {
        return Component.translatable("richPrefixes.done").append(s);
    }
}
