package io.github.jatar.teamMG;

import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;

public class RichMessagePrefixes {
    public static @NonNull String error(String s) {
        String errorText = "<red><bold>ERROR: <reset>";
        return errorText + s;
    }

    public static @NonNull String warn(String s) {
        String warnText = "<yellow><bold>UWAGA: <reset>";
        return warnText + s;
    }

    public static @NonNull String info(String s) {
        String infoText = "<blue><bold>INFO: <reset>";
        return infoText + s;
    }

    public static @NonNull String done(String s) {
        String doneText = "<green><bold>SUKCES: <reset>";
        return doneText + s;
    }
}
