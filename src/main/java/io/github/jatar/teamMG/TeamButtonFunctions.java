package io.github.jatar.teamMG;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

public class TeamButtonFunctions {

    public static void createNewTeam(@NonNull InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Objects.requireNonNull(event.getClickedInventory()).close();
        player.showDialog(TeamDialogs.createTeamDialog());
    }

    public static void searchForTeam(@NonNull InventoryClickEvent event) {
        event.getWhoClicked().sendMessage(Component.text("TAK"));
        Objects.requireNonNull(event.getClickedInventory()).close();
    }
}
