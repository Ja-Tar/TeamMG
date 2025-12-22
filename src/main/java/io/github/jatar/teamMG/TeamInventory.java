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
        public static String INV_NAME;
    }

    public static class NoTeamInv extends TeamInv {
        private final Inventory inventory;
        public static final String INV_NAME = "Drużyna:";

        public NoTeamInv() {
            // Create an Inventory with 9 slots, `this` here is our InventoryHolder.
            this.inventory = createInventory(this, 27, Component.text(INV_NAME));
        }

        @Override
        public @NonNull Inventory getInventory() {
            return this.inventory;
        }
    }
}
