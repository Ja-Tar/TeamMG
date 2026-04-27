package io.github.jatar.teamMG;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.NonNull;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;

import static io.github.jatar.teamMG.TeamMG.mm;

public class TeamInventory {
    public static @NonNull ItemStack getCustomItemStack(Material itemType, String formatedItemName) {
        Component parsed = mm.deserialize(formatedItemName).decoration(TextDecoration.ITALIC, false);
        ItemStack item = new ItemStack(itemType);
        item.editMeta(meta -> meta.customName(parsed));
        return item;
    }

    public static @NonNull InventoryGUI NoTeamInv() {
        InventoryGUI gui = new InventoryGUI(Bukkit.createInventory(null, 27, Component.text("Drużyna:")));

        ItemButton buttonCreateTeam = ItemButton.create(
                getCustomItemStack(Material.LIME_WOOL, "<b><gradient:#00FF1E:#00B315>STWÓRZ DRUŻYNĘ</b>"),
                TeamButtonFunctions::createNewTeam);
        ItemButton buttonFindTeam = ItemButton.create(
                getCustomItemStack(Material.BOOK, "<b><gradient:#006FDB:#0015B3>ZOBACZ ZAPROSZENIA</b>"),
                TeamButtonFunctions::searchForTeam);

        gui.addButton(buttonCreateTeam, 11);
        gui.addButton(buttonFindTeam, 15);

        return gui;
    }

    public static @NonNull InventoryGUI MngTeamInv() {
        InventoryGUI gui = new InventoryGUI(Bukkit.createInventory(null, 27, Component.text("Drużyna:")));
        // TODO: Add inventory for team manager
        return gui;
    }

    public static @NonNull InventoryGUI UsrTeamInv() {
        InventoryGUI gui = new InventoryGUI(Bukkit.createInventory(null, 27, Component.text("Drużyna:")));
        // TODO: Add inventory for team members
        return gui;
    }
}
