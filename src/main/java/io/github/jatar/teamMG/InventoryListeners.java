package io.github.jatar.teamMG;

import com.destroystokyo.paper.utils.PaperPluginLogger;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;
import org.jspecify.annotations.NonNull;

import java.util.Set;

import static org.bukkit.Bukkit.getLogger;

public class InventoryListeners {
    public Set<String> inventoryNameList;

    public static class StopItemsOut implements Listener {
        @EventHandler
        public void onInventoryEvent(@NonNull InventoryClickEvent event) {
            //if (event.getClickedInventory()) {

            //}
        }
    }

    public static void setupInventoryNameList() {
        for (int i = 0; i < TeamInventory.class.getClasses().length; i++) {
            final Class<?> invClass = TeamInventory.class.getClasses()[i];
            if (TeamInventory.TeamInv.class.isAssignableFrom(invClass)) {
                getLogger().info();
            }
        }
    }
}
