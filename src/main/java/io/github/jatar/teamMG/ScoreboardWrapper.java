package io.github.jatar.teamMG;

import net.kyori.adventure.audience.ForwardingAudience;
import org.bukkit.entity.Entity;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ScoreboardWrapper {
    private final Scoreboard scoreboard;

    public ScoreboardWrapper(@NotNull Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    @NotNull
    public TeamWrapper registerNewTeam(String name) {
        return new TeamWrapper(scoreboard.registerNewTeam(name));
    }

    @Nullable
    public TeamWrapper getEntityTeam(Entity entity) {
        Team entityTeam = scoreboard.getEntityTeam(entity);
        if (entityTeam == null) return null;
        return new TeamWrapper(entityTeam);
    }
}
