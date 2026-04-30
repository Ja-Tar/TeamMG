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
        player.showDialog(TeamDialogs.createTeamDialog(""));
    }

    public static void searchForTeam(@NonNull InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Objects.requireNonNull(event.getClickedInventory()).close();
        player.sendMessage(Component.text("SEARCH"));
        // TODO: Add inventory gui with list of guilds as some items
    }

    public static void removeTeam(@NonNull InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Objects.requireNonNull(event.getClickedInventory()).close();
        player.showDialog(TeamDialogs.removeTeamCheck());
    }
}
