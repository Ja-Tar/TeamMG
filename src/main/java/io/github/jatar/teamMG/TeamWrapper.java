package io.github.jatar.teamMG;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static io.github.jatar.teamMG.TeamMG.logger;
import static io.github.jatar.teamMG.TeamMG.mm;

public class TeamWrapper {
    private final Team team;
    private final NamespacedKey key;

    public TeamWrapper(@NotNull Team team) {
        this.team = team;
        key = new NamespacedKey(TeamMG.getProvidingPlugin(TeamMG.class), "TeamManager");
        team.prefix(mm.deserialize(" <i>%s<reset> ".formatted(team.getName())));
        logger.info("Utworzono drużynę -> %s".formatted(team.getName()));
    }

    void setTeamManager(@NotNull Player player) {
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        pdc.set(key, PersistentDataType.STRING, team.getName());
    }

    boolean isTeamManager(@NotNull Player player) {
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        String teamName = pdc.get(key, PersistentDataType.STRING);
        return Objects.equals(teamName, team.getName());
    }

    public void displayName(@NotNull TextComponent text) {
        team.displayName(text);
    }

    public void addEntity(Player player) { team.addEntity(player); }

    public void removeEntity(Player player) { team.removeEntity(player); }

    public void removeTeam(Player manager) {
        logger.info("Usunięto drużynę -> %s przez %s".formatted(team.getName(), manager.getName()));
        PersistentDataContainer pdc = manager.getPersistentDataContainer();
        pdc.remove(key);
        team.unregister();
    }
}
