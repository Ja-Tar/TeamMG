package io.github.jatar.teamMG;

import io.papermc.paper.dialog.DialogResponseView;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import static io.github.jatar.teamMG.ScoreboardMG.scoreboard;
import static io.github.jatar.teamMG.TeamMG.getLog;
import static io.github.jatar.teamMG.TeamMG.logger;

@SuppressWarnings({"UnstableApiUsage"})
public class TeamDialogCallbacks {
    public static void acceptNewTeamDialog(DialogResponseView view, Audience audience) {

        final String teamCommandName = view.getText("teamCommandName");
        String teamName = view.getText("teamName");

        if (audience instanceof Player player) {
            assert teamCommandName != null;
            assert teamName != null;

            if (!PatternCheckers.isValidCommandName(teamCommandName)) {
                player.showDialog(TeamDialogs.createTeamDialog(Component.translatable("gui.menu.createTeam.wrongInfo"), player.locale()));
                return;
            }

            final TeamWrapper newTeam;
            try {
                newTeam = scoreboard.registerNewTeam(teamCommandName);
            } catch (IllegalArgumentException e) {
                if (e.getMessage().contains("is already in use")) {
                    getLog().info("Team already exists: " + teamCommandName);
                    player.sendMessage(RichMessagePrefixes.error(Component.translatable("message.acceptNewTeam.idExists", Component.text(teamCommandName))));
                    return;
                }
                throw e;
            }
            if (!teamName.isEmpty()) {
                newTeam.displayName(Component.text(teamName));
            } else teamName = teamCommandName;

            player.sendMessage(RichMessagePrefixes.done(Component.translatable("message.acceptNewTeam.createdTeam", Component.text(teamCommandName), Component.text(teamName))));

            newTeam.addEntity(player);
            newTeam.setTeamManager(player);
            logger.info("Team created -> %s".formatted(newTeam.getName()));
        }
    }

    public static void acceptRemoveTeamDialog(DialogResponseView view, Audience audience) {
        if (audience instanceof Player player) {
            TeamWrapper team = scoreboard.getEntityTeam(player);
            assert team != null;
            if (team.isTeamManager(player)) {
                team.removeTeam(player);
                player.sendMessage(RichMessagePrefixes.done(Component.translatable("message.acceptRemoveTeam.removedTeam")));
            }
        }
    }

    public static void acceptSelectedRequests(DialogResponseView view, Audience audience) {
        // TODO finish
    }
}
