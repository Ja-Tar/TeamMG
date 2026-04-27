package io.github.jatar.teamMG;

import net.kyori.adventure.text.TextComponent;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TeamWrapper {
    private final Team team;
    private final NamespacedKey key;

    public TeamWrapper(@NotNull Team team) {
        this.team = team;
        key = new NamespacedKey(TeamMG.getProvidingPlugin(TeamMG.class), "TeamManager");
    }

    void setTeamManager(@NotNull Entity entity) {
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        pdc.set(key, PersistentDataType.STRING, team.getName());
    }

    boolean isTeamManager(@NotNull Entity entity) {
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        String teamName = pdc.get(key, PersistentDataType.STRING);
        return Objects.equals(teamName, team.getName());
    }

    public void displayName(@NotNull TextComponent text) {
        team.displayName(text);
    }

    public void addEntity(Player player) {
        team.addEntity(player);
    }
}
