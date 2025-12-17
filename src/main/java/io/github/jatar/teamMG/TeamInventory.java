package io.github.jatar.teamMG;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jspecify.annotations.NonNull;

import static org.bukkit.Bukkit.createInventory;

public class TeamInventory {
    public static class NoTeamInv implements InventoryHolder {

        private final Inventory inventory;

        public NoTeamInv() {
            // Create an Inventory with 9 slots, `this` here is our InventoryHolder.

            this.inventory = createInventory(this, 27, "");
        }

        @Override
        public @NonNull Inventory getInventory() {
            return this.inventory;
        }

    }
}
