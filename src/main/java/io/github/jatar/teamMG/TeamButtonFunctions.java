package io.github.jatar.teamMG;

import net.kyori.adventure.text.Component;
import org.bukkit.event.inventory.InventoryClickEvent;

public class TeamButtonFunctions {

    public static void createNewTeam(InventoryClickEvent event) {
        event.getWhoClicked().sendMessage(Component.text("Hello!"));
    }
}
