package io.github.jatar.teamMG;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import redempt.redlib.commandmanager.CommandHook;
import redempt.redlib.commandmanager.CommandParser;
import redempt.redlib.inventorygui.InventoryGUI;

import java.util.logging.Logger;

import static io.github.jatar.teamMG.TeamInventory.*;

public final class TeamMG extends JavaPlugin {
    private static Logger logger;
    static MiniMessage mm = MiniMessage.miniMessage();

    public static Logger getLog() {
        return logger;
    }

    @Override
    public void onEnable() {
        getLogger().info("Uruchamianie...");

        new CommandParser(this.getResource("command.rdcml")).parse().register("teammg", this);

        getLogger().info("Komendy dodane!");

    }

    private void openGUI(CommandSender sender, InventoryGUI gui) {
        Player player = (Player) sender;
        gui.open(player);
    }

    @CommandHook("druzyna")
    public void createGetTeamComm(CommandSender sender) {
        openGUI(sender, NoTeamInv());
    }
}
