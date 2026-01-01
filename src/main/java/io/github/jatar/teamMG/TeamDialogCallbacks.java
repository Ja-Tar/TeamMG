package io.github.jatar.teamMG;

import io.papermc.paper.dialog.DialogResponseView;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import static io.github.jatar.teamMG.ScoreboardMG.scoreboard;
import static io.github.jatar.teamMG.TeamMG.getLog;

@SuppressWarnings({"UnstableApiUsage"})
public class TeamDialogCallbacks {
    public static void acceptNewTeamDialog(DialogResponseView view, Audience audience) {

        final String teamCommandName = view.getText("teamCommandName");
        final String teamName = view.getText("teamName");

        if (audience instanceof Player player) {
            assert teamCommandName != null;
            assert teamName != null;

            if (!PatternCheckers.isValidCommandName(teamCommandName)) {
                player.showDialog(TeamDialogs.createTeamDialog("<red>ID drużyny może mieć tylko:</red><newline><b>- + . _ A-Z a-z 0-9</b>"));
                return;
            }

            player.sendRichMessage("Command team name: <color:#ccfffd><comm-name></color> | Team name: <color:#ccfffd><view-name></color>",
                    Placeholder.component("comm-name", Component.text(teamCommandName)),
                    Placeholder.component("view-name", Component.text(teamName))
            );
            // ADD HERE COMMANDS TO SET UP TEAM !!!
            final Team newTeam;
            try {
                newTeam = ScoreboardMG.scoreboard.registerNewTeam(teamCommandName);
            } catch (IllegalArgumentException e) {
                getLog().info("Team exists: " + teamCommandName);
                player.sendRichMessage(RichMessagePrefixes.error("ID drużyny już istnieje: <color:#ccfffd><comm-name></color>"),
                        Placeholder.component("comm-name", Component.text(teamCommandName))
                );
                return;
            }
            newTeam.displayName(Component.text(teamName));
        }
    }
}
