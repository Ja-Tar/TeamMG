package io.github.jatar.teamMG;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

public class TeamInventory {
    public static InventoryGUI NoTeamInv() {
        InventoryGUI gui = new InventoryGUI(Bukkit.createInventory(null, 27, Component.text("Drużyna:")));
        ItemButton button = ItemButton.create(new ItemBuilder(Material.EMERALD_BLOCK)
                .setName("Click me!"), TeamButtonFunctions::createNewTeam);
        gui.addButton(button, 13);
        return gui;
    }
}
