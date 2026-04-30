package io.github.jatar.teamMG;

import org.bukkit.entity.Entity;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

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

    @Nullable
    public Set<TeamWrapper> getTeams() {
        Set<Team> teams = scoreboard.getTeams();
        if (teams.isEmpty()) return null;
        Set<TeamWrapper> teamWrappers = new HashSet<>();
        for (Team team : teams) {
            TeamWrapper teamWrapper = new TeamWrapper(team);
            teamWrappers.add(teamWrapper);
        }
        return teamWrappers;
    }
}
