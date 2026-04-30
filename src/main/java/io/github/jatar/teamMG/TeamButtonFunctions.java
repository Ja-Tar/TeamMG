package io.github.jatar.teamMG;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import redempt.redlib.inventorygui.InventoryGUI;

import java.util.Objects;

public class TeamButtonFunctions {
    private static void openGUI(CommandSender sender, @NotNull InventoryGUI gui) {
        Player player = (Player) sender;
        gui.open(player);
    }

    public static void createNewTeam(@NonNull InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Objects.requireNonNull(event.getClickedInventory()).close();
        player.showDialog(TeamDialogs.createTeamDialog(""));
    }

    public static void searchForTeam(@NonNull InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Objects.requireNonNull(event.getClickedInventory()).close();
        openGUI(player, TeamInventory.TeamsList());
    }

    public static void removeTeam(@NonNull InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Objects.requireNonNull(event.getClickedInventory()).close();
        player.showDialog(TeamDialogs.removeTeamCheck());
    }

    public static void joinRequests(@NonNull InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Objects.requireNonNull(event.getClickedInventory()).close();
        player.sendMessage(Component.text("JOIN REQUESTS", NamedTextColor.YELLOW));
        //player.showDialog(TeamDialogs.listJoinRequests());
        // TODO Finish
    }
}
