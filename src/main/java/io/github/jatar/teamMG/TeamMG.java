package io.github.jatar.teamMG;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.translation.MiniMessageTranslationStore;
import net.kyori.adventure.translation.GlobalTranslator;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import redempt.redlib.commandmanager.CommandHook;
import redempt.redlib.commandmanager.CommandParser;
import redempt.redlib.inventorygui.InventoryGUI;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static io.github.jatar.teamMG.ScoreboardMG.scoreboard;
import static io.github.jatar.teamMG.TeamInventory.MngTeamInv;
import static io.github.jatar.teamMG.TeamInventory.NoTeamInv;

public final class TeamMG extends JavaPlugin {
    public static Logger logger;
    public static MiniMessage mm = MiniMessage.miniMessage();
    public static final Locale LocalePL = Locale.of("pl", "PL");

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
        MiniMessageTranslationStore store = MiniMessageTranslationStore.create(Key.key("teammg:translate"), mm);

        store.registerAll(Locale.US, ResourceBundle.getBundle("MiniMessages", Locale.US), true);
        store.registerAll(LocalePL, ResourceBundle.getBundle("MiniMessages", LocalePL), true);
        store.defaultLocale(Locale.US);

        GlobalTranslator.translator().addSource(store);
    }

    private void openGUI(CommandSender sender, @NotNull InventoryGUI gui) {
        Player player = (Player) sender;
        gui.open(player);
    }

    @CommandHook("druzyna")
    public void createGetTeamComm(CommandSender sender) {
        Player player = (Player) sender;
        Locale playerLocale = player.locale();
        TeamWrapper team = scoreboard.getEntityTeam(player);
        if (team == null) {
            openGUI(sender, NoTeamInv(playerLocale));
        } else {
            if (team.isTeamManager((Player) sender)) {
                openGUI(sender, MngTeamInv(playerLocale));
            } else {
                sender.sendMessage(mm.deserialize("<red>Masz już drużynę! <reset>(podgląd dodam później)"));
            }
            // TODO: Później dodać żeby ta sama komenda pozwalała na podgląd aktualnej drużyny
        }

    }
}
