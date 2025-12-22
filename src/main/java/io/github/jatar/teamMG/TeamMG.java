package io.github.jatar.teamMG;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.Component;
import org.bukkit.plugin.java.JavaPlugin;

public final class TeamMG extends JavaPlugin {

    @Override
    public void onEnable() {
        getComponentLogger().debug(Component.text("Uruchamianie..."));

        setupCommands();
        setupListeners();

        getComponentLogger().debug(Component.text("Komendy dodane!"));

    }

    void setupCommands() {
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register(TeamCommands.createGetTeamComm("druzyna"), "Stwórz drużynę");
        });
    }

    void setupListeners() {
        getServer().getPluginManager().registerEvents(new InventoryListeners.StopItemsOut(), this);
    }
}
