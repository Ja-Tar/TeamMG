package io.github.jatar.teamMG;

import java.util.regex.Pattern;

public class PatternCheckers {
    private static final Pattern COMMAND_CHARS = Pattern.compile("^[A-Za-z0-9+._-]+$");

    public static boolean isValidCommandName(String name) {
        if (name == null) return false;
        return COMMAND_CHARS.matcher(name).matches();
    }
}
