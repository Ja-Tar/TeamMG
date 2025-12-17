package io.github.jatar.teamMG;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jspecify.annotations.NonNull;

import static org.bukkit.Bukkit.createInventory;

public class TeamInventory {
    public static class NoTeamInv implements InventoryHolder {

        private final Inventory inventory;

        public NoTeamInv() {
            // Create an Inventory with 9 slots, `this` here is our InventoryHolder.
            this.inventory = createInventory(this, 27, Component.text("Drużyna:"));
        }

        @Override
        public @NonNull Inventory getInventory() {
            return this.inventory;
        }
    }
}
