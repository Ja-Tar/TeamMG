package io.github.jatar.teamMG;

import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.input.DialogInput;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickCallback;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

import static io.github.jatar.teamMG.TeamMG.mm;

@SuppressWarnings({"UnstableApiUsage"})
public class TeamDialogs {
    public static @NonNull Dialog createTeamDialog(String wrongInfo) {
        Component teamCommandNameLabel = mm.deserialize("ID drużyny (<color:red><bold><underlined>tego nie da się zmienić<reset>, bez spacji)");
        Component teamNameLabel = mm.deserialize("Nazwa drużyny <i>(pole może być puste)");
        Component wrongText = mm.deserialize(wrongInfo);

        return Dialog.create(builder -> builder.empty()
                .base(DialogBase.builder(Component.text("Tworzenie nowej drużyny:", NamedTextColor.GREEN))
                        .body(
                                List.of(
                                        DialogBody.plainMessage(wrongText)
                                )
                        )
                        .inputs(List.of(
                                DialogInput.text("teamCommandName", teamCommandNameLabel)
                                        .width(300)
                                        .maxLength(30)
                                        .build(),
                                DialogInput.text("teamName", teamNameLabel)
                                        .width(300)
                                        .maxLength(30)
                                        .build()
                        ))
                        .build()
                ).type(DialogType.confirmation(
                        ActionButton.create(
                                Component.text("Anuluj", TextColor.color(0xff0000)),
                                null,
                                100,
                                null // If we set the action to null, it doesn't do anything and closes the dialogue
                        ),
                        ActionButton.create(
                                Component.text("Zapisz", TextColor.color(0x00ff00)),
                                null,
                                100,
                                DialogAction.customClick(TeamDialogCallbacks::acceptNewTeamDialog, ClickCallback.Options.builder()
                                        .uses(1) // Set the number of uses for this callback. Defaults to 1
                                        .lifetime(ClickCallback.DEFAULT_LIFETIME) // Set the lifetime of the callback. Defaults to 12 hours
                                        .build())
                        )
                ))
        );
    }

    public static @NonNull Dialog removeTeamCheck() {
        return  Dialog.create(builder -> builder.empty()
                .base(DialogBase.builder(mm.deserialize("Czy chcesz <red><b><u>usunąć</u></b><reset> drużynę?"))
                        .build()
                ).type(DialogType.confirmation(
                        ActionButton.create(
                                Component.text("ANULUJ usuwanie", TextColor.color(0xff0000)),
                                null,
                                120,
                                null // If we set the action to null, it doesn't do anything and closes the dialogue
                        ),
                        ActionButton.create(
                                Component.text("POTWIERDŹ usuwanie", TextColor.color(0x00ff00)),
                                null,
                                120,
                                DialogAction.customClick(TeamDialogCallbacks::acceptRemoveTeamDialog, ClickCallback.Options.builder()
                                        .uses(1)
                                        .lifetime(ClickCallback.DEFAULT_LIFETIME)
                                        .build())
                        )
                ))
        );
    }

    public static @NonNull Dialog listJoinRequests() {
        List<DialogInput> inputs = new ArrayList<>();
        // TODO: add request list

        // all players will be listed with boolean selector
        return  Dialog.create(builder -> builder.empty()
                .base(DialogBase.builder(mm.deserialize("Lista próśb o dołączenie"))
                        .inputs(inputs)
                        .build()
                ).type(DialogType.confirmation(
                        ActionButton.create(
                                Component.text("ANULUJ", TextColor.color(0xff0000)),
                                null,
                                100,
                                null
                        ),
                        ActionButton.create(
                                Component.text("POTWIERDŹ dołączenie", TextColor.color(0x00ff00)),
                                null,
                                100,
                                DialogAction.customClick(TeamDialogCallbacks::acceptSelectedRequests, ClickCallback.Options.builder()
                                        .uses(1)
                                        .lifetime(ClickCallback.DEFAULT_LIFETIME)
                                        .build())
                        )
                ))
        );
    }
}
