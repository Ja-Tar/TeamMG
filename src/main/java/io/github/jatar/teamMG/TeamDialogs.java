package io.github.jatar.teamMG;

import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.input.DialogInput;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.jspecify.annotations.NonNull;

import java.util.List;

import static io.github.jatar.teamMG.TeamMG.mm;

@SuppressWarnings({"UnstableApiUsage"})
public class TeamDialogs {
    public static @NonNull Dialog createTeamDialog() {
        Component warning = mm.deserialize("<color:red><bold><underlined>UWAGA!!! Tego nie można zmienić!");

        return Dialog.create(builder -> builder.empty()
                .base(DialogBase.builder(Component.text("Tworzenie nowej drużyny:", NamedTextColor.GREEN))
                        .body(
                                List.of(
                                        DialogBody.plainMessage(warning)
                                )
                        )
                        .inputs(List.of(
                                DialogInput.text("teamCommandName", Component.text("ID drużyny (bez spacji, bez wielkich liter)"))
                                        .width(300)
                                        .maxLength(30)
                                        .build(),
                                DialogInput.text("teamName", Component.text("Nazwa drużyny"))
                                        .width(300)
                                        .maxLength(30)
                                        .build()
                        ))
                        .build()
                ).type(DialogType.confirmation(
                        ActionButton.create(
                                Component.text("Zapisz", TextColor.color(0x00ff00)),
                                null,
                                100,
                                DialogAction.customClick(Key.key("papermc:user_input/confirm"), null)
                        ),
                        ActionButton.create(
                                Component.text("Anuluj", TextColor.color(0xff0000)),
                                null,
                                100,
                                null // If we set the action to null, it doesn't do anything and closes the dialogue
                        )
                ))
        );
    }
}
