package io.github.jatar.teamMG;

import net.kyori.adventure.text.Component;
import org.bukkit.plugin.java.JavaPlugin;

public final class TeamMG extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getComponentLogger().debug(Component.text("TEN PLUGIN DZIAŁA"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
