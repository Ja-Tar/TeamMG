package io.github.jatar.teamMG;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jspecify.annotations.NonNull;

import java.util.*;
import java.util.function.Supplier;

import static org.bukkit.Bukkit.getLogger;

public class InventoryListeners {
    public static final Set<String> inventoryNameList = getInventoryNameList();

    public static class StopItemsOut implements Listener {
        @EventHandler
        public void onInventoryEvent(@NonNull InventoryClickEvent event) {
            getLogger().info(event.getView().title());
        }
    }

    public static Set<String> getInventoryNameList() {
        Set<String> invNames = new HashSet<>();

        for (Class<?> someClass : TeamInventory.class.getClasses()) {
            if (!TeamInventory.TeamInv.class.isAssignableFrom(someClass)) continue;
            try {
                // konwencja: pole nazywa się INV_NAME i jest public static final String
                java.lang.reflect.Field f = someClass.getField("INV_NAME");
                if (f.getType() == String.class && java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
                    String name = (String) f.get(null); // static -> null jako receiver
                    invNames.add(name);
                } else {
                    getLogger().warning("Pole INV_NAME ma nieprawidłowy typ lub nie jest static w " + someClass.getName());
                }
            } catch (NoSuchFieldException e) {
                // pole nie istnieje -> pomiń (możesz logować, jeżeli chcesz)
                // getLogger().info("Brak INV_NAME w " + someClass.getName());
            } catch (IllegalAccessException e) {
                getLogger().warning("Brak dostępu do INV_NAME w " + someClass.getName());
            }
        }

        return invNames;
    }
}
