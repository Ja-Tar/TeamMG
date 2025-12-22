package io.github.jatar.teamMG;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import redempt.redlib.commandmanager.CommandHook;
import redempt.redlib.commandmanager.CommandParser;
import redempt.redlib.inventorygui.InventoryGUI;

import java.util.logging.Logger;

public final class TeamMG extends JavaPlugin {
    private static Logger logger;

    public static Logger getLog() {
        return logger;
    }

    @Override
    public void onEnable() {
        getLogger().info("Uruchamianie...");

        new CommandParser(this.getResource("command.rdcml")).parse().register("teammg", this);

        getLogger().info("Komendy dodane!");

    }

    @CommandHook("druzyna")
    public void createGetTeamComm(CommandSender sender) {
        Player player = (Player) sender;
        getLogger().info("TTEST");
        InventoryGUI gui = TeamInventory.NoTeamInv();
        gui.open(player);
    }
}
