package io.github.jatar.teamMG;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.translation.GlobalTranslator;
import net.kyori.adventure.translation.TranslationStore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import redempt.redlib.commandmanager.CommandHook;
import redempt.redlib.commandmanager.CommandParser;
import redempt.redlib.inventorygui.InventoryGUI;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static io.github.jatar.teamMG.ScoreboardMG.scoreboard;
import static io.github.jatar.teamMG.TeamInventory.MngTeamInv;
import static io.github.jatar.teamMG.TeamInventory.NoTeamInv;

public final class TeamMG extends JavaPlugin {
    public static Logger logger;
    static MiniMessage mm = MiniMessage.miniMessage();

    public static Logger getLog() {
        return logger;
    }

    @Override
    public void onEnable() {
        logger = getLogger();

        getLogger().info("Loading...");

        loadTranslations();

        getLogger().info("Translations loaded!");

        new CommandParser(this.getResource("command.rdcml")).parse().register("teammg", this);

        getLogger().info("Commands added!");
    }

    private static void loadTranslations() {
        TranslationStore.StringBased<MessageFormat> store = TranslationStore.messageFormat(Key.key("teammg:translate"));
        ResourceBundle usBundle = ResourceBundle.getBundle("Bundle", Locale.US);
        store.registerAll(Locale.US, usBundle, true);
        ResourceBundle plBundle = ResourceBundle.getBundle("Bundle", Locale.of("pl", "PL"));
        store.registerAll(Locale.of("pl", "PL"), plBundle, true);
        GlobalTranslator.translator().addSource(store);
    }

    private void openGUI(CommandSender sender, @NotNull InventoryGUI gui) {
        Player player = (Player) sender;
        gui.open(player);
    }

    @CommandHook("druzyna")
    public void createGetTeamComm(CommandSender sender) {
        Player player = (Player) sender;
        TeamWrapper team = scoreboard.getEntityTeam(player);
        if (team == null) {
            openGUI(sender, NoTeamInv());
        } else {
            if (team.isTeamManager((Player) sender)) {
                openGUI(sender, MngTeamInv());
            } else {
                sender.sendMessage(mm.deserialize("<red>Masz już drużynę! <reset>(podgląd dodam później)"));
            }
            // TODO: Później dodać żeby ta sama komenda pozwalała na podgląd aktualnej drużyny
        }

    }
}
