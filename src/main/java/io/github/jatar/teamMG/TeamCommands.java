package io.github.jatar.teamMG;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.github.jatar.teamMG.TeamInventory.NoTeamInv;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class TeamCommands {
    public static LiteralCommandNode<CommandSourceStack> createGetTeamComm(final String commandName) {
        return Commands.literal(commandName)
                .executes(ctx -> {
                    final CommandSender sender = ctx.getSource().getSender();
                    final Player player = (Player)sender;

                    sender.sendPlainMessage("This plugin has a command. Your username: " + sender.getName());

                    NoTeamInv noTeamInv = new NoTeamInv();
                    player.openInventory(noTeamInv.getInventory());

                    return Command.SINGLE_SUCCESS;
                })
                .build();
    }
}
