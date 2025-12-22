package io.github.jatar.teamMG;

import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import net.kyori.adventure.text.Component;

public class TeamDialogs {
    public static Dialog createTeamDialog() {
        Dialog dialog = Dialog.create(builder -> builder.empty()
                .base(DialogBase.builder(Component.text("Title")).build())
                .type(DialogType.notice())
        );
        return dialog;
    }
}
