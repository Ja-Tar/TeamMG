package io.github.jatar.teamMG;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.inventorygui.PaginationPanel;
import redempt.redlib.itemutils.ItemBuilder;

import java.util.HashSet;
import java.util.Set;

import static io.github.jatar.teamMG.ScoreboardMG.scoreboard;
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
                getCustomItemStack(Material.BOOK, "<b><gradient:#006FDB:#0015B3>ZNAJDŹ ISTNIEJĄCĄ</b>"),
                TeamButtonFunctions::searchForTeam);

        gui.addButton(buttonCreateTeam, 11);
        gui.addButton(buttonFindTeam, 15);

        return gui;
    }

    public static @NonNull InventoryGUI MngTeamInv() {
        InventoryGUI gui = new InventoryGUI(Bukkit.createInventory(null, 27, Component.text("Drużyna:")));

        ItemButton buttonJoinRequests = ItemButton.create(
                getCustomItemStack(Material.IRON_DOOR, "<b><gradient:#0070ff:#0040ff:#0090ff>PROŚBY O DOŁĄCZENIE</b>"),
                TeamButtonFunctions::joinRequests);

        ItemButton buttonRemoveTeam = ItemButton.create(
                getCustomItemStack(Material.MAGMA_BLOCK, "<b><gradient:#ff0000:#910000:#ff0000>USUŃ DRUŻYNĘ</b>"),
                TeamButtonFunctions::removeTeam);

        gui.addButton(buttonJoinRequests, 11);
        gui.addButton(buttonRemoveTeam, 15);

        return gui;
    }

    public static @NonNull InventoryGUI UsrTeamInv() {
        InventoryGUI gui = new InventoryGUI(Bukkit.createInventory(null, 27, Component.text("Drużyna:")));
        // TODO: Add inventory for team members
        return gui;
    }

    private static @Nullable Set<ItemButton> teamsItemStack() {
        Set<TeamWrapper> teamWrapperSet = scoreboard.getTeams();
        assert teamWrapperSet != null;
        if (teamWrapperSet.isEmpty()) {
            return null;
        }
        Set<ItemButton> teamsItemStack = new HashSet<>();
        for (TeamWrapper teamWrapper : teamWrapperSet) {
            ItemStack item = new ItemStack(Material.GREEN_WOOL);
            item.editMeta(meta -> meta.customName(teamWrapper.displayName()));
            teamsItemStack.add(
                    ItemButton.create(item, TeamButtonFunctions::joinTeam)
            );
        }
        return teamsItemStack;
    }

    public static @NonNull InventoryGUI TeamsList() {
        InventoryGUI gui = new InventoryGUI(Bukkit.createInventory(null, 27, Component.text("Dostępne drużyny:")));

        PaginationPanel pages = new PaginationPanel(gui);
        pages.addSlots(0, 18); // Add the first two rows of the inventory to the paged panel.
        pages.addPagedButtons(teamsItemStack());
        ItemButton next = ItemButton.create(new ItemBuilder(Material.EMERALD_BLOCK).setName("Kolejna strona"), e -> pages.nextPage());
        ItemButton prev = ItemButton.create(new ItemBuilder(Material.EMERALD_BLOCK).setName("Poprzednia strona"), e -> pages.prevPage());
        gui.addButton(next, 18);
        gui.addButton(prev, 26);
        return gui;
    }
}
