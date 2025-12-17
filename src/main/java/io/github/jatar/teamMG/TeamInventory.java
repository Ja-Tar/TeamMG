package io.github.jatar.teamMG;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jspecify.annotations.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.bukkit.Bukkit.createInventory;

public class TeamInventory {
    public static abstract class TeamInv implements InventoryHolder {
        public String invName;
    }

    public static class NoTeamInv extends TeamInv {
        private final Inventory inventory;
        public final String invName = "Drużyna:";

        public NoTeamInv() {
            // Create an Inventory with 9 slots, `this` here is our InventoryHolder.
            this.inventory = createInventory(this, 27, Component.text(this.invName));
        }

        @Override
        public @NonNull Inventory getInventory() {
            return this.inventory;
        }
    }
}
