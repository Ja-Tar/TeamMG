package io.github.jatar.teamMG;

import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import static org.bukkit.Bukkit.getServer;

public class ScoreboardMG {
    static final ScoreboardManager scoreboardManager = getServer().getScoreboardManager();
    public static final ScoreboardWrapper scoreboard = new ScoreboardWrapper(scoreboardManager.getMainScoreboard());
}
