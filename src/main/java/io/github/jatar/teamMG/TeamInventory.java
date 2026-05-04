package io.github.jatar.teamMG;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.translation.GlobalTranslator;
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
import java.util.Locale;
import java.util.Set;

import static io.github.jatar.teamMG.ScoreboardMG.scoreboard;

public class TeamInventory {
    public static @NonNull ItemStack getCustomItemStack(Material itemType, Component formatedItemName, Locale playerLocale) {
        Component parsed = formatedItemName.decoration(TextDecoration.ITALIC, false);
        ItemStack item = new ItemStack(itemType);
        item.editMeta(meta -> meta.customName(GlobalTranslator.render(parsed, playerLocale)));
        return item;
    }

    public static @NonNull InventoryGUI NoTeamInv(Locale playerLocale) {
        InventoryGUI gui = new InventoryGUI(Bukkit.createInventory(null, 27, Component.translatable("gui.inv.noTeam.name")));

        ItemButton buttonCreateTeam = ItemButton.create(
                getCustomItemStack(Material.LIME_WOOL, Component.translatable("gui.inv.noTeam.limeWool"), playerLocale),
                TeamButtonFunctions::createNewTeam);
        ItemButton buttonFindTeam = ItemButton.create(
                getCustomItemStack(Material.BOOK, Component.translatable("gui.inv.noTeam.book"), playerLocale),
                TeamButtonFunctions::searchForTeam);

        gui.addButton(buttonCreateTeam, 11);
        gui.addButton(buttonFindTeam, 15);

        return gui;
    }

    public static @NonNull InventoryGUI MngTeamInv(Locale playerLocale) {
        InventoryGUI gui = new InventoryGUI(Bukkit.createInventory(null, 27, Component.translatable("gui.inv.mngTeam.name")));

        ItemButton buttonJoinRequests = ItemButton.create(
                getCustomItemStack(Material.IRON_DOOR, Component.translatable("gui.inv.mngTeam.ironDoor"), playerLocale),
                TeamButtonFunctions::joinRequests);

        ItemButton buttonRemoveTeam = ItemButton.create(
                getCustomItemStack(Material.MAGMA_BLOCK, Component.translatable("gui.inv.mngTeam.magmaBlock"), playerLocale),
                TeamButtonFunctions::removeTeam);

        gui.addButton(buttonJoinRequests, 11);
        gui.addButton(buttonRemoveTeam, 15);

        return gui;
    }

    public static @NonNull InventoryGUI UsrTeamInv(Locale playerLocale) {
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

    public static @NonNull InventoryGUI TeamsList(Locale playerLocale) {
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
