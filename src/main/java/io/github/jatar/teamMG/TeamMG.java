package io.github.jatar.teamMG;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.Component;
import org.bukkit.plugin.java.JavaPlugin;

public final class TeamMG extends JavaPlugin {

    @Override
    public void onEnable() {
        getComponentLogger().debug(Component.text("Uruchamianie..."));

        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register(TeamCommands.createGetTeamComm("druzyna"), "Stwórz drużynę");
        });

        getComponentLogger().debug(Component.text("Komendy dodane!"));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
