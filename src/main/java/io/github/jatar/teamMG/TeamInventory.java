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

        ItemButton buttonCreateTeam = ItemButton.create(new ItemBuilder(Material.LIME_WOOL)
                .setName("STWÓRZ DRUŻYNĘ"), TeamButtonFunctions::createNewTeam);
        ItemButton buttonFindTeam = ItemButton.create(new ItemBuilder(Material.COMPASS)
                .setName("ZNAJDŹ ISTNIEJĄCĄ"), TeamButtonFunctions::searchForTeam);

        gui.addButton(buttonCreateTeam, 11);
        gui.addButton(buttonFindTeam, 15);

        return gui;
    }
}
